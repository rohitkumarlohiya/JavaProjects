package com.estel.utility;

import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: rohit
 * Date: 29/1/14
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class EncryptionAlgorithmImpl implements EncryptionAlgorithm {

    private String algorithm;

    public EncryptionAlgorithmImpl() {
    }

    public EncryptionAlgorithmImpl(String algorithm) {
        this.algorithm = algorithm;
    }

    public String encrypt(String stringToBeEncrypted) {

        String encryptedString = "";

        if (algorithm.equalsIgnoreCase(Constant.MD5)) {
            encryptedString = MD5.MD5Convertor(stringToBeEncrypted);
        } else if (algorithm.equalsIgnoreCase(Constant.SHA2)) {
            EncriptionSHA2Impl sha2 = null;

            try {
                sha2 = new EncriptionSHA2Impl("SHA-256");
                encryptedString = EncriptionSHA2Impl.bytesToHex(sha2.digest(stringToBeEncrypted.getBytes()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return encryptedString.toUpperCase();
    }

    public String getDecryptedPasswordOfDB(String encryptedPassword) {

        //String decryptedPassword = new MposEncryption().decrypt(encryptedPassword, false, "");
        return "123456";
    }

    public String getDecryptedPasswordOfSMTP(String encryptedPassword) {

//        if(encryptedPassword == null || encryptedPassword.equalsIgnoreCase(""))
//        {
//            return "";
//        }
//        else
//        {
        String decryptedPassword = new MposEncryption().decrypt(encryptedPassword, false, "");
        return decryptedPassword;
        //}
    }

}
