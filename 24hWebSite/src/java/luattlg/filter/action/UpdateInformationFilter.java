/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.filter.action;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
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

/**
 *
 * @author luattlgse62386
 */
@WebFilter(filterName = "UpdateInformationFilter", urlPatterns = {"/UpdateInformation.action"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class UpdateInformationFilter implements Filter {
    
    private static final boolean debug = true;
    private static final String PATH = "/tuanda/profile.jsp";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public UpdateInformationFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("UpdateInformationFilter:DoBeforeProcessing");
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
            log("UpdateInformationFilter:DoAfterProcessing");
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
        
        Map<String,String> error = doValidate((HttpServletRequest) request,(HttpServletResponse) response);
        if(!error.isEmpty()){
            System.out.println("have error " + error.size());
            request.setAttribute("ERROR", error);
            request.getRequestDispatcher(PATH).forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
    
     private Map<String,String> doValidate(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        
        //Get value
        String name = request.getParameter("txtName");
        request.setAttribute("txtName", name);
        String address = request.getParameter("txtAddress");
        request.setAttribute("txtAddress", address);
        String phoneNumber = request.getParameter("txtPhoneNumber");
        request.setAttribute("txtPhoneNumber", phoneNumber);
        String email = request.getParameter("txtEmail");
        request.setAttribute("txtEmail", email);
        String birthday = request.getParameter("txtDateOfBirth");
        request.setAttribute("txtDateOfBirth", birthday);
        
        //Validate
        Map<String,String> error = new HashMap<>();
        if(name == null || name.length() == 0){
            error.put("NAME", "Họ và tên không được bỏ trống.");
        }
        if(address == null || address.length() == 0){
            error.put("ADDRESS", "Địa chỉ không được bỏ trống.");
        }
        if(phoneNumber == null || phoneNumber.length() == 0){
            error.put("PHONE", "Số điện thoại không được bỏ trống.");
        }
        else{
            if(!Pattern.matches("[0-9]{9,11}", phoneNumber)){
                error.put("PHONE", "Số điện thoại phải có độ dài từ 9-11 số.");
            }
        }
        if(email == null || email.length() == 0){
            error.put("EMAIL", "E-mail không được bỏ trống.");
        }
        else{
            if(!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+(\\.[a-zA-Z]+)+", email)){
                error.put("EMAIL","E-mail không đúng.");
            }
        }
        int year = Integer.parseInt(birthday.split("/")[2]);
        Calendar nowCal = Calendar.getInstance();
        if(nowCal.get(Calendar.YEAR)-year < 16){
            error.put("BIRTHDAY","Bạn phải từ 16 tuổi trơ lên mới được phép đăng ký tài khoản trên trang của chúng tôi.");
        }
        
        return error;
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
                log("UpdateInformationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("UpdateInformationFilter()");
        }
        StringBuffer sb = new StringBuffer("UpdateInformationFilter(");
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
