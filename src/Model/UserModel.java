/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.UserDAO;
import java.security.PublicKey;
import java.util.Date;

/**
 *
 * @author Joyce
 */
public class UserModel {
    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    private int user_id;
    private String username;
    private String password;
    private String login;
    private String salt;
    private int id_group;
    private Certificate digital_certificate;
    private Date blocked_until;
    private int password_errors;
    private int private_key_errors;
    private int number_of_access;
    private int number_of_searches_key;
    private int number_of_searches_files;
    private String name;
    
    public int getPrivate_key_errors() {
        return private_key_errors;
    }

    public int getPassword_errors() {
        return password_errors;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the id_group
     */
    public int getId_group() {
        return id_group;
    }

    /**
     * @param id_group the id_group to set
     */
    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    /**
     * @return the digital_certificate
     */
    public Certificate getDigital_certificate() {
        return digital_certificate;
    }

    /**
     * @param digital_certificate the digital_certificate to set
     */
    public void setDigital_certificate(Certificate digital_certificate) {
        this.digital_certificate = digital_certificate;
    }

    /**
     * @return the blocked
     */
    public boolean isBlocked() {
        if(blocked_until == null) {
            return false;
        }
        if(blocked_until.after(new Date())){
            return true;
        }
        return false;
    }

//    /**
//     * @param blocked the blocked to set
//     */
//    public void setBlocked(int blocked) {
//        this.blocked = blocked;
//    }

    /**
     * @return the blocked_since
     */
    public Date getBlocked_until() {
        return blocked_until;
    }

//    /**
//     * @param blocked_since the blocked_since to set
//     */
//    public void setBlocked_since(String blocked_since) {
//        this.blocked_since = blocked_since;
//    }

    /**
     * @return the number_of_access
     */
    public int getNumber_of_access() {
        return number_of_access;
    }

    /**
     * @param number_of_access the number_of_access to set
     */
    public void setNumber_of_access(int number_of_access) {
        this.number_of_access = number_of_access;
    }

    /**
     * @return the number_of_searches_key
     */
    public int getNumber_of_searches_key() {
        return number_of_searches_key;
    }

    /**
     * @param number_of_searches_key the number_of_searches_key to set
     */
    public void setNumber_of_searches_key(int number_of_searches_key) {
        this.number_of_searches_key = number_of_searches_key;
    }

    /**
     * @return the number_of_searches_files
     */
    public int getNumber_of_searches_files() {
        return number_of_searches_files;
    }

    /**
     * @param number_of_searches_files the number_of_searches_files to set
     */
    public void setNumber_of_searches_files(int number_of_searches_files) {
        this.number_of_searches_files = number_of_searches_files;
    }
    
    public boolean checkPassword(String possible_password) {
        Password pass = new Password(possible_password, salt);
        if(pass.getPassword().equals(this.password))
            return true;
        return false;
    }

    public UserModel(String username, String password, String salt, int ugroup, String name, Certificate cert, 
            int password_errors, int private_key_errors,  Date blocked_until, int number_of_access) {
        super();
        this.password = password;
        this.username = username;
        this.password_errors = password_errors;
        this.private_key_errors = private_key_errors;
        this.salt = salt;
        this.blocked_until = blocked_until;
        this.number_of_access = number_of_access;
        this.digital_certificate = cert;
        this.id_group = ugroup;
        this.name = name;
    }
    
    public void resetPasswordError(){
        this.password_errors = 0;
        UserDAO.setPasswordError(this, password_errors);
    }

    public int increasePasswordError() {
        this.password_errors += 1;
        UserDAO.setPasswordError(this, password_errors);
        return this.password_errors;
    }

    public void blockStepTwo() {
        this.blocked_until = new Date(System.currentTimeMillis()+2*60*1000);
        UserDAO.setBlockedUntil(this, blocked_until);
        this.password_errors = 0;
        UserDAO.setPasswordError(this, password_errors);
    }

    public PublicKey getPublicKey() {
        return digital_certificate.getPublicKey();
    }

    public void resetPrivateKeyError() {
        this.private_key_errors = 0;
        UserDAO.setPrivateKeyError(this, private_key_errors);
    }

    public int increasePrivateKeyError() {
        this.private_key_errors += 1;
        UserDAO.setPrivateKeyError(this, private_key_errors);
        return this.private_key_errors;
    }

    public void blockStepThree() {
        this.blocked_until = new Date(System.currentTimeMillis()+2*60*1000);
        UserDAO.setBlockedUntil(this, blocked_until);
        this.private_key_errors = 0;
        UserDAO.setPrivateKeyError(this, private_key_errors);
    }

    public int increaseAccessCount() {
        this.number_of_access += 1;
        UserDAO.setNumberOfAccess(this, number_of_access);
        return this.number_of_access;
    }
}
