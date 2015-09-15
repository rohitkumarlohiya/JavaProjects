package com.estel.exception;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 24/12/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class EntityException extends Exception {


    String msg = null;
    int level = 100;

    public EntityException() {
        super();
    }

    public EntityException(String msg, Exception ex) {
        super(msg, ex);
        this.msg = msg;
    }

    public String getDescription() {
        return "Entity General Error";
    }

    public String getMessage() {

        return level+" "+msg;
    }

}

