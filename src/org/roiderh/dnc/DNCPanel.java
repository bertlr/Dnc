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
package org.roiderh.dnc;

import java.util.ArrayList;
import java.util.Arrays;
import jssc.SerialPort;
import org.openide.util.NbPreferences;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;
import jssc.SerialNativeInterface;
import jssc.SerialPortList;

public final class DNCPanel extends javax.swing.JPanel {

        private final DNCOptionsPanelController controller;
        //private Properties properties = null;
        private int current_index = -1;
        private ArrayList<Option> baudrates;
        private ArrayList<Option> parity;
        private ArrayList<Option> port;
        private ArrayList<Option> flowcontrol;
        private ArrayList<Option> linebreak;
        private ArrayList<Option> databits;
        private ArrayList<Option> stopbits;

        DNCPanel(DNCOptionsPanelController controller) {
                this.controller = controller;
                initComponents();
                // TODO listen to changes in form fields and call controller.changed()
                //TablePropertiesModel tm = new TablePropertiesModel();
                //this.jTableProperties.setModel(tm);
                //ListModel<Properties> lm = new

                Integer[] baud = {SerialPort.BAUDRATE_110,
                        SerialPort.BAUDRATE_300,
                        SerialPort.BAUDRATE_600,
                        SerialPort.BAUDRATE_1200,
                        SerialPort.BAUDRATE_4800,
                        SerialPort.BAUDRATE_9600,
                        SerialPort.BAUDRATE_14400,
                        SerialPort.BAUDRATE_19200,
                        SerialPort.BAUDRATE_38400,
                        SerialPort.BAUDRATE_57600,
                        SerialPort.BAUDRATE_115200,
                        SerialPort.BAUDRATE_128000,
                        SerialPort.BAUDRATE_256000
                };
                this.baudrates = new ArrayList<Option>();
                for (int i = 0; i < baud.length; i++) {
                        this.baudrates.add(new Option(baud[i]));
                }
                this.jComboBaud.setRenderer(new ComboBoxOptionRenderer());
                for (int i = 0; i < baud.length; i++) {
                        this.jComboBaud.addItem(this.baudrates.get(i));
                }

                this.parity = new ArrayList<Option>();
                this.parity.add(new Option(SerialPort.PARITY_NONE, "none"));
                this.parity.add(new Option(SerialPort.PARITY_ODD, "odd"));
                this.parity.add(new Option(SerialPort.PARITY_EVEN, "even"));
                this.parity.add(new Option(SerialPort.PARITY_MARK, "mark"));
                this.parity.add(new Option(SerialPort.PARITY_SPACE, "space"));
                this.jComboParity.setRenderer(new ComboBoxOptionRenderer());
                for (int i = 0; i < this.parity.size(); i++) {
                        this.jComboParity.addItem(this.parity.get(i));

                }

                this.databits = new ArrayList<Option>();
                this.databits.add(new Option(SerialPort.DATABITS_5, "5"));
                this.databits.add(new Option(SerialPort.DATABITS_6, "6"));
                this.databits.add(new Option(SerialPort.DATABITS_7, "7"));
                this.databits.add(new Option(SerialPort.DATABITS_8, "8"));
                this.jComboDatabits.setRenderer(new ComboBoxOptionRenderer());
                for (int i = 0; i < this.databits.size(); i++) {
                        this.jComboDatabits.addItem(this.databits.get(i));

                }

                this.stopbits = new ArrayList<Option>();
                this.stopbits.add(new Option(SerialPort.STOPBITS_1, "1"));
                this.stopbits.add(new Option(SerialPort.STOPBITS_1_5, "1.5"));
                this.stopbits.add(new Option(SerialPort.STOPBITS_2, "2"));
                this.jComboStopbits.setRenderer(new ComboBoxOptionRenderer());
                for (int i = 0; i < this.stopbits.size(); i++) {
                        this.jComboStopbits.addItem(this.stopbits.get(i));

                }

                this.flowcontrol = new ArrayList<Option>();
                this.flowcontrol.add(new Option(Properties.FLOWCONTROL_NONE, "none"));
                this.flowcontrol.add(new Option(Properties.FLOWCONTROL_RTSCTS, "Hardware (RTS/CTS)"));
                this.flowcontrol.add(new Option(Properties.FLOWCONTROL_XONXOFF, "Software (XON/XOFF)"));
                this.flowcontrol.add(new Option(Properties.FLOWCONTROL_RTXCTSXONXOFF, "Hardware + Software (RTS/CTS + XON/XOFF)"));

                this.jComboFlowcontrol.setRenderer(new ComboBoxOptionRenderer());
                for (int i = 0; i < this.flowcontrol.size(); i++) {
                        this.jComboFlowcontrol.addItem(this.flowcontrol.get(i));

                }

                this.linebreak = new ArrayList<Option>();
                this.linebreak.add(new Option(Properties.LINEBREAK_CRLF, "Cr+Lf"));
                this.linebreak.add(new Option(Properties.LINEBREAK_LF, "Lf"));
                this.linebreak.add(new Option(Properties.LINEBREAK_CR, "Cr"));
                this.jComboLinebreak.setRenderer(new ComboBoxOptionRenderer());
                for (int i = 0; i < this.linebreak.size(); i++) {
                        this.jComboLinebreak.addItem(this.linebreak.get(i));

                }

        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLayeredPane2 = new javax.swing.JLayeredPane();
                jLayeredPane1 = new javax.swing.JLayeredPane();
                jButtonNew = new javax.swing.JButton();
                jButtonRemove = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                ListProperties = new javax.swing.JList();
                PanelProperties = new javax.swing.JLayeredPane();
                jLayeredPane3 = new javax.swing.JLayeredPane();
                jLabel1 = new javax.swing.JLabel();
                jTextName = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jComboBaud = new javax.swing.JComboBox();
                jLabel3 = new javax.swing.JLabel();
                jComboParity = new javax.swing.JComboBox();
                jLabel4 = new javax.swing.JLabel();
                jComboPort = new javax.swing.JComboBox();
                jLabel5 = new javax.swing.JLabel();
                jComboFlowcontrol = new javax.swing.JComboBox();
                jLabel6 = new javax.swing.JLabel();
                jComboLinebreak = new javax.swing.JComboBox();
                jLabel7 = new javax.swing.JLabel();
                jComboDatabits = new javax.swing.JComboBox();
                jLabel8 = new javax.swing.JLabel();
                jComboStopbits = new javax.swing.JComboBox();
                filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 3), new java.awt.Dimension(0, 200), new java.awt.Dimension(32767, 3));

                addComponentListener(new java.awt.event.ComponentAdapter() {
                        public void componentShown(java.awt.event.ComponentEvent evt) {
                                formComponentShown(evt);
                        }
                });
                setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

                jLayeredPane2.setLayout(new javax.swing.BoxLayout(jLayeredPane2, javax.swing.BoxLayout.Y_AXIS));

                jLayeredPane1.setLayout(new javax.swing.BoxLayout(jLayeredPane1, javax.swing.BoxLayout.X_AXIS));

                org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jButtonNew.text")); // NOI18N
                jButtonNew.setToolTipText(org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jButtonNew.toolTipText")); // NOI18N
                jButtonNew.setFocusable(false);
                jButtonNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButtonNew.setMaximumSize(new java.awt.Dimension(100, 25));
                jButtonNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                jButtonNew.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonNewActionPerformed(evt);
                        }
                });
                jLayeredPane1.add(jButtonNew);
                jButtonNew.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jButtonNew.AccessibleContext.accessibleName")); // NOI18N

                org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jButtonRemove.text")); // NOI18N
                jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonRemoveActionPerformed(evt);
                        }
                });
                jLayeredPane1.add(jButtonRemove);

                jLayeredPane2.add(jLayeredPane1);

                jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 131));

                ListProperties.setModel(new ListPropertiesModel());
                ListProperties.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                ListProperties.addComponentListener(new java.awt.event.ComponentAdapter() {
                        public void componentShown(java.awt.event.ComponentEvent evt) {
                                ListPropertiesComponentShown(evt);
                        }
                });
                ListProperties.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                                ListPropertiesValueChanged(evt);
                        }
                });
                jScrollPane1.setViewportView(ListProperties);

                jLayeredPane2.add(jScrollPane1);

                add(jLayeredPane2);

                PanelProperties.setLayout(new javax.swing.BoxLayout(PanelProperties, javax.swing.BoxLayout.Y_AXIS));

                jLayeredPane3.setLayout(new java.awt.GridLayout(8, 2));

                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jLabel1.text")); // NOI18N
                jLayeredPane3.add(jLabel1);

                jTextName.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                jTextNameCaretUpdate(evt);
                        }
                });
                jLayeredPane3.add(jTextName);

                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jLabel2.text")); // NOI18N
                jLayeredPane3.add(jLabel2);

                jComboBaud.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboBaudItemStateChanged(evt);
                        }
                });
                jLayeredPane3.add(jComboBaud);

                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jLabel3.text")); // NOI18N
                jLayeredPane3.add(jLabel3);

                jComboParity.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboParityItemStateChanged(evt);
                        }
                });
                jLayeredPane3.add(jComboParity);

                jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jLabel4.text")); // NOI18N
                jLayeredPane3.add(jLabel4);

                jComboPort.setEditable(true);
                jComboPort.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboPortItemStateChanged(evt);
                        }
                });
                jLayeredPane3.add(jComboPort);

                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jLabel5.text")); // NOI18N
                jLayeredPane3.add(jLabel5);

                jComboFlowcontrol.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboFlowcontrolItemStateChanged(evt);
                        }
                });
                jLayeredPane3.add(jComboFlowcontrol);

                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jLabel6.text")); // NOI18N
                jLayeredPane3.add(jLabel6);

                jComboLinebreak.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboLinebreakItemStateChanged(evt);
                        }
                });
                jLayeredPane3.add(jComboLinebreak);

                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jLabel7.text")); // NOI18N
                jLayeredPane3.add(jLabel7);

                jComboDatabits.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboDatabitsItemStateChanged(evt);
                        }
                });
                jLayeredPane3.add(jComboDatabits);

                jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(DNCPanel.class, "DNCPanel.jLabel8.text")); // NOI18N
                jLayeredPane3.add(jLabel8);

                jComboStopbits.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboStopbitsItemStateChanged(evt);
                        }
                });
                jLayeredPane3.add(jComboStopbits);

                PanelProperties.add(jLayeredPane3);
                PanelProperties.add(filler1);

                add(PanelProperties);
                PanelProperties.getAccessibleContext().setAccessibleParent(ListProperties);
        }// </editor-fold>//GEN-END:initComponents

        private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
                // TODO add your handling code here:
                System.out.println("New");
                Properties p = new Properties();
                p.name = "New config";
                ListPropertiesModel lm = (ListPropertiesModel) this.ListProperties.getModel();
                lm.add_row(p);
                this.ListProperties.setSelectedIndex(lm.getSize() - 1);

        }//GEN-LAST:event_jButtonNewActionPerformed

        private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
                // TODO add your handling code here:
//                System.out.println("remove Click");
                ListPropertiesModel lm = (ListPropertiesModel) this.ListProperties.getModel();

                if (lm.getSize() <= 0) {
                        return;
                }
                int index = this.ListProperties.getSelectedIndex();
                if (index < 0) {
                        return;
                }
                lm.delete_row(index);
                if (index > 0) {
                        this.ListProperties.setSelectedIndex(index-1);
                } else {
                        this.current_index = -1;
                }


        }//GEN-LAST:event_jButtonRemoveActionPerformed

        private void ListPropertiesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListPropertiesValueChanged
                // TODO add your handling code here:
                System.out.println("changed");

                int new_index = this.ListProperties.getSelectedIndex();
                if (new_index < 0) {
                        return;
                }
                if (this.current_index == new_index) {
                        return;
                }
                this.current_index = new_index;
                Properties p = ((ListPropertiesModel) this.ListProperties.getModel()).get_row(this.current_index);

                this.jTextName.setText(p.name);
                this.jComboPort.setSelectedItem(p.port);

                this.setSelectedComboBoxItem(p.baud, jComboBaud, this.baudrates);
                this.setSelectedComboBoxItem(p.parity, jComboParity, this.parity);
                this.setSelectedComboBoxItem(p.flowcontrol, jComboFlowcontrol, this.flowcontrol);
                this.setSelectedComboBoxItem(p.linebreak, jComboLinebreak, this.linebreak);
                this.setSelectedComboBoxItem(p.databits, jComboDatabits, this.databits);
                this.setSelectedComboBoxItem(p.stopbits, jComboStopbits, this.stopbits);


        }//GEN-LAST:event_ListPropertiesValueChanged

        private void jTextNameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextNameCaretUpdate
                // TODO add your handling code here:
                System.out.println("caret text changed");
                if (this.current_index < 0) {
                        return;
                }
                Properties p = ((ListPropertiesModel) this.ListProperties.getModel()).get_row(this.current_index);
                if (p == null) {
                        return;
                }
                p.name = this.jTextName.getText().trim();

                ((ListPropertiesModel) this.ListProperties.getModel()).update_row(this.current_index, p);


        }//GEN-LAST:event_jTextNameCaretUpdate

        private void jComboBaudItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBaudItemStateChanged
                // TODO add your handling code here:
                this.setListPropertiesModelFromComboBox("baud", (javax.swing.JComboBox) evt.getSource());
        }//GEN-LAST:event_jComboBaudItemStateChanged

        private void jComboParityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboParityItemStateChanged
                // TODO add your handling code here:
                this.setListPropertiesModelFromComboBox("parity", (javax.swing.JComboBox) evt.getSource());
        }//GEN-LAST:event_jComboParityItemStateChanged

        private void jComboPortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboPortItemStateChanged
                // TODO add your handling code here:
                if (this.current_index < 0) {
                        return;
                }
                Properties p = ((ListPropertiesModel) this.ListProperties.getModel()).get_row(this.current_index);
                if (p == null) {
                        return;
                }
                p.port = (String) this.jComboPort.getSelectedItem();
                ((ListPropertiesModel) this.ListProperties.getModel()).update_row(this.current_index, p);


        }//GEN-LAST:event_jComboPortItemStateChanged

        private void jComboFlowcontrolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboFlowcontrolItemStateChanged
                // TODO add your handling code here:
                this.setListPropertiesModelFromComboBox("flowcontrol", (javax.swing.JComboBox) evt.getSource());

        }//GEN-LAST:event_jComboFlowcontrolItemStateChanged

        private void jComboLinebreakItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboLinebreakItemStateChanged
                // TODO add your handling code here:
                this.setListPropertiesModelFromComboBox("linebreak", (javax.swing.JComboBox) evt.getSource());

        }//GEN-LAST:event_jComboLinebreakItemStateChanged

        private void jComboDatabitsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboDatabitsItemStateChanged
                // TODO add your handling code here:
                this.setListPropertiesModelFromComboBox("databits", (javax.swing.JComboBox) evt.getSource());

        }//GEN-LAST:event_jComboDatabitsItemStateChanged

        private void jComboStopbitsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboStopbitsItemStateChanged
                // TODO add your handling code here:
                this.setListPropertiesModelFromComboBox("stopbits", (javax.swing.JComboBox) evt.getSource());

        }//GEN-LAST:event_jComboStopbitsItemStateChanged

        private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

        }//GEN-LAST:event_formComponentShown

        private void ListPropertiesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ListPropertiesComponentShown
                // TODO add your handling code here:
        }//GEN-LAST:event_ListPropertiesComponentShown

        void load() {
                // TODO read settings and initialize GUI
                // Example:        
                // someCheckBox.setSelected(Preferences.userNodeForPackage(CoolOptionsPanel.class).getBoolean("someFlag", false));
                // or for org.openide.util with API spec. version >= 7.4:
                // someCheckBox.setSelected(NbPreferences.forModule(CoolOptionsPanel.class).getBoolean("someFlag", false));
                // or:
                // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
                //String s = NbPreferences.forModule(CoolOptionsPanel.class).get("someFlag", "leer");

                this.jComboPort.removeAllItems();
                String[] portNames = null;
                if (SerialNativeInterface.getOsType() == SerialNativeInterface.OS_LINUX) {
                        portNames = SerialPortList.getPortNames("/dev/", Pattern.compile("tty."));

                } else if (SerialNativeInterface.getOsType() == SerialNativeInterface.OS_MAC_OS_X) {
                        portNames = SerialPortList.getPortNames("/dev/", Pattern.compile("tty."));

                } else if (SerialNativeInterface.getOsType() == SerialNativeInterface.OS_SOLARIS) {
                        portNames = SerialPortList.getPortNames("/dev/", Pattern.compile("tty."));

                } else if (SerialNativeInterface.getOsType() == SerialNativeInterface.OS_WINDOWS) {
                        portNames = SerialPortList.getPortNames("", Pattern.compile("COM."));

                }
                for (int i = 0; i < portNames.length; i++) {
                        this.jComboPort.addItem(portNames[i]);
                }

                int item_size = ((ListPropertiesModel) this.ListProperties.getModel()).getSize();
                for (int i = 0; i < item_size; i++) {
                        ((ListPropertiesModel) this.ListProperties.getModel()).delete_row(0);
                }

                ArrayList<Properties> properties = DNCPanel.readPreferences();
                if (properties == null) {
                        return;
                }
                for (int i = 0; i < properties.size(); i++) {
                        ((ListPropertiesModel) this.ListProperties.getModel()).add_row(properties.get(i));
                }

        }

        void store() {
                // TODO store modified settings
                // Example:
                // Preferences.userNodeForPackage(CoolOptionsPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
                // or for org.openide.util with API spec. version >= 7.4:
                // NbPreferences.forModule(CoolOptionsPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
                // or:
                // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());

                ListPropertiesModel lm = (ListPropertiesModel) this.ListProperties.getModel();
                for (Integer i = 0; i < lm.getSize(); i++) {
                        String config_nr = i.toString();
                        Properties p = (Properties) lm.get_row(i);
                        System.out.println("store: " + config_nr + "/name = " + p.name);
                        NbPreferences.forModule(DNCPanel.class).put(config_nr + "/uid", p.uid.toString());
                        NbPreferences.forModule(DNCPanel.class).put(config_nr + "/name", p.name);
                        NbPreferences.forModule(DNCPanel.class).put(config_nr + "/port", p.port);
                        NbPreferences.forModule(DNCPanel.class).putInt(config_nr + "/baud", p.baud);
                        NbPreferences.forModule(DNCPanel.class).putInt(config_nr + "/parity", p.parity);
                        NbPreferences.forModule(DNCPanel.class).putInt(config_nr + "/databits", p.databits);
                        NbPreferences.forModule(DNCPanel.class).putInt(config_nr + "/stopbits", p.stopbits);
                        NbPreferences.forModule(DNCPanel.class).putInt(config_nr + "/flowcontrol", p.flowcontrol);
                        NbPreferences.forModule(DNCPanel.class).putInt(config_nr + "/linebreak", p.linebreak);

                }
                //NbPreferences.forModule(DNCPanel.class).put("someFlag/teststring", "stringvalue");
                Preferences prefs = NbPreferences.forModule(DNCPanel.class);
                System.out.println(prefs.absolutePath());

                //SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
        }

        boolean valid() {
                // TODO check whether form is consistent and complete
                return true;
        }

        private void setSelectedComboBoxItem(int option_number, javax.swing.JComboBox c, ArrayList<Option> a) {
                for (int i = 0; i < a.size(); i++) {
                        if (a.get(i).number == option_number) {
                                c.setSelectedItem(a.get(i));
                                return;
                        }
                }

        }

        private void setListPropertiesModelFromComboBox(String name, javax.swing.JComboBox c) {
                if (this.current_index < 0) {
                        return;
                }
                Properties p = ((ListPropertiesModel) this.ListProperties.getModel()).get_row(this.current_index);
                if (p == null) {
                        return;
                }
                int option_number = ((Option) c.getSelectedItem()).number;
                if (name == "parity") {
                        p.parity = option_number;
                } else if (name == "baud") {
                        p.baud = option_number;
                } else if (name == "linebreak") {
                        p.linebreak = option_number;
                } else if (name == "flowcontrol") {
                        p.flowcontrol = option_number;
                } else if (name == "databits") {
                        p.databits = option_number;
                } else if (name == "stopbits") {
                        p.stopbits = option_number;
                }

                ((ListPropertiesModel) this.ListProperties.getModel()).update_row(this.current_index, p);

        }

        public static ArrayList<Properties> readPreferences() {
                ArrayList<Properties> properties = null;
                Preferences prefs = NbPreferences.forModule(DNCPanel.class);
                System.out.println("readPreferences()");
                System.out.println(prefs.absolutePath());
                try {
                        String[] names = prefs.keys();
                        System.out.println("Eintraege anz. = " + names.length);
                        properties = new ArrayList<Properties>();
                        for (Integer i = 0; i < 10; i++) {
                                //System.out.println(names[i]);
                                String config_nr = i.toString();
                                if (Arrays.asList(names).contains(config_nr + "/name")) {
                                        System.out.println(config_nr + " exitsts");
                                } else {
                                        break;
                                }

                                Properties p = new Properties();
                                p.uid = java.util.UUID.fromString(prefs.get(config_nr + "/uid", ""));
                                p.name = prefs.get(config_nr + "/name", "<leer>");
                                p.port = prefs.get(config_nr + "/port", "");
                                p.baud = prefs.getInt(config_nr + "/baud", SerialPort.BAUDRATE_9600);
                                p.parity = prefs.getInt(config_nr + "/parity", SerialPort.PARITY_NONE);
                                p.databits = prefs.getInt(config_nr + "/databits", SerialPort.DATABITS_8);
                                p.stopbits = prefs.getInt(config_nr + "/stopbits", SerialPort.STOPBITS_1);
                                p.flowcontrol = prefs.getInt(config_nr + "/flowcontrol", Properties.FLOWCONTROL_NONE);
                                p.linebreak = prefs.getInt(config_nr + "/linebreak", Properties.LINEBREAK_CRLF);
                                System.out.println(config_nr + "/name = " + p.name);
                                properties.add(p);
                                //((ListPropertiesModel) this.ListProperties.getModel()).add_row(p);

                        }

                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
                return properties;

        }
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JList ListProperties;
        private javax.swing.JLayeredPane PanelProperties;
        private javax.swing.Box.Filler filler1;
        private javax.swing.JButton jButtonNew;
        private javax.swing.JButton jButtonRemove;
        private javax.swing.JComboBox jComboBaud;
        private javax.swing.JComboBox jComboDatabits;
        private javax.swing.JComboBox jComboFlowcontrol;
        private javax.swing.JComboBox jComboLinebreak;
        private javax.swing.JComboBox jComboParity;
        private javax.swing.JComboBox jComboPort;
        private javax.swing.JComboBox jComboStopbits;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLayeredPane jLayeredPane1;
        private javax.swing.JLayeredPane jLayeredPane2;
        private javax.swing.JLayeredPane jLayeredPane3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextField jTextName;
        // End of variables declaration//GEN-END:variables
}
