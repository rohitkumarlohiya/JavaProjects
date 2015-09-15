package com.estel.exception;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 24/12/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class MandatoryValuesNull extends EntityException {

    public MandatoryValuesNull() {
        super();
    }

    public String getDescription() {
        return "Mandatory values are null";
    }

    public String getResultCode(Integer levelNumber) {
        return "" + msg;
    }
}


