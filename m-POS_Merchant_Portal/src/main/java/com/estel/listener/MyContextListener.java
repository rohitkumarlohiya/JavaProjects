package com.estel.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 27/12/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("*********************contextInitialized");

        HashMap<String, HttpSession> hashMap = new HashMap<String, HttpSession>();
        servletContextEvent.getServletContext().setAttribute("hashMap", hashMap);

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("*********************contextDestroyed");
    }
}
