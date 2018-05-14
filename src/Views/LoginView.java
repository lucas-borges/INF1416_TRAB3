/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.EventsModel;
import Model.UserModel;
import Model.Password;
import controller.EventsController;
import controller.LoginController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Joyce - MeConsulte
 */
public class LoginView {

    public static JPanel panel1;
    public static JPanel panel2;
    public static JTextField username;
    public static JTextField password;
    public static JLabel welcome;
    public static JFrame frame;
    public static JLabel username_label;
    public static JButton clear;
    public static JButton next;
    public static JButton[] btOp;
    public static UserModel user;
    public static List<String> typed_password = new ArrayList<String>();
    public static List<String> phonemes = new ArrayList<String>();

    
    //  ADMIN: admin@inf1416.puc-rio.br / BACADA
    public static void main(String[] args) throws FileNotFoundException, CertificateException, IOException, NoSuchAlgorithmException {
        EventsController.insertNewEvent(EventsModel.SISTEMA_INICIADO);
        frame = new JFrame("INF1416_TRABALHO_3");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new JPanel();
        frame.add(panel1, BorderLayout.CENTER);
        setStepOne(panel1);
        frame.setVisible(true);
    }

    private static void setStepOne(JPanel panel) {
        panel.setLayout(null);
        welcome = new JLabel("Bem Vindo(a)!");
        welcome.setBounds(60, 40, 200, 25);
        welcome.setFont(new java.awt.Font("Arial", 0, 24));
        panel.add(welcome);
        username_label = new JLabel("Identificação");
        username_label.setBounds(60, 90, 80, 25);
        username_label.setFont(new java.awt.Font("Arial", 0, 14));
        panel.add(username_label);
        username = new JTextField(20);
        username.setBounds(150, 90, 160, 25);
        username.setFont(new java.awt.Font("Arial", 0, 14));
        panel.add(username);
        clear = new JButton("Próximo");
        clear.setBounds(210, 140, 100, 25);
        clear.setFont(new java.awt.Font("Arial", 0, 14));
        clear.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                nextMouseClicked(e);
            }
        });
        panel.add(clear);
    }

    private static void nextMouseClicked(ActionEvent e) {
        EventsController.insertNewEvent(EventsModel.AUTENTICACAO_ETAPA_UM_INICIADA);
        user = LoginController.findUser(username.getText());
        if (user == null) {
            EventsController.insertNewEvent(EventsModel.LOGIN_NAO_IDENTIFICADO, user.getUsername());
            alertInvalidUsername();
        } else {
            if (user.isBlocked()) {
                EventsController.insertNewEvent(EventsModel.LOGIN_ACESSO_BLOQUEADO, user.getUsername());
                alertBlockedUser();
            } else {
                EventsController.insertNewEvent(EventsModel.LOGIN_ACESSO_LIBERADO, user.getUsername());
                EventsController.insertNewEvent(EventsModel.AUTENTICACAO_ETAPA_UM_ENCERRADA);
                frame.remove(panel1);
                panel2 = new JPanel();
                frame.add(panel2, BorderLayout.CENTER);
                setStepTwo(panel2);
            }
        }
    }

    private static void alertInvalidUsername() {
        JLabel warning;
        warning = new JLabel();
        warning.setText("<html><font color='red'>Usuário inválido.</font></html>");
        warning.setFont(new java.awt.Font("Arial", 0, 14));
        warning.setBounds(60, 150, 200, 30);
        panel1.add(warning);
        panel1.repaint();
    }

    private static void alertBlockedUser() {
        JLabel warning;
        warning = new JLabel();
        warning.setText("<html><font color='red'>Usuário bloqueado.</font></html>");
        warning.setFont(new java.awt.Font("Arial", 0, 14));
        warning.setBounds(60, 150, 200, 30);
        panel1.add(warning);
        panel1.repaint();
    }

    private static void setStepTwo(JPanel panel) {
        EventsController.insertNewEvent(EventsModel.AUTENTICACAO_ETAPA_DOIS_INICIADA);
        panel.setLayout(null);
        welcome = new JLabel("Bem Vindo(a)!");
        welcome.setBounds(60, 40, 200, 25);
        welcome.setFont(new java.awt.Font("Arial", 0, 24));
        panel.add(welcome);
        username_label = new JLabel("Senha");
        username_label.setBounds(60, 90, 80, 25);
        username_label.setFont(new java.awt.Font("Arial", 0, 14));
        panel.add(username_label);
        initButtons(panel);
        password = new JTextField(20);
        password.setEditable(false);
        password.setBounds(110, 150, 160, 25);
        password.setFont(new java.awt.Font("Arial", 0, 14));
        password.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (e.getDocument().getLength() == 6) {
                    next.setEnabled(true);
                    password.setEnabled(false);
                    for (int k = 0; k < btOp.length; k++) {
                        btOp[k].setEnabled(false);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (e.getDocument().getLength() == 0) {
                    next.setEnabled(false);
                    password.setEnabled(true);
                    for (int k = 0; k < btOp.length; k++) {
                        btOp[k].setEnabled(true);
                    }
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        panel.add(password);
        next = new JButton("Próximo");
        next.setBounds(590, 150, 120, 25);
        next.setFont(new java.awt.Font("Arial", 0, 14));
        next.setEnabled(false);
        next.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                StepTwoVerification();
            }
        });
        panel.add(next);
        clear = new JButton("Limpar");
        clear.setBounds(450, 150, 120, 25);
        clear.setFont(new java.awt.Font("Arial", 0, 14));
        clear.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                password.setText("");
                typed_password.clear();
            }
        });
        panel.add(clear);
        frame.setSize(800, 280);
        frame.validate();
    }

    public static void initButtons(JPanel panel) {
        char j;
        char[] i = {'A', 'E', 'O'};
        btOp = new JButton[5];
        phonemes = new ArrayList<String>();
        for (j = 'B'; j < 'H'; j++) {
            for (char c : i) {
                if (j == 'E' || j == c) {
                    continue;
                }
                String temp = String.valueOf(j) + String.valueOf(c);
                //System.out.println(temp);
                phonemes.add(temp);
            }

        }
        Collections.shuffle(phonemes);

        for (int k = 0; k < btOp.length; k++) {
            String text = "";
            for (int w = 0; w < 3; w++) {
                if (w != 0) {
                    text += " - ";
                }
                text += phonemes.get(3 * k + w);
            }
            btOp[k] = new JButton(text);
            btOp[k].setBounds(110 + k * 120, 90, 120, 25);


            btOp[k].addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                  handlePhonemeButton(e);
              }
            });
            panel.add(btOp[k]);
        }
    }
    
    private static void updateButtons() {
        Collections.shuffle(phonemes);
        
        for (int k = 0; k < btOp.length; k++) {
            String text = "";
            for (int w = 0; w < 3; w++) {
                if (w != 0) {
                    text += " - ";
                }
                text += phonemes.get(3 * k + w);
            }
            btOp[k].setText(text);
        }
    }

    private static void handlePhonemeButton(ActionEvent e) {
        Object o = e.getSource();
        JButton b = (JButton) o;

        String button_txt = b.getText();
        typed_password.add(button_txt);
        password.setText(password.getText() + "**");
        
        updateButtons();
    }
    
    private static void StepTwoVerification(){
        if(LoginController.checkPassword(typed_password)){
            EventsController.insertNewEvent(EventsModel.AUTENTICACAO_ETAPA_DOIS_ENCERRADA);
            frame.setVisible(false);
            MainMenuView.start(user);
        }
    }
}
