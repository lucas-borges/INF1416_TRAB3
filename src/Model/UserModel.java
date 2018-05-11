/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
    private String digital_certificate;
    private int blocked;
    private String blocked_since;
    private int number_of_access;
    private int number_of_searches_key;
    private int number_of_searches_files;

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
    public String getDigital_certificate() {
        return digital_certificate;
    }

    /**
     * @param digital_certificate the digital_certificate to set
     */
    public void setDigital_certificate(String digital_certificate) {
        this.digital_certificate = digital_certificate;
    }

    /**
     * @return the blocked
     */
    public int isBlocked() {
        return blocked;
    }

    /**
     * @param blocked the blocked to set
     */
    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    /**
     * @return the blocked_since
     */
    public String getBlocked_since() {
        return blocked_since;
    }

    /**
     * @param blocked_since the blocked_since to set
     */
    public void setBlocked_since(String blocked_since) {
        this.blocked_since = blocked_since;
    }

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
    

    public UserModel(String username, String password, String login, String salt, int id_group, String digital_certificate, int blocked, String blocked_since, int number_of_searches_key, int number_of_searches_files, int number_of_access) {
        super();
        this.login = login;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.id_group = id_group;
        this.digital_certificate = digital_certificate;
        this.blocked = blocked;
        this.blocked_since = blocked_since;
        this.number_of_access = number_of_access;
        this.number_of_searches_files = number_of_searches_files;
        this.number_of_searches_key = number_of_searches_key;
    }
    
    public UserModel(String username, String password, int blocked, int number_of_access) {
        super();
        this.password = password;
        this.username = username;
        this.blocked = blocked;
        this.number_of_access = number_of_access;
    }
}
