/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.EventsDAO;

/**
 *
 * @author Joyce - MeConsulte
 */
public class EventsController {
    public static void insertNewEvent(int event_id){
        EventsDAO.insertEvent(event_id);
    }
    
    public static void insertNewEvent(int event_id, String login_name){
        EventsDAO.insertEvent(event_id, login_name);
    }
    
    public static void insertNewEvent(int event_id, String login_name, String file_name){
        EventsDAO.insertEvent(event_id, login_name, file_name);
    }
}
