/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
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
public class IndexFile {
    private String path;
    private PrivateKey privateKey;
    private byte[] seed;
    private SecretKey symmetricKey;
    private PublicKey publicKey;
    
    private byte[] indexContents;
    private boolean verified;
    
    private List<IndexItem> indexItems;
    
    public List<IndexItem> getIndexItems() {
        return indexItems;
    }
    
    public IndexFile(String path, PrivateKey privateKey, PublicKey publicKey) {
        this.path = path;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    public boolean checkPath(){
        File inputFile = new File(path);
        return inputFile.isDirectory();
    }
    
    public void openEnvelope(){
        try{
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//
            File inputFile = new File(path+"/index.env");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            inputStream.close();
//
//            seed = cipher.doFinal(inputBytes);
               

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            random.setSeed(inputBytes);
            keygen.init(random);
            symmetricKey = keygen.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void decryptIndex(){
        try{
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, symmetricKey);

            File inputFile = new File(path+"/index.enc");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            inputStream.close();
            
            System.out.println(">>>" + inputBytes.toString());
            indexContents = cipher.doFinal(inputBytes);

            System.out.println(indexContents.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verifyIndex(){
        try{
            File inputFile = new File(path+"/index.asd");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] signatureBytes = new byte[(int) inputFile.length()];
            inputStream.read(signatureBytes);
            inputStream.close();

            // might be SHA256withRSA
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(indexContents);
            verified =  signature.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verified;
    }
    
    public void parseIndexContents() {
        if(!verified) {
            System.out.println(">>>>>>>>>>>>> Index has to first be verified!");
        }
        
        indexItems = new ArrayList<IndexItem>();
        
        for(String line: indexContents.toString().split("\n")) {
            indexItems.add(new IndexItem(line));
        }
    }
}
