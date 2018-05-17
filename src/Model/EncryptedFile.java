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
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Lucas
 */
public class EncryptedFile {
    private String path;
    private PrivateKey privateKey;
    private byte[] seed;
    private SecretKey symmetricKey;
    private PublicKey publicKey;
    
    protected byte[] fileContents;
    protected boolean verified;
    
    public EncryptedFile(String path, PrivateKey privateKey, PublicKey publicKey) {
        this.path = path;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    public boolean checkPath(){
        File enc = new File(path+".enc");
        File env = new File(path+".env");
        File asd = new File(path+".asd");
        return enc.isFile() && env.isFile() && asd.isFile();
    }
    
    private void openEnvelope(){
        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//
            File inputFile = new File(path+".env");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            inputStream.close();
//
            seed = cipher.doFinal(inputBytes);
               

//            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//            KeyGenerator keygen = KeyGenerator.getInstance("DES");
//            random.setSeed(inputBytes);
//            keygen.init(random);
//            symmetricKey = keygen.generateKey();
            SecureRandom random = new SecureRandom(seed);
		
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56, random);

            symmetricKey = keyGen.generateKey();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean decryptFile(){
        try{
            openEnvelope();
            
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, symmetricKey);

            File inputFile = new File(path+".enc");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            inputStream.close();
            
//            System.out.println(">>>" + inputBytes.toString());
            fileContents = cipher.doFinal(inputBytes);
            
            FileOutputStream fos = new FileOutputStream(path+".txt");
            fos.write(fileContents);
            fos.close();
            
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean verifyFile(){
        try{
            File inputFile = new File(path+".asd");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] signatureBytes = new byte[(int) inputFile.length()];
            inputStream.read(signatureBytes);
            inputStream.close();

            // might be SHA256withRSA
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(fileContents);
            verified =  signature.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verified;
    }
    
    public String getContentsString() {
        if(!verified) {
            System.out.println(">>>>>>>>>>>>> File '"+path+"' has to first be verified!");
        }
        
        return new String(fileContents);
    }
}
