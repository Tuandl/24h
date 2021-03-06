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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.Role;
import tuanvxm.other.RoleList;

/**
 * This filter to check if user is editor or not. If it's not editor, redirect
 * to home.
 *
 */
@WebFilter(filterName = "EditorPageFilter", urlPatterns = {"/tuanda/censor-page.jsp"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class EditorPageFilter implements Filter {

    private static final boolean debug = true;
    private static final String HOME = "home.jsp";
    private int page_split;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public EditorPageFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("EditorPageFilter:DoBeforeProcessing");
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
            log("EditorPageFilter:DoAfterProcessing");
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
        //Check role
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String role = (String) httpRequest.getSession().getAttribute("ROLE");
        if (role == null || !role.equalsIgnoreCase("editor")) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(HOME);
            return;
        }
        page_split = Integer.parseInt(request.getServletContext().getInitParameter("SIZEOFPAGE"));
        int page = 1;
        int pageNew = 1;
        int pageAvailable = 1;
        int pageHided = 1;
        try {
            page = Integer.parseInt(request.getParameter("txtPage"));
            pageNew = Integer.parseInt(request.getParameter("txtPageNew"));
            pageAvailable = Integer.parseInt(request.getParameter("txtPageAvailable"));
            pageHided = Integer.parseInt(request.getParameter("txtpageHided"));
        } catch (Exception e) {
            System.out.println("This is the init");
        }
        pageNew = page;
        pageAvailable = page;
        pageHided = page;
        pageNew--;
        pageAvailable--;
        pageHided--;
        page--;
        request.setAttribute("curPage", page);

        //Load article for editor
        List<ArticleDTO> newArticleList = new ArticleDAO().findByStatus(ArticleDTO.STATUS_NEW);
        List<ArticleDTO> hidedArticleList = new ArticleDAO().findByStatus(ArticleDTO.STATUS_HIDED);

        //Get creator
        List<UserDTO> listOfUserDTOs = new UserDAO().findByRoleID(RoleList.getID("journalist"));
        HashMap<Integer, String> mapUser;
        mapUser = new HashMap<Integer, String>();
        for (UserDTO user : listOfUserDTOs) {
            mapUser.put(new Integer(user.getUserID()), user.getName());
        }

        for (ArticleDTO article : newArticleList) {
            article.setCreator(mapUser.get(new Integer(article.getCreatorID())));
        }
        for (ArticleDTO article : hidedArticleList) {
            article.setCreator(mapUser.get(new Integer(article.getCreatorID())));
        }

        newArticleList = sortList(newArticleList);
        hidedArticleList = sortList(hidedArticleList);

        request.setAttribute("MAXNEWPAGE", General.getMaxPage(newArticleList.size(), page_split));
        request.setAttribute("MAXHIDEDPAGE", General.getMaxPage(hidedArticleList.size(), page_split));

        try {
            request.setAttribute("NEW-ARTICLE-LIST", newArticleList.subList(pageNew * page_split, Math.min((pageNew + 1) * page_split, newArticleList.size())));
        } catch (Exception e) {
            request.setAttribute("NEW-ARTICLE-LIST", new ArrayList<ArticleDTO>());
        }
        try {
            request.setAttribute("HIDED-ARTICLE-LIST", hidedArticleList.subList(pageHided * page_split, Math.min((pageHided + 1) * page_split, hidedArticleList.size())));
        } catch (Exception e) {
            request.setAttribute("HIDED-ARTICLE-LIST", new ArrayList<ArticleDTO>());
        }
        chain.doFilter(request, response);

    }

    private List<ArticleDTO> sortList(List<ArticleDTO> list) {
        list.sort(new Comparator<ArticleDTO>() {
            @Override
            public int compare(ArticleDTO t, ArticleDTO t1) {
                return t1.getCreatedTime().compareTo(t.getCreatedTime());
            }
        });
        return list;
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
                log("EditorPageFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("EditorPageFilter()");
        }
        StringBuffer sb = new StringBuffer("EditorPageFilter(");
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
