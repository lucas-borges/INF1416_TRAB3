/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import Model.UserModel;
import java.util.List;

/**
 *
 * @author Joyce - MeConsulte
 */
public class LoginController {
    private static UserModel user;
    
    public static UserModel findUser(String username){
        user = UserDAO.getUserByUsername(username);
        if(user!=null){
            System.out.println("Usuário encontrado.");
        }
        
        return user;
    }

    public static boolean checkPassword(List<String> typed_password) {
        String[][] phonemes = new String [3][3];
        for (int i=0;i<3;i++) {
            phonemes[i] = typed_password.get(i).split(" - ");
        }
        String possible_password;
        boolean correct_password = false;
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                for (int k=0;k<3;k++) {
                    possible_password = phonemes[0][i]+phonemes[1][j]+phonemes[2][k];
                    correct_password = user.checkPassword(possible_password);
                    if(correct_password){
                        return true;
                    }
                }
            }
        }        
        return false;
    }

    public static void processCorrectPassword() {
        user.resetPasswordError();
    }
    
    public static int processIncorrectPassword() {
        return user.increasePasswordError();
    }

    public static void blockUserStepTwo() {
       user.blockStepTwo();
    }
}
