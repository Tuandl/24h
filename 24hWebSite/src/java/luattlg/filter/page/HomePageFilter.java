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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import tuanvxm.DAOs.ArticleDAO;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.Category;
import tuanvxm.other.Role;
import tuanvxm.other.RoleList;

/**
 * Filter for loading the article of the home page
 */
@WebFilter(filterName = "HomePageFilter", urlPatterns = {"/tuanda/index.jsp"}, 
        dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.REQUEST})
public class HomePageFilter implements Filter {

    private static final boolean debug = true;
    private static final int GETTOP = 15;
    private static final int STARTDAY = 365;

    private FilterConfig filterConfig = null;

    public HomePageFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("HomePageFilter:DoBeforeProcessing");
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
            log("HomePageFilter:DoAfterProcessing");
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

        //Load article 
        Map<String, ArrayList<ArticleDTO>> articleWithCategory = new HashMap<>();
        List<Category> listOfCategory = (ArrayList<Category>) request.getServletContext().getAttribute("CATEGORY-LIST");
        ArrayList<Role> listOfRole = (ArrayList<Role>)request.getServletContext().getAttribute("ROLE-LIST");
        List<UserDTO>  listOfUserDTOs = new UserDAO().findByRoleID(getRoleID("journalist",listOfRole));
        HashMap<Integer,String> mapUser;
        mapUser = new HashMap<Integer, String>();
        for(UserDTO user : listOfUserDTOs){
            mapUser.put(new Integer(user.getUserID()),user.getName());
        }
        for (Category category : listOfCategory) {
            ArrayList<ArticleDTO> articles = (ArrayList<ArticleDTO>) new ArticleDAO().findByCategoryIDAndStatus(category.getCategoryID(), ArticleDTO.STATUS_AVAILABLE);
            for(ArticleDTO article : articles){
                article.setCreator(mapUser.get(new Integer(article.getCreatorID())));
            }
            articleWithCategory.put(category.getName(), articles);
        }

        //Get trending article count from now-GETTOP day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -STARTDAY);
        Timestamp time = new Timestamp(calendar.getTime().getTime());
        ArrayList<ArticleDTO> articles = (ArrayList) new ArticleDAO().findTopViewCountCreatedAfterTime(GETTOP, time);
        ArrayList<ArticleDTO> topTrendAfetDelete = new ArrayList<>();
         for (ArticleDTO trendArticle : articles) {
            if (trendArticle.getStatus().equalsIgnoreCase(ArticleDTO.STATUS_AVAILABLE)) {
                topTrendAfetDelete.add(trendArticle);
                trendArticle.setCreator(mapUser.get(new Integer(trendArticle.getArticleID())));
            }
        }
        request.setAttribute("TOP-TREND-LIST", topTrendAfetDelete);
        request.setAttribute("ARTICLE-LIST-BY-CATEGORY", articleWithCategory);   
        chain.doFilter(request, response);
    }

    private int getRoleID(String rolename, ArrayList<Role> listOfRole) {
        //System.out.println("Get Role ID home page filter here "+listOfRole.size());
        for (Role role : listOfRole) {
            if (role.getName().equalsIgnoreCase(rolename)) {
                return role.getRoleID();
            }
        }
        return 0;
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
                log("HomePageFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("HomePageFilter()");
        }
        StringBuffer sb = new StringBuffer("HomePageFilter(");
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
