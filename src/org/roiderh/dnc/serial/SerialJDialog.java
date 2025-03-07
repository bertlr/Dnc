/*
 * Copyright (C) 2014 by Herbert Roider <herbert.roider@utanet.at>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.roiderh.dnc.serial;

import java.awt.Color;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import jssc.SerialPortException;

/**
 *
 * @author Herbert Roider <herbert.roider@utanet.at>
 */
public class SerialJDialog extends javax.swing.JDialog implements SerialPortEventListener, ActionListener {

    private SerialPort serialPort;
    private javax.swing.text.Document document;
    /**
     * If the dialog is for receive data, this must be true, for sending false:
     */
    public boolean receive = true;
    private Timer t = new Timer(400, this);
    /**
     * count of the received bytes
     */
    private int received_count = 0;
    /**
     * buffer with the text ready to sent
     */
    private byte[] sendBuffer;
    /**
     * count of the already sent bytes
     */
    private int current_send_pos = 0;
    private boolean is_sending = false;
    private JTextComponent textComponent;

    boolean cts_on = true;

    /**
     * The serial port must be opened before call this function.
     *
     * @param s the port
     * @param doc the current document in the editor
     * @param r for receive from serial port, set this to true.
     * @param pre_string this is sent before the document will be sent, to send delete command
     */
    public void setPort(SerialPort s, JTextComponent ed, javax.swing.text.Document doc, boolean r, String pre_string) {
        this.serialPort = s;
        this.jTextAreaReceive.setText("");
        this.document = doc;
        this.textComponent = ed;
        this.receive = r;
        this.received_count = 0;
        this.current_send_pos = 0;
        this.jLabelCts.setForeground(Color.LIGHT_GRAY);
        jProgressBarSent.setMaximum(100);
        jProgressBarSent.setMinimum(0);
        this.jProgressBarSent.setValue(0);
        this.jProgressBarSent.setStringPainted(true);
        this.jProgressBarSent.setVisible(false);
        if (this.receive == false) {
            String doc_string = pre_string;
            try {
                doc_string += this.document.getText(0, this.document.getLength());
            } catch (Exception ex) {
                System.out.println("cannot read document: " + ex.getMessage());
            }
            doc_string = doc_string.replace("\r", "");
            doc_string = doc_string.replace("\n", "\r\n");
            this.sendBuffer = doc_string.getBytes();
            this.jProgressBarSent.setVisible(true);
        }
        t.start();

    }

    /**
     * Creates new form NewJDialog
     */
    public SerialJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.serialPort = null;
        //this.receivedText = new String();
        this.document = null;
        //this.jLabelCts.setForeground(Color.LIGHT_GRAY);

        //Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                clearAndHide();

            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub
                System.out.println("Window activated");
                if (receive == false) {
                    //sendString();
                }
                //super.windowActivated(e);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReceive = new javax.swing.JTextArea();
        jLabelCts = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();
        jProgressBarSent = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(SerialJDialog.class, "SerialJDialog.title")); // NOI18N

        jTextAreaReceive.setEditable(false);
        jTextAreaReceive.setColumns(20);
        jTextAreaReceive.setRows(5);
        jScrollPane1.setViewportView(jTextAreaReceive);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCts, org.openide.util.NbBundle.getMessage(SerialJDialog.class, "SerialJDialog.jLabelCts.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonClose, org.openide.util.NbBundle.getMessage(SerialJDialog.class, "SerialJDialog.jButtonClose.text")); // NOI18N
        jButtonClose.setToolTipText(org.openide.util.NbBundle.getMessage(SerialJDialog.class, "SerialJDialog.jButtonClose.toolTipText")); // NOI18N
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCts)
                            .addComponent(jButtonClose))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jProgressBarSent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarSent, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButtonClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
            // TODO add your handling code here:
            this.clearAndHide();

        }//GEN-LAST:event_jButtonCloseActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SerialJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SerialJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SerialJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SerialJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SerialJDialog dialog = new SerialJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isTXEMPTY()) {
//            int val = event.getEventValue();
//            System.out.println("SerialEvent TXEMPTY:" + Integer.toString(val));
//            if (this.receive == false) {
//                this.jLabelCts.setForeground(Color.red);
//                System.out.println("serialEvent pos: " + this.current_send_pos);
//                //this.sendString();
//                this.textComponent.select(0, this.current_send_pos);
////
//            }
        } else if (event.isRXCHAR()) {//If data is available
            //if (this.receive) {
            // The color shows incomming data
            this.jLabelCts.setForeground(Color.red);
            int bytes = event.getEventValue();
            System.out.println("SerialEvent RXCHAR anz=" + bytes);
            try {
                byte buffer[] = serialPort.readBytes(bytes);
                String readed = "";

                for (int i = 0; i < buffer.length; i++) {
                    // remove characters with code 0, which comes from cnc machine
                    if (buffer[i] == 0) {
                        continue;
                    }
                    //if (SerialNativeInterface.getOsType() == SerialNativeInterface.OS_LINUX || SerialNativeInterface.getOsType() == SerialNativeInterface.OS_MAC_OS_X) {
                    if (buffer[i] == 13) { // "\r"
                        continue;
                    }

                    //}
                    readed += (char) buffer[i];
                }
                //System.out.println("readed:"+readed);
                if (this.receive) {
                    this.document.insertString(this.document.getLength(), readed, null);
                    this.received_count += readed.length();
                }
            } catch (BadLocationException | SerialPortException ex) {
                System.out.println(ex);
            }

        } else if (event.isCTS()) {//If CTS (clear to send) line has changed state
            if (event.getEventValue() == 1) {//If line is ON
                this.cts_on = true;
                System.out.println("CTS on");
            } else {
                this.cts_on = false; // receiver want to wait
                System.out.println("CTS off");
            }
        } else if (event.isDSR()) {///If DSR (data set ready) line has changed state
            if (event.getEventValue() == 1) {//If line is ON
                System.out.println("DSR on");
            } else {
                System.out.println("DSR off");
            }
        } else if (event.isBREAK()) {
//            System.out.println("break");
//
        } else if (event.isERR()) {
            System.out.println("Error " + event.getEventValue());
        } else if (event.isRING()) {
            //System.out.println("Ring " + event.getEventValue());
        } else if (event.isRLSD()) {
            //System.out.println("RLSD " + event.getEventValue());
        } else if (event.isRXFLAG()) {
            //System.out.println("RXFlag " + event.getEventValue());
        }
    }

    /**
     * Called by a timer every second. switch the color of the Text to green
     * every second. This shows there is no incomming data.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t) {

            this.jLabelCts.setForeground(Color.LIGHT_GRAY);
            if (this.receive) {
                this.jTextAreaReceive.setText("received: " + Integer.toString(this.received_count));
            } else {
                this.sendString();
                String text = "sent: " + Integer.toString(this.current_send_pos) + " Bytes";
                if (this.current_send_pos < this.sendBuffer.length) {
                    int progress = (int) Math.round(100.0 * this.current_send_pos / this.sendBuffer.length);
                    if (progress >= 100) {
                        progress = 99;
                    }
                    text += " ( " + Integer.toString(progress) + " % )";
                    this.jProgressBarSent.setValue(progress);
                    this.jProgressBarSent.setString(Integer.toString(progress) + " %");
                } else {
                    text += " ( 100 % )";
                    this.jProgressBarSent.setValue(100);
                    this.jProgressBarSent.setString("100 %");
                }
                this.jTextAreaReceive.setText(text);

            }

        }

    }

    /**
     * This stop the timer and hides it.
     */
    public void clearAndHide() {
        t.stop();
        // read bytes in the pipe to clear the pipe (bringt aber nichts):
//        try {
//            this.serialPort.readBytes();
//        } catch (SerialPortException spe) {
//            System.out.println(spe.getMessage());
//        }
        setVisible(false);
    }

    /**
     * Sends a line.
     *
     * @param begin position in the string to send
     * @return
     */
    private int sendString() {
        if (this.is_sending) {
            System.out.println("unnoetiges Timerevent");
            return 0;
        }
        if (cts_on == false) {
            this.jLabelCts.setForeground(Color.yellow);
            //return this.current_send_pos
        }
        this.is_sending = true;
        try {
            String sends = "";
            int lines = 0;
            boolean succeed = false;

            for (int i = this.current_send_pos; i < this.sendBuffer.length; i++) {
                sends += (char) this.sendBuffer[i];
                //this.current_send_pos = i;
                if (this.sendBuffer[i] == (char) 10) {
                    // reach the last character or max. lines
                    if (this.sendBuffer.length - 1 == i || lines >= 10) {

                        lines = 0;
                        //System.out.println("writeByte");
                        this.jLabelCts.setForeground(Color.red);
                        succeed = this.serialPort.writeString(sends);
                        if (succeed == false) {
                            System.out.println("Error at sendString");
                            this.jTextAreaReceive.setText("Error at sending");
                            return 0;
                        }
                        sends = "";
                        this.current_send_pos = i;
                        this.current_send_pos++;// set to the next index
                        break;

                    }

                    lines++;

                }
            }
            if (sends.length() > 0) {
                this.serialPort.writeString(sends);
                this.current_send_pos += sends.length();
                sends = "";
            }

            this.is_sending = false;
            //this.textComponent.select(0, this.current_send_pos);

        } catch (SerialPortException ex) {
            System.out.println("Error at writeByte: " + ex.getMessage());
        }

        return this.current_send_pos;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JLabel jLabelCts;
    private javax.swing.JProgressBar jProgressBarSent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaReceive;
    // End of variables declaration//GEN-END:variables
}
