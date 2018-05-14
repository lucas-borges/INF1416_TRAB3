/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Certificate;
import Model.UserModel;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Joyce - MeConsulte
 */
public class UserDAO {
    
    public static UserModel getUserByUsername(String username) {
        Factory.openConnection();
        String query = "SELECT * FROM users WHERE email = '" + username + "';";
        UserModel result = null;
        try {
            ResultSet rs = Factory.connection.createStatement().executeQuery(query);
            if (rs.next()) {
                result = new UserModel(rs.getString("email"), rs.getString("password"), rs.getString("salt"), 
                        rs.getInt("ugroup"), rs.getString("name"), new Certificate(rs.getBlob("digital_cert").getBinaryStream()),
                        rs.getInt("password_errors"), rs.getInt("privatekey_validation_errors"), rs.getTimestamp("blocked_until"), rs.getInt("access_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            Factory.closeConnection();            
        }

        return result;
    }

    public static void setPasswordError(UserModel user, int password_errors) {
        String query = "UPDATE users SET password_errors = " + password_errors + " WHERE email = '" + user.getUsername() + "' ;";
        ExecuteQuery(query);
    }
    
    private static void ExecuteQuery(String query){
        Factory.openConnection();
        System.out.println(">>>> " + query);
        try {
            Factory.connection.createStatement().executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            Factory.closeConnection();            
        }
    }

    public static void setBlockedUntil(UserModel user, Date blocked_until) {
        Timestamp timestamp = new Timestamp(blocked_until.getTime());
        String query = "UPDATE users SET blocked_until = '" + timestamp + "' WHERE email = '" + user.getUsername() + "' ;";
        ExecuteQuery(query);
    }

    public static void setPrivateKeyError(UserModel user, int private_key_errors) {
        String query = "UPDATE users SET privatekey_validation_errors = " + private_key_errors + " WHERE email = '" + user.getUsername() + "' ;";
        ExecuteQuery(query);
    }

    public static void setNumberOfAccess(UserModel user, int number_of_access) {
        String query = "UPDATE users SET access_number = " + number_of_access + " WHERE email = '" + user.getUsername() + "' ;";
        ExecuteQuery(query);
    }
}
