package com.rohit;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/InternationalizationDemoServlet")
public class InternationalizationDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		/*System.out.println(" request.getLocale()"+ request.getLocale());
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("com.rohit.DemoBundle", request.getLocale());
		if(resourceBundle == null){
		
			resourceBundle = ResourceBundle.getBundle("com.rohit.DemoBundle",Locale.US);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("resourceBundle",resourceBundle);*/
		
		response.sendRedirect("index.jsp");
		
		
		
	}

}
