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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import tuanvxm.DAOs.ReportDAO;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.Role;

/**
 * Filter for checking role and load data for admin page. If it's not admin,
 * redirect to Home page.
 */
@WebFilter(filterName = "AdminPageFilter", urlPatterns = {"/tuanda/admin-home-page.jsp"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class AdminPageFilter implements Filter {

    private static final String HOME = "/tuanda/index.jsp";
    private static final boolean debug = true;
    private int page_split;
    private static final int TOP_ARTICLE = 10;
    private static final int DAY_CALCULATE = 30;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AdminPageFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminPageFilter:DoBeforeProcessing");
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
            log("AdminPageFilter:DoAfterProcessing");
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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        int pageEditor = 1;
        int pageJournalist = 1;
        int pageReader = 1;
        String role = (String) httpRequest.getSession().getAttribute("ROLE");
        if (role == null || !role.equalsIgnoreCase("administrator")) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        page_split = Integer.parseInt(request.getServletContext().getInitParameter("SIZEOFPAGE"));
        String dateStart = httpRequest.getParameter("httpDateStart");
        String dateEnd = httpRequest.getParameter("httpDateEnd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Date getTime = sdf.parse(request.getParameter("txtDateOfBirth").substring(0, 10));
        try {
            pageEditor = Integer.parseInt(httpRequest.getParameter("txtPageEditor"));
            pageJournalist = Integer.parseInt(httpRequest.getParameter("txtpageJournalist"));
            pageReader = Integer.parseInt(httpRequest.getParameter("txtpageReader"));
        } catch (Exception e) {
            //System.out.println("This is the init");
        }
        pageEditor--;
        pageJournalist--;
        pageReader--;
        request.setAttribute("curPageEditor", pageEditor);
        request.setAttribute("curPageJournalist", pageJournalist);

        List<Role> listOfRole = (ArrayList<Role>) request.getServletContext().getAttribute("ROLE-LIST");

        List<UserDTO> listOfEditor = new UserDAO().findByRoleID(getRoleID("editor", listOfRole));
        List<UserDTO> listOfJournalist = new UserDAO().findByRoleID(getRoleID("journalist", listOfRole));
        List<UserDTO> listOfReader = new UserDAO().findByRoleID(getRoleID("reader", listOfRole));
        ReportDAO reportDAO = new ReportDAO();
        for (UserDTO journalist : listOfJournalist) {
            List<Integer> list = reportDAO.reportArticleByCreatorID(journalist.getUserID());
            journalist.setNumberOfAllArticle(list.get(1));
            journalist.setNumberOfAvailableArticle(list.get(0));
        }
        UserDAO userDAO = new UserDAO();
        Timestamp timeStart = null;
        Timestamp timeEnd = null;

        try {
            Date getTimeStart = sdf.parse(dateStart);
            Date getTimeEnd = sdf.parse(dateEnd);
            timeStart = new Timestamp(getTimeStart.getTime());
            timeEnd = new Timestamp(getTimeEnd.getTime());
        } catch (Exception ex) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, -DAY_CALCULATE);
            timeStart = new Timestamp(calendar.getTime().getTime());
            timeEnd = new Timestamp(new Date().getTime());
        }
        try{
            dateStart = sdf.format(new Date(timeStart.getTime()));
            dateEnd = sdf.format(new Date(timeEnd.getTime()));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        List<ArticleDTO> listOfTopView = reportDAO.findTopViewCountArticle(TOP_ARTICLE, timeStart, timeEnd);
        for (ArticleDTO article : listOfTopView) {
            article.setCreator(userDAO.findByUserID(article.getCreatorID()).getName());
        }
        request.setAttribute("NUMBEROFARTICLE", new ArticleDAO().findByTitle("%%").size());
        request.setAttribute("TOPVIEWARTICLE", listOfTopView);

        request.setAttribute("MAXEDITORPAGE", General.getMaxPage(listOfEditor.size(), page_split));
        request.setAttribute("MAXJOURNALISTPAGE", General.getMaxPage(listOfJournalist.size(), page_split));
        request.setAttribute("MAXREADERPAGE", General.getMaxPage(listOfReader.size(), page_split));

        request.setAttribute("EDITOR-LIST", listOfEditor.subList(pageEditor * page_split, Math.min((pageEditor + 1) * page_split, listOfEditor.size())));
        request.setAttribute("JOURNALIST-LIST", listOfJournalist.subList(pageJournalist * page_split, Math.min((pageJournalist + 1) * page_split, listOfJournalist.size())));
        request.setAttribute("READER-LIST", listOfReader.subList(pageReader * page_split, Math.min((pageReader + 1) * page_split, listOfReader.size())));

        request.setAttribute("TIMESTARTREPORT", dateStart);
        request.setAttribute("TIMEENDREPORT", dateEnd);
        chain.doFilter(request, response);
    }

    private int getRoleID(String rolename, List<Role> listOfRole) {
        for (Role role : listOfRole) {
            if (role.getName().equalsIgnoreCase(rolename)) {
                return role.getRoleID();
            }

        }
        return 0;
    }

    private int getMaxPage(int number,int page){
        if(number == 0){
            number++;
        }
        return number / page + (number%page == 0 ? 0 : 1);
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
                log("AdminPageFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AdminPageFilter()");
        }
        StringBuffer sb = new StringBuffer("AdminPageFilter(");
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
