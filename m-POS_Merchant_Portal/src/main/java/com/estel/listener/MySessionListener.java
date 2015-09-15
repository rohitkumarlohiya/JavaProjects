package com.estel.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 27/12/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class MySessionListener implements HttpSessionListener, ServletRequestListener {

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("*********************sessionCreated");
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("*********************sessionDestroyed");
    }

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        //System.out.println("*********************requestDestroyed");
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        //System.out.println("*********************requestInitialized");
    }
}
