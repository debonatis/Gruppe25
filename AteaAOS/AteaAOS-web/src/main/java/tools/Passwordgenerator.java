/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author simond
 */
public class Passwordgenerator {

   

    public static void main(String[] args) {
        System.out.println("Den riktige: " + encryptPassword("Curtis300"));
       
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

    

}
