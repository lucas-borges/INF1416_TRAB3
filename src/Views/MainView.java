/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.EventsDAO;
import Model.Certificate;
import Model.EventModel;
import Model.IndexFile;
import Model.UserModel;
import Model.MyPrivateKey;
import controller.EventsController;
import controller.MainController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
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
import sun.misc.BASE64Encoder;
import sun.security.provider.X509Factory;

/**
 *
 * @author Joyce
 */
public class MainView {

    public static JPanel panel1;
    public static JPanel panel2;
    public static JPanel panel3;
    public static JTextField username;
    public static JTextField password;
    public static JLabel welcome;
    public static JFrame mainFrame;
    public static JLabel username_label;
    public static JLabel path_label;
    public static JLabel password_errors_label;
    public static JButton clear;
    public static JButton next;
    public static JButton[] btOp;
    public static UserModel user;
    public static List<String> typed_password = new ArrayList<String>();
    public static List<String> phonemes = new ArrayList<String>();
    private static JPanel register;

    //  ADMIN: admin@inf1416.puc-rio.br / BACADA
    public static void main(String[] args) throws FileNotFoundException, CertificateException, IOException, NoSuchAlgorithmException {
//        MyPrivateKey priv = new MyPrivateKey("Keys/admin-pkcs8-pem-des.key", "admin");
//        priv.run();
//        Certificate cert = new Certificate("Keys/admin-x509.crt");
//        IndexFile index = new IndexFile("Files", priv.getPrivateKey(), cert.getPublicKey());
//        System.out.println(index.checkPath());
//        index.openEnvelope();
//        index.decryptIndex();
//        System.out.println(index.verifyIndex());
//        index.parseIndexContents();
        

        EventsController.insertNewEvent(EventModel.SISTEMA_INICIADO);
        initComponents();
    }

    private static void initComponents() {
        mainFrame = new JFrame("INF1416_TRABALHO_3");
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
        setStepOne();
    }

    private static void exitProcedure() {
        EventsController.insertNewEvent(EventModel.SISTEMA_ENCERRADO);
        mainFrame.dispose();
        System.exit(0);
    }

    private static void setStepOne() {

        panel1 = new JPanel();
        
        panel1.setLayout(null);
        welcome = new JLabel("Bem Vindo(a)!");
        welcome.setBounds(60, 40, 200, 25);
        welcome.setFont(new java.awt.Font("Arial", 0, 24));
        panel1.add(welcome);
        username_label = new JLabel("Identificação");
        username_label.setBounds(60, 90, 80, 25);
        username_label.setFont(new java.awt.Font("Arial", 0, 14));
        panel1.add(username_label);
        username = new JTextField(20);
        username.setText("admin@inf1416.puc-rio.br");
        username.setBounds(150, 90, 160, 25);
        username.setFont(new java.awt.Font("Arial", 0, 14));
        panel1.add(username);
        clear = new JButton("Próximo");
        clear.setBounds(210, 140, 100, 25);
        clear.setFont(new java.awt.Font("Arial", 0, 14));
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextMouseClicked(e);
            }
        });
        panel1.add(clear);
        mainFrame.add(panel1, BorderLayout.CENTER);
        mainFrame.setSize(400, 250);
        mainFrame.validate();
        mainFrame.setVisible(true);
    }

    private static void nextMouseClicked(ActionEvent e) {
        EventsController.insertNewEvent(EventModel.AUTENTICACAO_ETAPA_UM_INICIADA);
        user = MainController.findUser(username.getText());
        if (user == null) {
            EventsController.insertNewEvent(EventModel.LOGIN_NAO_IDENTIFICADO, username.getText());
            alertInvalidUsername();
        } else if (user.isBlocked()) {
            EventsController.insertNewEvent(EventModel.LOGIN_ACESSO_BLOQUEADO, user.getUsername());
            alertBlockedUser();
        } else {
            EventsController.insertNewEvent(EventModel.LOGIN_ACESSO_LIBERADO, user.getUsername());
            EventsController.insertNewEvent(EventModel.AUTENTICACAO_ETAPA_UM_ENCERRADA);
            mainFrame.remove(panel1);
            setStepTwo();
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

    private static void setStepTwo() {
        
        panel2 = new JPanel();
        EventsController.insertNewEvent(EventModel.AUTENTICACAO_ETAPA_DOIS_INICIADA, user.getUsername());
        panel2.setLayout(null);
        welcome = new JLabel("Bem Vindo(a)!");
        welcome.setBounds(60, 40, 200, 25);
        welcome.setFont(new java.awt.Font("Arial", 0, 24));
        panel2.add(welcome);
        username_label = new JLabel("Senha");
        username_label.setBounds(60, 90, 80, 25);
        username_label.setFont(new java.awt.Font("Arial", 0, 14));
        panel2.add(username_label);
        initButtons(panel2);
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
        panel2.add(password);
        next = new JButton("Próximo");
        next.setBounds(590, 150, 120, 25);
        next.setFont(new java.awt.Font("Arial", 0, 14));
        next.setEnabled(false);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StepTwoVerification();
            }
        });
        panel2.add(next);
        clear = new JButton("Limpar");
        clear.setBounds(450, 150, 120, 25);
        clear.setFont(new java.awt.Font("Arial", 0, 14));
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                password.setText("");
                typed_password.clear();
            }
        });
        panel2.add(clear);
        password_errors_label = new JLabel("Erros: " + user.getPassword_errors());
        password_errors_label.setBounds(60, 190, 80, 25);
        password_errors_label.setFont(new java.awt.Font("Arial", 0, 14));
        panel2.add(password_errors_label);
        mainFrame.add(panel2, BorderLayout.CENTER);
        mainFrame.setSize(800, 280);
        mainFrame.validate();
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

            btOp[k].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handlePhonemeButton(e);
                }
            });
            panel.add(btOp[k]);
        }
    }

    public static void backToStepOne() { //int id
        EventsController.insertNewEvent(EventModel.CHAVE_PRIV_FRASE_SECRETA_INV, user.getUsername());
        user = null;
        mainFrame = new JFrame();
        setStepOne();
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

    private static void StepTwoVerification() {
        if (MainController.checkPassword(typed_password)) {
            MainController.processCorrectPassword();
            EventsController.insertNewEvent(EventModel.SENHA_OK, user.getUsername());
            EventsController.insertNewEvent(EventModel.AUTENTICACAO_ETAPA_DOIS_ENCERRADA, user.getUsername());

            mainFrame.remove(panel2);
            panel3 = new JPanel();
            setStepThree(panel3);

        } else {
            int errors = MainController.processIncorrectPassword();
            switch (errors) {
                case 1:
                    EventsController.insertNewEvent(EventModel.PRIMEIRO_ERRO_SENHA, user.getUsername());
                    password_errors_label.setText("Erros: " + user.getPassword_errors());
                    password.setText("");
                    typed_password.clear();
                    break;
                case 2:
                    EventsController.insertNewEvent(EventModel.SEGUNDO_ERRO_SENHA, user.getUsername());
                    password_errors_label.setText("Erros: " + user.getPassword_errors());
                    password.setText("");
                    typed_password.clear();
                    break;
                case 3:
                    EventsController.insertNewEvent(EventModel.TERCEIRO_ERRO_SENHA, user.getUsername());
                    EventsController.insertNewEvent(EventModel.ACESSO_BLOQUEADO_ETAPA_DOIS, user.getUsername());
                    MainController.blockUserStepTwo();
                    EventsController.insertNewEvent(EventModel.AUTENTICACAO_ETAPA_DOIS_ENCERRADA, user.getUsername());
                    user = null;
                    mainFrame.remove(panel2);
                    
                    setStepOne();
                    break;
            }
        }
    }

    private static void setStepThree(JPanel panel3) {
        panel3 = new KeyView(user);
        mainFrame.add(panel3, BorderLayout.CENTER);
        mainFrame.setSize(400, 250);
        mainFrame.validate();
    }

    public static void setMenuView() {
        mainFrame.setVisible(false);
        mainFrame = new JFrame();
        MenuView.start(user);
    }

    static void setRegisterView() {
        EventsController.insertNewEvent(EventModel.OPCAO_UM_MENU_PRINCIPAL, user.getUsername());
        mainFrame = new JFrame();
        register = new RegisterView(user);
        mainFrame.add(register, BorderLayout.CENTER);
        mainFrame.setSize(400, 500);
        mainFrame.validate();
        mainFrame.setVisible(true);
    }
    
    static void setFilesList(){
        EventsController.insertNewEvent(EventModel.TELA_CONSULTA_ARQUIVOS_SECRETOS, user.getUsername());
        mainFrame = new JFrame();
        register = new FilesListView();
        mainFrame.add(register, BorderLayout.CENTER);
        mainFrame.setSize(600, 500);
        mainFrame.validate();
        mainFrame.setVisible(true);
    }
    
    static void setChangePsswrd(){
        EventsController.insertNewEvent(EventModel.ALT_TELA_ALTERACAO, user.getUsername());
        mainFrame = new JFrame();
        register = new ChangePsswrd();
        mainFrame.add(register, BorderLayout.CENTER);
        mainFrame.setSize(600, 500);
        mainFrame.validate();
        mainFrame.setVisible(true);
    }
}
