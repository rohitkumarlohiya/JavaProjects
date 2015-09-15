package com.estel.utility;

/**
 * Created with IntelliJ IDEA.
 * User: rohit
 * Date: 29/1/14
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EncryptionAlgorithm {
    public String encrypt(String stringToBeEncrypted);
    public String getDecryptedPasswordOfDB(String encryptedPassword);
    public String getDecryptedPasswordOfSMTP(String encryptedPassword);
}


