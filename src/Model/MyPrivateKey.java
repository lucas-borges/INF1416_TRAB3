/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Lucas
 */
public class MyPrivateKey {
//    private byte[] symmetricKey = new byte[56];
    SecretKey symmetricKey;
    PrivateKey privateKey;
    
    public MyPrivateKey(String path, String passphrase){
        generateSymmetricKey(passphrase);

        try {
            byte[] rawPrivateKey =  decryptPrivateKeyFile(path);
//            rawPrivateKey = toDecodedBase64ByteArray(rawPrivateKey);
            String stringPrivateKey = new String(rawPrivateKey, "UTF-8");
            stringPrivateKey = stringPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "");  
            stringPrivateKey = stringPrivateKey.replace("-----END PRIVATE KEY-----", ""); 
            byte[] decodedPrivateKey = toDecodedBase64ByteArray(stringPrivateKey.getBytes());
                 
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
            
            System.out.println(privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public byte[] signRandomBytes(byte[] bytes){
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        
        byte[] signatureBytes = null;
        
        try{
            Signature sig = Signature.getInstance("MD5withRSA");
            sig.initSign(privateKey);
            sig.update(bytes);
            signatureBytes = sig.sign();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return signatureBytes;
    }
    
    private byte[] decryptPrivateKeyFile(String path) throws NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, symmetricKey);

        File inputFile = new File(path);
        File outputFile = new File(path+".oooooo");
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);
        inputStream.close();

        byte[] outputBytes =  cipher.doFinal(inputBytes);
        
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputBytes);
        
        return outputBytes;
    }

    private void generateSymmetricKey(String passphrase) {
        try{
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            random.setSeed(passphrase.getBytes());
            keygen.init(random);
            symmetricKey = keygen.generateKey();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static byte[] toDecodedBase64ByteArray(byte[] base64EncodedByteArray) {
        return DatatypeConverter.parseBase64Binary(
            new String(base64EncodedByteArray, Charset.forName("UTF-8")));
}
}
