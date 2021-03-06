/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.filter.page;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.List;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luattlg.other.General;
import tuanvxm.DAOs.ArticleDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.UserDTO;

/**
 *
 * @author luattlgse62386
 */
@WebFilter(filterName = "JournalistPageFilter", urlPatterns = {"/tuanda/journalist-manage-articles.jsp"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class JournalistPageFilter implements Filter {

    private static final boolean debug = true;
    private int page_split;
    private static final String HOME = "tuanda/index.jsp";

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public JournalistPageFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("JournalistPageFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("JournalistPageFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String role = (String) ((HttpServletRequest) request).getSession().getAttribute("ROLE");
        page_split = Integer.parseInt(request.getServletContext().getInitParameter("SIZEOFPAGE"));
        if (!role.equalsIgnoreCase("journalist")) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            httpRequest.setAttribute("WARNING", "Please login to journalist to use this");
            httpRequest.getRequestDispatcher(HOME).forward(request, response);
            return;
        }
        int page = 1;
        try {
            page = Integer.parseInt(((HttpServletRequest) request).getParameter("txtPage"));
        } catch (Exception ex) {
            System.out.println("This is init");
        }
        page--;
        request.setAttribute("curPage", page);
        if (role == null || !role.equalsIgnoreCase("journalist")) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "This page is only for journalist. Please login to journalist and try again.");
            return;
        }
        UserDTO user = (UserDTO) ((HttpServletRequest) request).getSession().getAttribute("USER");
        List<ArticleDTO> listOfArticle = new ArticleDAO().findByCreatorID(user.getUserID());
        listOfArticle.sort(new Comparator<ArticleDTO>() {
            @Override
            public int compare(ArticleDTO t, ArticleDTO t1) {
                if (t.getCategoryID() == t1.getCategoryID()) {
                    return t1.getCreatedTime().compareTo(t.getCreatedTime());
                }//To change body of generated methods, choose Tools | Templates.
                return t.getCategoryID() < t1.getCategoryID() ? -1 : 1;
            }
        });
        request.setAttribute("MAXPAGE",  General.getMaxPage(listOfArticle.size(), page_split));
        request.setAttribute("ARTICLE-LIST", listOfArticle.subList(page * page_split, Math.min((page + 1) * page_split, listOfArticle.size())));
        chain.doFilter(request, response);

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("JournalistPageFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("JournalistPageFilter()");
        }
        StringBuffer sb = new StringBuffer("JournalistPageFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
