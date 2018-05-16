/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.EventModel;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Joyce - MeConsulte
 */
public class EventsDAO {
        public static void insertEvent(int event_id){
        String query  = "INSERT INTO REGISTERS (message_id) VALUES("+event_id+");";
        ExecuteQuery(query);
    }
    
    public static void insertEvent(int event_id, String login_name){
        String query  = "INSERT INTO REGISTERS (user, message_id) VALUES('"+login_name+"', "+event_id+");";
        ExecuteQuery(query);
    }
    
    public static void insertEvent(int event_id, String login_name, String file_name){
        String query  = "INSERT INTO REGISTERS (user, message_id, file) VALUES('"+login_name+"', "+event_id+", '"+file_name+"');";
        ExecuteQuery(query);
    }
    
    public static List<EventModel> getEventsList() {
        Factory.openConnection();
        String query = "SELECT registers.id, messages.string, registers.user, registers.file, registers.datetime" +
                        " FROM registers INNER JOIN messages" +
                        " ON registers.message_id=messages.id;";
        List<EventModel> result = new ArrayList<EventModel>();
        try {
            ResultSet rs = Factory.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                result.add(new EventModel(rs.getInt("id"), rs.getString("string"), rs.getString("user"), rs.getString("file"), rs.getTimestamp("datetime")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            Factory.closeConnection();            
        }
        return result;
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
}
