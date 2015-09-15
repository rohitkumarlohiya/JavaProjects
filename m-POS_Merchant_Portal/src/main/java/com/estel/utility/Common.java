package com.estel.utility;

import com.estel.exception.MaliciousCharacterException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: rohit
 * Date: 28/1/14
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Common {

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void maliciousCharacterExist(String str) throws MaliciousCharacterException
    {
        for(int i=0;i<str.length();i++)
        {
            char charCode1 = str.charAt(i);
            if((charCode1 > 47 && charCode1 < 58) || (charCode1 > 64 && charCode1 < 91) || (charCode1 > 96 && charCode1 < 123) || charCode1 == 64 || charCode1 == 45 || charCode1 == 95 || charCode1 == 46|| charCode1 == 8
                    || charCode1 == 35 || charCode1 == 44 ||charCode1 == 59 || charCode1 == 58 || charCode1 == 39 || charCode1 == 40 || charCode1 == 41 || charCode1 == 32)//support for @/./-/_ numbers and alphabats/#/,/;/:/'/(/)/
            {

            }
            else
            {
                throw new MaliciousCharacterException("malicious character");
            }
        }
    }

    /*public static void main(String[] args) {

        try {
            Common.maliciousCharacterExist("rohit!@#$%^&*()_+-");
        } catch (MaliciousCharacterException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            //System.out.println("dsafds");
        }

    }*/
}
