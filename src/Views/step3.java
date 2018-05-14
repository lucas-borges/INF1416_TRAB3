/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.EventsModel;
import Model.UserModel;
import controller.EventsController;
import controller.LoginController;

/**
 *
 * @author joy
 */
public class step3 extends javax.swing.JPanel {

    private UserModel user;
    /**
     * Creates new form step3
     */
    public step3(UserModel user) {
        this.user = user;
        EventsController.insertNewEvent(EventsModel.AUTENTICACAO_ETAPA_TRES_INICIADA, user.getUsername());
        initComponents();
        errorLabel.setText("Erros: "+ user.getPrivate_key_errors());
        pathField.setText("Keys/admin-pkcs8-pem-des.key");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pathField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        passphraseField = new javax.swing.JTextField();
        errorDescLabel = new javax.swing.JLabel();

        pathField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathFieldActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Caminho do Arquivo");

        jLabel2.setText("Chave Secreta");

        errorLabel.setText("errors");

        errorDescLabel.setForeground(new java.awt.Color(200, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorDescLabel)
                    .addComponent(errorLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pathField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(passphraseField))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passphraseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorDescLabel)
                .addGap(8, 8, 8)
                .addComponent(jButton1)
                .addGap(39, 39, 39))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pathFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pathFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        LoginView.setMenuView();
        int verifyResult = LoginController.verifyPrivateKey(pathField.getText(), passphraseField.getText());
        if(verifyResult == 1){
            LoginController.processCorrectPrivateKey();
            EventsController.insertNewEvent(EventsModel.CHAVE_PRIV_OK, user.getUsername());
            EventsController.insertNewEvent(EventsModel.AUTENTICACAO_ETAPA_TRES_ENCERRADA, user.getUsername());
            LoginView.setMenuView();
        }
        else{
            switch (verifyResult) {
                case -1:
                    EventsController.insertNewEvent(EventsModel.CHAVE_PRIV_CAMINHO_INVALIDO, user.getUsername());
                    errorDescLabel.setText("Caminho invalido");
                    break;
                case -2:
                    EventsController.insertNewEvent(EventsModel.CHAVE_PRIV_FRASE_SECRETA_INV, user.getUsername());
                    errorDescLabel.setText("Frase secreta invalida");
                    break;
                case -3:
                    EventsController.insertNewEvent(EventsModel.CHAVE_PRIV_DIG_SIG_INV, user.getUsername());
                    errorDescLabel.setText("Chave privada invalida");
                    break;
            }
            int errors = LoginController.processIncorrectPrivateKey();
            
            errorLabel.setText("Erros: "+ user.getPrivate_key_errors());
            passphraseField.setText("");
            if (errors == 3){
                EventsController.insertNewEvent(EventsModel.ACESSO_BLOQUEADO_ETAPA_DOIS, user.getUsername());
                LoginController.blockUserStepThree();
                EventsController.insertNewEvent(EventsModel.AUTENTICACAO_ETAPA_TRES_ENCERRADA, user.getUsername());
                LoginView.backToStepOne();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorDescLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField passphraseField;
    private javax.swing.JTextField pathField;
    // End of variables declaration//GEN-END:variables
}
