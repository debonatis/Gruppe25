/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.unboundid.util.Base64;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/**
 *
 * @author simond
 */
public class Passwordgenerator {

    public static BASE64Encoder base64;

    public static void main(String[] args) {
        System.out.println("Den riktige: " + encryptPassword("admin"));
        System.out.println("SHA-256-->Base64: " + encryptPassword(Base64.encode("admin")));
        System.out.println("encryptPasswordBase64: " + encryptPasswordBase64("admin"));
    }

    public static String encryptPassword(String planepassword) {
        try {
            MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            md.update(planepassword.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            String encodedPasswordHash = new sun.misc.BASE64Encoder().encode(passwordDigest);

            return encodedPasswordHash;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return "";
    }

    public static String encryptPasswordBase64(String planepassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(planepassword.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();

            return org.apache.directory.api.util.Base64.encode(digest).toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return "";
    }

}
