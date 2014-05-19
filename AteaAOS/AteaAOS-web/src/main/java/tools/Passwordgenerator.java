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

/**
 *
 * @author simond
 */
public class Passwordgenerator {

    public static void main(String[] args) {
        System.out.println(Base64.encode(encryptPassword("admin")));
    }

    public static String encryptPassword(String planepassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(planepassword.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);

            return output;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return "";
    }

}
