package com.estel.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: rohit
 * Date: 28/1/14
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyFilter implements Filter {

    public void destroy() {
        //System.out.println("*********************Filter******destroy");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //System.out.println("*********************Filter******doFilter");

        String maliciousCharacters = "Malicious characters in request \\nPlease do not use characters other than @ . _ # , : ( )";

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        httpServletResponse.addHeader("X-FRAME-OPTIONS", "SAMEORIGIN");


        String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = httpServletRequest.getRemoteAddr();
        }

        final Logger logger = LoggerFactory.getLogger(MyFilter.class.getName());
        logger.info("Request Come from IP : " + ipAddress);

        Enumeration en = req.getParameterNames();

        while (en.hasMoreElements()) {
            Object objOri = en.nextElement();
            String param = (String) objOri;
            String value = req.getParameter(param);
            for (int i = 0; i < value.length(); i++) {
                char charCode1 = value.charAt(i);
                if ((charCode1 > 47 && charCode1 < 58) || (charCode1 > 64 && charCode1 < 91) || (charCode1 > 96 && charCode1 < 123) || charCode1 == 64 || charCode1 == 95 || charCode1 == 46 || charCode1 == 45 ||charCode1 == 8
                        || charCode1 == 35 || charCode1 == 44 ||charCode1 == 58 || charCode1 == 40 || charCode1 == 41 || charCode1 == 32 || charCode1 == 47)//support for @ . _ numbers and alphabats # , : ( )
                {

                } else {

                    if (httpServletRequest.getRequestURL().toString().contains("signUpAction")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/signUp.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else  if (httpServletRequest.getRequestURL().toString().contains("forgotPasswordAction")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/forgotPassword.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("changePasswordAction")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/changePassword.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("forcedChangePasswordAction")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/forcedChangePassword.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("changeMpinAction")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/changeMpin.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

//                    else if (httpServletRequest.getRequestURL().toString().contains("merchantBusinessInfoAdd")) {
//                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
//                        httpServletRequest.getRequestDispatcher("/merchantBusinessInfoEdit.do").forward(httpServletRequest, httpServletResponse);
//                        return;
//                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("merchantBusinessInfoAdd")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/merchantBusinessInfoAction.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

//                    else if (httpServletRequest.getRequestURL().toString().contains("merchantProfileAdd")) {
//                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
//                        httpServletRequest.getRequestDispatcher("/merchantProfileEdit.do").forward(httpServletRequest, httpServletResponse);
//                        return;
//                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("merchantProfileAdd")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/merchantProfileAction.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("bankAccountEdit")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/bankAccountList.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("deviceAdd")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/deviceList.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("deviceEdit")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/deviceList.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("orderDeviceAdd")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/orderDevice.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }

                    else if (httpServletRequest.getRequestURL().toString().contains("searchReceiptByTransactionIdorDate")) {
                        httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                        httpServletRequest.getRequestDispatcher("/searchReceipt.do").forward(httpServletRequest, httpServletResponse);
                        return;
                    }


                    PrintWriter out = resp.getWriter();
                    out.println("<h1>" + maliciousCharacters + "</h1>");
                    logger.info("malicious character in request :: " + charCode1);
                    //((HttpServletResponse)resp).sendRedirect("/Mpos");
                    httpServletRequest.setAttribute("errorMessage", maliciousCharacters);
                    httpServletRequest.getRequestDispatcher("/Mpos.do").forward(httpServletRequest, httpServletResponse);
                    return;
                }
            }
        }

        chain.doFilter(req, httpServletResponse);
    }

    public void init(FilterConfig config) throws ServletException {
        //System.out.println("*********************Filter******init");
    }
}
