/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.EventsModel;

/**
 *
 * @author Joyce - MeConsulte
 */
public class EventsDAO {
    public static void insertEvent(int event_id, int user_id){
        Factory.openConnection();
        String query;
        if (user_id == -1){
            query = "INSERT INTO registers (message_id) VALUES("+event_id+");";
        } else {
            query = "INSERT INTO registers (user, message_id) VALUES("+user_id+", "+event_id+");";
        }
        EventsModel event;
        try {
            Factory.connection.createStatement().executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            Factory.closeConnection();            
        }
    }
}
