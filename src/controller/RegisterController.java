/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.security.SecureRandom;

/**
 *
 * @author joy
 */
public class RegisterController {

    public static String generateSalt() {
        SecureRandom rand = new SecureRandom();
        StringBuffer salt = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            salt.append(new Integer(rand.nextInt(9)).toString());
        }
        return salt.toString();
    }

}
