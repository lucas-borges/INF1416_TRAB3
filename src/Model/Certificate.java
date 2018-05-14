/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 *
 * @author Lucas
 */
public class Certificate {
    public Certificate(String path) throws FileNotFoundException, CertificateException, IOException{
        InputStream inStream = null;
        try {
            inStream = new FileInputStream("Keys/admin-x509.crt");
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(inStream);
            System.out.println(cert.getSubjectX500Principal().toString());
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }
    }
}
