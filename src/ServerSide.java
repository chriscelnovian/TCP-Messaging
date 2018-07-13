import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * @author Chriscel
 */
public class ServerSide extends javax.swing.JFrame {

    /* Initialize Components */
    static Socket socket;
    static ServerSocket serverSocket;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;
    
    /* Creates new form ClientSide */
    public ServerSide() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_server = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        server_messages = new javax.swing.JTextArea();
        txt_server_message = new javax.swing.JTextField();
        btn_send_to_client = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label_server.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_server.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_server.setText("SERVER");

        server_messages.setColumns(20);
        server_messages.setRows(5);
        jScrollPane1.setViewportView(server_messages);

        btn_send_to_client.setText("Send to Client");
        btn_send_to_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_send_to_clientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_server, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_server_message, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_send_to_client, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_server)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_server_message)
                    .addComponent(btn_send_to_client, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* Button Send to Client Handler */
    private void btn_send_to_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_send_to_clientActionPerformed
        try {
            String outgoingMessage = "";
            outgoingMessage = txt_server_message.getText().trim();
            outputStream.writeUTF(outgoingMessage);
            server_messages.setText(server_messages.getText().trim()+"\nYou : " + outgoingMessage);
            txt_server_message.setText("");
        } catch (Exception e){
            System.err.println("Send Message to Client Failed");
        }
    }//GEN-LAST:event_btn_send_to_clientActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerSide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerSide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerSide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerSide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerSide().setVisible(true);
            }
        });
        
        // Incoming Message Initialize
        String incomingMessage = "";
        
        /* Messaging */
        try {
            serverSocket = new ServerSocket(8888);
            socket = serverSocket.accept();
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            
            while (!incomingMessage.equals("Exit")) {
                incomingMessage = inputStream.readUTF();
                server_messages.setText(server_messages.getText().trim()+"\nClient : "+ incomingMessage);
            }
        } catch(Exception e){
            server_messages.setText("Client is offline...");
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_send_to_client;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_server;
    private static javax.swing.JTextArea server_messages;
    private javax.swing.JTextField txt_server_message;
    // End of variables declaration//GEN-END:variables
}
