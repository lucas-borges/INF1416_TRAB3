/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import sun.misc.BASE64Encoder;
import sun.security.provider.X509Factory;

/**
 *
 * @author Lucas
 */
public class Certificate {
    private X509Certificate certificate;
    public Certificate(String path) throws FileNotFoundException, CertificateException, IOException{
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(path);
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
        return getCertificate().getPublicKey();
    }
    
    public int getVersion(){
        return getCertificate().getVersion();
    }
    
    public BigInteger getSerialNumber(){
        return getCertificate().getSerialNumber();
    }
    
    public Date getValidFrom(){
       return getCertificate().getNotBefore();
    }
    
    public Date getValidTo(){
       return getCertificate().getNotAfter();
    }
    
    public String getSignAlgName(){
        return getCertificate().getSigAlgName();
    }
    
    public String getIssuer(){
        String issuer = getCertificate().getIssuerDN().getName();
        String[] split = issuer.split(","); 
        for (String x : split) {
            if (x.contains("CN=")) {
                return x.substring(x.indexOf('=')+1).trim();
            }
        }
        return "emissor nao encontrado";
    }
    
    public String getSubject(){
        String subject = getCertificate().getSubjectDN().getName();
        String[] split = subject.split(","); 
        for (String x : split) {
            if (x.contains("CN=")) {
                return x.substring(x.indexOf('=')+1).trim();
            }
        }
        return "sujeito nao encontrado";
    }
        
    public String getEmail() {
        String subject = getCertificate().getSubjectDN().getName();
        String[] split = subject.split(","); 
        for (String x : split) {
            if (x.contains("EMAILADDRESS=")) {
                return x.substring(x.indexOf('=')+1).trim();
            }
        }
        return "email nao encontrado";
    }
    
    public String getEncoded(){
        String encoded = "";
        try {
            OutputStream os = new ByteArrayOutputStream();
            BASE64Encoder encoder = new BASE64Encoder();
            os.write(X509Factory.BEGIN_CERT.getBytes());
            os.write("\n".getBytes());
            encoder.encodeBuffer(getCertificate().getEncoded(), os);
            os.write(X509Factory.END_CERT.getBytes());
            encoded = os.toString();
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return encoded;
    }

    /**
     * @return the certificate
     */
    public X509Certificate getCertificate() {
        return certificate;
    }
}
