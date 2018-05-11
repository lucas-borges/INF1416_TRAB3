/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.UserModel;
import java.sql.ResultSet;

/**
 *
 * @author Joyce - MeConsulte
 */
public class UserDAO {
    
    public static UserModel getUserByUsername(String username) {
        Factory.openConnection();
        String query = "SELECT * FROM user WHERE username = '" + username + "';";
        UserModel result = null;
        try {
            ResultSet rs = Factory.connection.createStatement().executeQuery(query);
            if (rs.next()) {
                result = new UserModel(rs.getString("username"), rs.getString("password"), rs.getInt("blocked"), rs.getInt("number_of_access"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            Factory.closeConnection();            
        }

        return result;
    }

    public static void setUserAvailable(UserModel user) {
        Factory.openConnection();
        String query = "UPDATE user SET blocked = '" + 0 + "', cont_login_error = '" + 0 + "',"
                + " blocked_since = '' WHERE username = '" + user.getUsername() + "' ;";
        try {
            Factory.statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Factory.closeConnection();
        }

    }
}
