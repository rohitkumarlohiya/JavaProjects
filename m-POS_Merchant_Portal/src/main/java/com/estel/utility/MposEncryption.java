package com.estel.utility;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Created with IntelliJ IDEA.
 * User: rohit
 * Date: 29/1/14
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class MposEncryption {
    private static String theIV = "0000000000000000";
    private static String encryptionkey = "4094426978780845";
    private static boolean badpadding = false;


    public byte[] hexToBytes(String str) {
        if (str == null)
            return null;
        if (str.length() < 2)
            return null;

        int len = str.length() / 2;
        byte[] buffer = new byte[len];
        for (int i = 0; i < len; ++i) {
            buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
        }

        return buffer;
    }

    public String bytesToHex(byte[] data) {
        if (data == null)
            return null;

        int len = data.length;
        String str = "";
        for (int i = 0; i < len; ++i)
            if ((data[i] & 0xFF) < 16) {
                str = str + "0" + Integer.toHexString(data[i] & 0xFF);
            } else
                str = str + Integer.toHexString(data[i] & 0xFF);

        return str.toUpperCase();
    }

    public String stringToHex(String str) {
        StringBuffer output = new StringBuffer();
        char[] chars = new char[str.length()];
        for (int i = 0; i < chars.length; ++i) {
            char b = str.charAt(i);
            output.append(Integer.toHexString(b));
        }
        return output.toString().toUpperCase();
    }

    public String getDecryptedPassword(String password) {

        String decryptPassword = new MposEncryption().decrypt(password, false, "");

        return decryptPassword;
    }

    public String decrypt(String EncryptedString, Boolean mpin, String key) {
        String completeHexMessage = null;
        String finalDecryptedMessage = null;
        Cipher cf3DES1 = null;
        try {

            String desKey = null;
            if (key != null && !key.isEmpty()) {
                desKey = generateDesKey(key);
            } else {
                desKey = generateDesKey(encryptionkey);

            }
            KeySpec ks3DES = new DESedeKeySpec(hexToBytes(desKey));
            SecretKeyFactory kf3DES = SecretKeyFactory.getInstance("DESede");
            SecretKey ky3DES = kf3DES.generateSecret(ks3DES);
            cf3DES1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(hexToBytes(theIV));
            cf3DES1.init(2, ky3DES, ivSpec);

            String decryptedHex = bytesToHex(cf3DES1.doFinal(hexToBytes(EncryptedString)));

            byte[] decryptedBytes_counter = hexToBytes(decryptedHex);

            String completeMessage = bytesToHex(decryptedBytes_counter);

            String pin = "";
            String message = "";
            if (mpin) {
                pin = completeMessage.substring(completeMessage.length() - 32);
                message = completeMessage.substring(0, completeMessage.length() - 32);

            } else {
                message = completeMessage.substring(0, completeMessage.length());

            }
            String decryptedString = hexToChar(message);
            StringBuffer parseMessage = new StringBuffer(decryptedString);

            parseMessage.append(pin);
            finalDecryptedMessage = parseMessage.toString();

        }
        catch (NoSuchAlgorithmException e) {
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        catch (NoSuchPaddingException e) {
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        catch (IllegalBlockSizeException e) {
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        catch (BadPaddingException e) {
            badpadding = true;
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        catch (InvalidKeyException e) {
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        catch (InvalidKeySpecException e) {
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        catch (InvalidAlgorithmParameterException e) {
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        catch (Exception e) {
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        finally {
        }
        return finalDecryptedMessage;
    }

    public String hexToChar(String base) {
        char[] chars = new char[base.length() / 2];

        for (int i = 0; i < chars.length; ++i) {
            chars[i] = (char) Integer.parseInt(base.substring(i * 2, i * 2 + 2), 16);
        }

        String str = new String(chars);
        return str;
    }

    public String generateDesKey(String activationcode) {
        String deskey = null;
        try {
            MessageDigest messageDigest = null;
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(hexToBytes(stringToHex(activationcode)));
            byte[] hash1 = messageDigest.digest();

            MessageDigest messageDigest1 = MessageDigest.getInstance("MD5");
            messageDigest1.update(hash1);
            byte[] hash2 = messageDigest1.digest();

            deskey = bytesToHex(hash1) + bytesToHex(hash2).substring(0, 16);
        }
        catch (NoSuchAlgorithmException e) {
            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }
        return deskey;
    }

    public String encrypt(String message, String mobileno, String mpin, String key) {
        String finalEncryptedMessage = null;
        try {
            byte[] counter = {0, 1};
            String finalKey = null;
            if (key != null && !key.isEmpty()) {
                finalKey = generateDesKey(key);
            } else {
                finalKey = generateDesKey(encryptionkey);
            }


            String almostCompleteMessageHex = stringToHex(new StringBuilder().append(message).toString());
            byte[] almostCompleteMessageByte = hexToBytes(almostCompleteMessageHex);


            KeySpec ks3DES = new DESedeKeySpec(hexToBytes(finalKey));
            SecretKeyFactory kf3DES = SecretKeyFactory.getInstance("DESede");
            SecretKey ky3DES = kf3DES.generateSecret(ks3DES);
            Cipher cf3DES = Cipher.getInstance("DESede/CBC/PKCS5Padding");

            IvParameterSpec ivSpec = new IvParameterSpec(hexToBytes(theIV));
            cf3DES.init(1, ky3DES, ivSpec);

            byte[] theCph = cf3DES.doFinal(almostCompleteMessageByte);
            finalEncryptedMessage = bytesToHex(theCph);
        }

        catch (Exception e)

        {

            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }

        return finalEncryptedMessage;
    }

    public String encrypt(String message, String key) {
        String finalEncryptedMessage = null;
        try {
            byte[] counter = {0, 1};
            String finalKey = null;
            if (key != null && !key.isEmpty()) {
                finalKey = generateDesKey(key);
            } else {
                finalKey = generateDesKey(encryptionkey);
            }


            String almostCompleteMessageHex = stringToHex(new StringBuilder().append(message).append("").toString());
            byte[] almostCompleteMessageByte = hexToBytes(almostCompleteMessageHex);
            KeySpec ks3DES = new DESedeKeySpec(hexToBytes(finalKey));
            SecretKeyFactory kf3DES = SecretKeyFactory.getInstance("DESede");
            SecretKey ky3DES = kf3DES.generateSecret(ks3DES);
            Cipher cf3DES = Cipher.getInstance("DESede/CBC/PKCS5Padding");

            IvParameterSpec ivSpec = new IvParameterSpec(hexToBytes(theIV));
            cf3DES.init(1, ky3DES, ivSpec);

            byte[] theCph = cf3DES.doFinal(almostCompleteMessageByte);
            finalEncryptedMessage = bytesToHex(theCph);
        }

        catch (Exception e)

        {

            //throw new BaseRuntimeException(e);
            System.out.println("Excerption occured.");
        }

        return finalEncryptedMessage;
    }

    public static void main(String[] args) {
        String username = "sp002";
        String encrypted = new MposEncryption().encrypt(username, "");
        String decrypted = new MposEncryption().decrypt(encrypted, false, "");
        System.out.println("username " + username + " encrypted " + encrypted + " decrypted " + decrypted);
    }
}


