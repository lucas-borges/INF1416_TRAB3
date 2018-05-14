/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Lucas
 */
public class Password {
    private byte[] calculated_hash;
    private String calculated_hash_HEX;

    public byte[] getCalculated_hash() {
        return calculated_hash;
    }

    public String getCalculated_hash_HEX() {
        return calculated_hash_HEX;
    }
    
    public String getPassword(){
        return calculated_hash_HEX;
    }
            
    public Password(String key, String salt){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String combined = key+salt;
        byte[] input = combined.getBytes();
        md.update(input);
        calculated_hash = md.digest();
        calculated_hash_HEX = Password.ByteToString(calculated_hash);
    }
    
    public static String generateSalt(){
        final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lower = upper.toLowerCase();
        final String digits = "0123456789";
        final String alphanum = upper + lower + digits;

        int count = 10;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*alphanum.length());
            builder.append(alphanum.charAt(character));
        }
        return builder.toString();
    }
    
    public static String ByteToString(byte[] info) {
        // convert to hexadecimal
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < info.length; i++) {
            String hex = Integer.toHexString(0x0100 + (info[i] & 0x00FF))
                    .substring(1);
            buf.append((hex.length() < 2 ? "0" : "") + hex);
        }
        return buf.toString();
    }
}
