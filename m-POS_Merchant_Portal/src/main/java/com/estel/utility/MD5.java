package com.estel.utility;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

/**
 * Created by IntelliJ IDEA.
 * User: Neeraj
 * Date: Apr 15, 2009
 * Time: 5:37:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class MD5
{
    static String request;
    public MD5( )
    {
        //request = value;
    }

    public static String MD5Convertor(String value)
    {
        MessageDigest algorithm = null;
         request=value;
        try
        {
            algorithm = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException nsae)
        {
            System.out.println("Cannot find digest algorithm");
            System.exit(1);
        }
        byte[] defaultBytes = request.getBytes();
        algorithm.reset();
        algorithm.update(defaultBytes);
        byte messageDigest[] = algorithm.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < messageDigest.length; i++)
        {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1)
            {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        String md5val = hexString.toString();
        //System.out.println("MD5 (" + request + ") = " + md5val);
        return md5val;

    }



    public static void main(String[] args)
    {
        /*String md5val = "";
        MessageDigest algorithm = null;

        try
        {
            algorithm = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException nsae)
        {
            System.out.println("Cannot find digest algorithm");
            System.exit(1);
        }

        for (String arg : args)
        {
            byte[] defaultBytes = arg.getBytes();
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < messageDigest.length; i++)
            {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1)
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            md5val = hexString.toString();
            System.out.println("MD5 (" + arg + ") = " + md5val);
        }*/
         // MD5.MD5Convertor("test"); 
        System.out.println("Test : "+MD5.MD5Convertor("test"));

    }
}
