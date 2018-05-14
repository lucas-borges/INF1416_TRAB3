/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 *
 * @author Lucas
 */
public class Certificate {
    X509Certificate certificate;
    public Certificate(String path) throws FileNotFoundException, CertificateException, IOException{
        InputStream inStream = null;
        try {
            inStream = new FileInputStream("Keys/admin-x509.crt");
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            certificate = (X509Certificate)cf.generateCertificate(inStream);
//            System.out.println(certificate.getSubjectX500Principal().toString());
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }
    }
        
    public Certificate(InputStream inStream) throws FileNotFoundException, CertificateException, IOException{
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            certificate = (X509Certificate)cf.generateCertificate(inStream);
//            System.out.println("########" + certificate.getSubjectX500Principal().toString());
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }
    }
    
    public PublicKey getPublicKey(){
        return certificate.getPublicKey();
    }
}
