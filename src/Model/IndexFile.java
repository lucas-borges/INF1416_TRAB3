/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Lucas
 */
public class IndexFile extends EncryptedFile {
    
    private List<IndexItem> indexItems;
    
    public List<IndexItem> getIndexItems() {
        return indexItems;
    }
    
    public IndexFile(String path, PrivateKey privateKey, PublicKey publicKey) {
        super(path, privateKey, publicKey);
    }
    
    public void parseIndexContents() {
        
        indexItems = new ArrayList<IndexItem>();
        String indexContentsString = this.getContentsString();
        
        for(String line: indexContentsString.split("\n")) {
            indexItems.add(new IndexItem(line));
        }
    }
}
