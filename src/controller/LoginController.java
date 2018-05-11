/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import Model.UserModel;

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
        else{
            return null;
        }
        
        return user;
    }
}
