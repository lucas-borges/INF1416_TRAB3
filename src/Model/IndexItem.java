/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lucas
 */
public class IndexItem {
    private String code_name;
    private String secret_name;
    private String owner;
    private String owner_group;

    public String getCode_name() {
        return code_name;
    }

    public String getSecret_name() {
        return secret_name;
    }

    public String getOwner() {
        return owner;
    }

    public String getOwner_group() {
        return owner_group;
    }
    
    public IndexItem(String raw_line) { 
        System.out.println(raw_line);
        String[] parts = raw_line.split(" ");
        code_name = parts[0];
        secret_name = parts[1];
        owner = parts[2];
        owner_group = parts[3];
    }
}
