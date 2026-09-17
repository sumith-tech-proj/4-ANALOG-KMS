package obuits;

import com.pi4j.io.gpio.PinState;
import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import static obuits.clsDefines.DIG_INPUT_NONE;
import static obuits.MainFrmIts.DigInp1CheckState;
import static obuits.MainFrmIts.DigInp2CheckState;
import static obuits.MainFrmIts.DigInp3CheckState;
import static obuits.MainFrmIts.DigInp4CheckState;
import static obuits.clsDefines.usb_filepath;

public class PanInputOutput extends javax.swing.JPanel {

    public PanInputOutput() {
        initComponents();

        if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_NONE) {
            this.cmbDigInput1Name.setSelectedIndex(0);
            chkVideoDi1Upload.setVisible(false);
            chkVideoDi1Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_SOS) {
            this.cmbDigInput1Name.setSelectedIndex(1);
            chkVideoDi1Upload.setVisible(false);
            chkVideoDi1Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            this.cmbDigInput1Name.setSelectedIndex(2);
            chkVideoDi1Upload.setVisible(true);
            if (clsSharedVariables.getVideoDig1FtpUploadEnable() == true) {
                chkVideoDi1Upload.setSelected(true);
                // clsSharedVariables.setVideoDig1FtpUploadEnable(true);
            } else {
                chkVideoDi1Upload.setSelected(false);
                clsSharedVariables.setVideoDig1FtpUploadEnable(false);
            }
        } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
            this.cmbDigInput1Name.setSelectedIndex(3);
            chkVideoDi1Upload.setVisible(false);
            chkVideoDi1Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
            this.cmbDigInput1Name.setSelectedIndex(4);
            chkVideoDi1Upload.setVisible(false);
            chkVideoDi1Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_LEFTARROW) {
            this.cmbDigInput1Name.setSelectedIndex(5);
            chkVideoDi1Upload.setVisible(false);
            chkVideoDi1Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
            this.cmbDigInput1Name.setSelectedIndex(6);
            chkVideoDi1Upload.setVisible(false);
            chkVideoDi1Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_DOORCAM) {
            this.cmbDigInput1Name.setSelectedIndex(7);
            chkVideoDi1Upload.setVisible(false);
            chkVideoDi1Upload.setSelected(false);
        }

        if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_NONE) {
            this.cmbDigInput2Name.setSelectedIndex(0);
            chkVideoDi2Upload.setVisible(false);
            chkVideoDi2Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_SOS) {
            this.cmbDigInput2Name.setSelectedIndex(1);
            chkVideoDi2Upload.setVisible(false);
            chkVideoDi2Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            this.cmbDigInput2Name.setSelectedIndex(2);
            chkVideoDi2Upload.setVisible(true);
            if (clsSharedVariables.getVideoDig2FtpUploadEnable() == true) {
                chkVideoDi2Upload.setSelected(true);
                clsSharedVariables.setVideoDig2FtpUploadEnable(true);
            } else {
                chkVideoDi2Upload.setSelected(false);
                clsSharedVariables.setVideoDig2FtpUploadEnable(false);
            }
        } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
            this.cmbDigInput2Name.setSelectedIndex(3);
            chkVideoDi2Upload.setVisible(false);
            chkVideoDi2Upload.setSelected(false);
        }else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
            this.cmbDigInput2Name.setSelectedIndex(4);
            chkVideoDi2Upload.setVisible(false);
            chkVideoDi2Upload.setSelected(false);
        }
        else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_LEFTARROW) {
            this.cmbDigInput2Name.setSelectedIndex(5);
            chkVideoDi2Upload.setVisible(false);
            chkVideoDi2Upload.setSelected(false);
        }else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
            this.cmbDigInput2Name.setSelectedIndex(6);
            chkVideoDi2Upload.setVisible(false);
            chkVideoDi2Upload.setSelected(false);
        }
        
        if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_NONE) {
            this.cmbDigInput3Name.setSelectedIndex(0);
            chkVideoDi3Upload.setVisible(false);
            chkVideoDi3Upload.setSelected(false);
        } /*else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_SOS) {
            this.cmbDigInput3Name.setSelectedIndex(1);
            chkVideoDi3Upload.setVisible(false);
            chkVideoDi3Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            this.cmbDigInput3Name.setSelectedIndex(2);
            chkVideoDi3Upload.setVisible(true);
            if (clsSharedVariables.getVideoDig3FtpUploadEnable() == true) {
                chkVideoDi3Upload.setSelected(true);
                clsSharedVariables.setVideoDig3FtpUploadEnable(true);
            } else {
                chkVideoDi3Upload.setSelected(false);
                clsSharedVariables.setVideoDig3FtpUploadEnable(false);
            }
        } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
            this.cmbDigInput3Name.setSelectedIndex(3);
            chkVideoDi3Upload.setVisible(false);
            chkVideoDi3Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
            this.cmbDigInput3Name.setSelectedIndex(4);
            chkVideoDi3Upload.setVisible(false);
            chkVideoDi3Upload.setSelected(false);
        }*/ else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_LEFTARROW) {
            this.cmbDigInput3Name.setSelectedIndex(1);
            chkVideoDi3Upload.setVisible(false);
            chkVideoDi3Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
            this.cmbDigInput3Name.setSelectedIndex(2);
            chkVideoDi3Upload.setVisible(false);
            chkVideoDi3Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_DOORCAM) {
            this.cmbDigInput3Name.setSelectedIndex(3);
            chkVideoDi3Upload.setVisible(false);
            chkVideoDi3Upload.setSelected(false);
        }

        if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_NONE) {
            this.cmbDigInput4Name.setSelectedIndex(0);
            chkVideoDi4Upload.setVisible(false);
            chkVideoDi4Upload.setSelected(false);
        } /*else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_SOS) {
            this.cmbDigInput4Name.setSelectedIndex(1);
            chkVideoDi4Upload.setVisible(false);
            chkVideoDi4Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            this.cmbDigInput4Name.setSelectedIndex(2);
            chkVideoDi4Upload.setVisible(true);
            if (clsSharedVariables.getVideoDig4FtpUploadEnable() == true) {
                chkVideoDi4Upload.setSelected(true);
                clsSharedVariables.setVideoDig4FtpUploadEnable(true);
            } else {
                chkVideoDi4Upload.setSelected(false);
                clsSharedVariables.setVideoDig4FtpUploadEnable(false);
            }
        } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
            this.cmbDigInput4Name.setSelectedIndex(3);
            chkVideoDi4Upload.setVisible(false);
            chkVideoDi4Upload.setSelected(false);
        } */else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
            this.cmbDigInput4Name.setSelectedIndex(1);
            chkVideoDi4Upload.setVisible(false);
            chkVideoDi4Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_LEFTARROW) {
            this.cmbDigInput4Name.setSelectedIndex(2);
            chkVideoDi4Upload.setVisible(false);
            chkVideoDi4Upload.setSelected(false);
        } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
            this.cmbDigInput4Name.setSelectedIndex(3);
            chkVideoDi4Upload.setVisible(false);
            chkVideoDi4Upload.setSelected(false);
        } /*else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_DOORCAM) {
            this.cmbDigInput4Name.setSelectedIndex(7);
            chkVideoDi4Upload.setVisible(false);
            chkVideoDi4Upload.setSelected(false);
        }*/

        if (clsSharedVariables.getDigInp1DefaultClosedState() == false) {
            cmbDig1Switch.setSelectedIndex(0);
        } else {
            cmbDig1Switch.setSelectedIndex(1);
        }
        if (clsSharedVariables.getDigInp2DefaultClosedState() == false) {
            cmbDig2Switch.setSelectedIndex(0);
        } else {
            cmbDig2Switch.setSelectedIndex(1);
        }
        if (clsSharedVariables.getDigInp3DefaultClosedState() == false) {
            cmbDig3Switch.setSelectedIndex(0);
        } else {
            cmbDig3Switch.setSelectedIndex(1);
        }
        if (clsSharedVariables.getDigInp4DefaultClosedState() == false) {
            cmbDig4Switch.setSelectedIndex(0);
        } else {
            cmbDig4Switch.setSelectedIndex(1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblConfigName = new javax.swing.JTextField();
        lblSosActive2 = new javax.swing.JLabel();
        cmbDigInput1Name = new javax.swing.JComboBox();
        cmbDig1Switch = new javax.swing.JComboBox();
        chkVideoDi1Upload = new javax.swing.JCheckBox();
        lblSosActive3 = new javax.swing.JLabel();
        cmbDigInput2Name = new javax.swing.JComboBox();
        cmbDig2Switch = new javax.swing.JComboBox();
        chkVideoDi2Upload = new javax.swing.JCheckBox();
        lblSosActive4 = new javax.swing.JLabel();
        cmbDigInput3Name = new javax.swing.JComboBox();
        cmbDig3Switch = new javax.swing.JComboBox();
        chkVideoDi3Upload = new javax.swing.JCheckBox();
        lblSosActive5 = new javax.swing.JLabel();
        cmbDigInput4Name = new javax.swing.JComboBox();
        cmbDig4Switch = new javax.swing.JComboBox();
        chkVideoDi4Upload = new javax.swing.JCheckBox();
        btnSave = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(453, 430));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("DIGITAL INPUT ");
        lblConfigName.setPreferredSize(new java.awt.Dimension(550, 32));

        lblSosActive2.setFont(lblSosActive2.getFont().deriveFont(lblSosActive2.getFont().getStyle() | java.awt.Font.BOLD, lblSosActive2.getFont().getSize()+5));
        lblSosActive2.setForeground(new java.awt.Color(0, 0, 102));
        lblSosActive2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSosActive2.setText("Digital I/P1");
        lblSosActive2.setPreferredSize(new java.awt.Dimension(115, 37));

        cmbDigInput1Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbDigInput1Name.setForeground(new java.awt.Color(102, 0, 0));
        cmbDigInput1Name.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE", "SOS", "VIDEO RECORD" }));
        cmbDigInput1Name.setToolTipText("");
        cmbDigInput1Name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDigInput1NameItemStateChanged(evt);
            }
        });
        cmbDigInput1Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDigInput1NameActionPerformed(evt);
            }
        });

        cmbDig1Switch.setFont(cmbDig1Switch.getFont().deriveFont(cmbDig1Switch.getFont().getStyle() | java.awt.Font.BOLD, cmbDig1Switch.getFont().getSize()+3));
        cmbDig1Switch.setForeground(new java.awt.Color(0, 51, 51));
        cmbDig1Switch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N Open", "N Close" }));
        cmbDig1Switch.setPreferredSize(new java.awt.Dimension(103, 23));
        cmbDig1Switch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDig1SwitchActionPerformed(evt);
            }
        });

        chkVideoDi1Upload.setBackground(new java.awt.Color(153, 153, 0));
        chkVideoDi1Upload.setFont(chkVideoDi1Upload.getFont().deriveFont(chkVideoDi1Upload.getFont().getStyle() | java.awt.Font.BOLD, chkVideoDi1Upload.getFont().getSize()+5));
        chkVideoDi1Upload.setForeground(new java.awt.Color(0, 0, 51));
        chkVideoDi1Upload.setText("FTP");
        chkVideoDi1Upload.setPreferredSize(new java.awt.Dimension(57, 30));
        chkVideoDi1Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVideoDi1UploadActionPerformed(evt);
            }
        });

        lblSosActive3.setFont(lblSosActive3.getFont().deriveFont(lblSosActive3.getFont().getStyle() | java.awt.Font.BOLD, lblSosActive3.getFont().getSize()+5));
        lblSosActive3.setForeground(new java.awt.Color(0, 0, 102));
        lblSosActive3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSosActive3.setText("Digital I/P2");
        lblSosActive3.setPreferredSize(new java.awt.Dimension(115, 37));

        cmbDigInput2Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbDigInput2Name.setForeground(new java.awt.Color(102, 0, 0));
        cmbDigInput2Name.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE", "SOS", "VIDEO RECORD", "STOP REQUEST", "REVERSE CAMERA", "LEFT", "RIGHT" }));
        cmbDigInput2Name.setToolTipText("");
        cmbDigInput2Name.setMinimumSize(new java.awt.Dimension(148, 37));
        cmbDigInput2Name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDigInput2NameItemStateChanged(evt);
            }
        });
        cmbDigInput2Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDigInput2NameActionPerformed(evt);
            }
        });

        cmbDig2Switch.setFont(cmbDig2Switch.getFont().deriveFont(cmbDig2Switch.getFont().getStyle() | java.awt.Font.BOLD, cmbDig2Switch.getFont().getSize()+3));
        cmbDig2Switch.setForeground(new java.awt.Color(0, 51, 51));
        cmbDig2Switch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N Open", "N Close" }));
        cmbDig2Switch.setPreferredSize(new java.awt.Dimension(103, 23));
        cmbDig2Switch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDig2SwitchActionPerformed(evt);
            }
        });

        chkVideoDi2Upload.setBackground(new java.awt.Color(153, 153, 0));
        chkVideoDi2Upload.setFont(chkVideoDi2Upload.getFont().deriveFont(chkVideoDi2Upload.getFont().getStyle() | java.awt.Font.BOLD, chkVideoDi2Upload.getFont().getSize()+5));
        chkVideoDi2Upload.setForeground(new java.awt.Color(0, 0, 51));
        chkVideoDi2Upload.setText("FTP");
        chkVideoDi2Upload.setPreferredSize(new java.awt.Dimension(57, 30));
        chkVideoDi2Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVideoDi2UploadActionPerformed(evt);
            }
        });

        lblSosActive4.setFont(lblSosActive4.getFont().deriveFont(lblSosActive4.getFont().getStyle() | java.awt.Font.BOLD, lblSosActive4.getFont().getSize()+5));
        lblSosActive4.setForeground(new java.awt.Color(0, 0, 102));
        lblSosActive4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSosActive4.setText("Digital I/P3");
        lblSosActive4.setPreferredSize(new java.awt.Dimension(115, 37));

        cmbDigInput3Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbDigInput3Name.setForeground(new java.awt.Color(102, 0, 0));
        cmbDigInput3Name.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE", "LEFT", "RIGHT", "DOOR CAM" }));
        cmbDigInput3Name.setToolTipText("");
        cmbDigInput3Name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDigInput3NameItemStateChanged(evt);
            }
        });
        cmbDigInput3Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDigInput3NameActionPerformed(evt);
            }
        });

        cmbDig3Switch.setFont(cmbDig3Switch.getFont().deriveFont(cmbDig3Switch.getFont().getStyle() | java.awt.Font.BOLD, cmbDig3Switch.getFont().getSize()+3));
        cmbDig3Switch.setForeground(new java.awt.Color(0, 51, 51));
        cmbDig3Switch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N Open", "N Close" }));
        cmbDig3Switch.setPreferredSize(new java.awt.Dimension(103, 23));
        cmbDig3Switch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDig3SwitchActionPerformed(evt);
            }
        });

        chkVideoDi3Upload.setBackground(new java.awt.Color(153, 153, 0));
        chkVideoDi3Upload.setFont(chkVideoDi3Upload.getFont().deriveFont(chkVideoDi3Upload.getFont().getStyle() | java.awt.Font.BOLD, chkVideoDi3Upload.getFont().getSize()+5));
        chkVideoDi3Upload.setForeground(new java.awt.Color(0, 0, 51));
        chkVideoDi3Upload.setText("FTP");
        chkVideoDi3Upload.setPreferredSize(new java.awt.Dimension(57, 30));
        chkVideoDi3Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVideoDi3UploadActionPerformed(evt);
            }
        });

        lblSosActive5.setFont(lblSosActive5.getFont().deriveFont(lblSosActive5.getFont().getStyle() | java.awt.Font.BOLD, lblSosActive5.getFont().getSize()+5));
        lblSosActive5.setForeground(new java.awt.Color(0, 0, 102));
        lblSosActive5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSosActive5.setText("Digital I/P4");
        lblSosActive5.setPreferredSize(new java.awt.Dimension(115, 37));

        cmbDigInput4Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbDigInput4Name.setForeground(new java.awt.Color(102, 0, 0));
        cmbDigInput4Name.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE", "REVERSE CAMERA", "LEFT", "RIGHT" }));
        cmbDigInput4Name.setToolTipText("");
        cmbDigInput4Name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDigInput4NameItemStateChanged(evt);
            }
        });
        cmbDigInput4Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDigInput4NameActionPerformed(evt);
            }
        });

        cmbDig4Switch.setFont(cmbDig4Switch.getFont().deriveFont(cmbDig4Switch.getFont().getStyle() | java.awt.Font.BOLD, cmbDig4Switch.getFont().getSize()+3));
        cmbDig4Switch.setForeground(new java.awt.Color(0, 51, 51));
        cmbDig4Switch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N Open", "N Close" }));
        cmbDig4Switch.setPreferredSize(new java.awt.Dimension(103, 23));
        cmbDig4Switch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDig4SwitchActionPerformed(evt);
            }
        });

        chkVideoDi4Upload.setBackground(new java.awt.Color(153, 153, 0));
        chkVideoDi4Upload.setFont(chkVideoDi4Upload.getFont().deriveFont(chkVideoDi4Upload.getFont().getStyle() | java.awt.Font.BOLD, chkVideoDi4Upload.getFont().getSize()+5));
        chkVideoDi4Upload.setForeground(new java.awt.Color(0, 0, 51));
        chkVideoDi4Upload.setText("FTP");
        chkVideoDi4Upload.setPreferredSize(new java.awt.Dimension(57, 30));
        chkVideoDi4Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVideoDi4UploadActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(47, 49, 51));
        btnSave.setFont(btnSave.getFont().deriveFont(btnSave.getFont().getStyle() | java.awt.Font.BOLD, btnSave.getFont().getSize()+7));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.setAutoscrolls(true);
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(47, 49, 51));
        btnRefresh.setFont(btnRefresh.getFont().deriveFont(btnRefresh.getFont().getStyle() | java.awt.Font.BOLD, btnRefresh.getFont().getSize()+7));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.setAutoscrolls(true);
        btnRefresh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh.setPreferredSize(new java.awt.Dimension(110, 42));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+6));
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSosActive5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDigInput4Name, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDig4Switch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblSosActive4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDigInput3Name, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDig3Switch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblSosActive3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDigInput2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDig2Switch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblSosActive2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDigInput1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDig1Switch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkVideoDi4Upload, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkVideoDi3Upload, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkVideoDi2Upload, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkVideoDi1Upload, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkVideoDi1Upload, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSosActive2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbDigInput1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbDig1Switch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkVideoDi2Upload, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSosActive3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbDigInput2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbDig2Switch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSosActive4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbDigInput3Name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbDig3Switch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkVideoDi3Upload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSosActive5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbDigInput4Name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDig4Switch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkVideoDi4Upload, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents
private boolean check_pendrive_detection() {
        UserPrincipal p = null;
        String path = null;
        int i = 0;
        File[] out_path = usb_filepath.listFiles();
        Arrays.sort(out_path);

        for (i = 0; i < out_path.length; i++) {
            if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                try {

                    p = java.nio.file.Files.getOwner(out_path[i].toPath(), LinkOption.NOFOLLOW_LINKS);

                    if (p.toString().equals("root")) {
                    } else {
                        path = out_path[i].getAbsolutePath();
                    }
                } catch (IOException ex) {
                } catch (Exception ex) {
                }
            }
        }

        p = null;
        if (path == null) {
            return false;
        }
        return true;

    }
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        try {

            if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_NONE) {
                this.cmbDigInput1Name.setSelectedIndex(0);
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_SOS) {
                this.cmbDigInput1Name.setSelectedIndex(1);
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                this.cmbDigInput1Name.setSelectedIndex(2);
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                this.cmbDigInput1Name.setSelectedIndex(3);
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
                this.cmbDigInput1Name.setSelectedIndex(4);
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                this.cmbDigInput1Name.setSelectedIndex(5);
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                this.cmbDigInput1Name.setSelectedIndex(6);
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_DOORCAM) {
                this.cmbDigInput1Name.setSelectedIndex(7);
            }

            if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_NONE) {
                this.cmbDigInput2Name.setSelectedIndex(0);
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_SOS) {
                this.cmbDigInput2Name.setSelectedIndex(1);
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                this.cmbDigInput2Name.setSelectedIndex(2);
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                this.cmbDigInput2Name.setSelectedIndex(3);
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
                this.cmbDigInput2Name.setSelectedIndex(4);
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_LEFTARROW){
                this.cmbDigInput2Name.setSelectedIndex(5);
            }else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_RIGHTARROW){
                this.cmbDigInput2Name.setSelectedIndex(6);
            }

            if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_NONE) {
                this.cmbDigInput3Name.setSelectedIndex(0);
            }/* else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_SOS) {
                this.cmbDigInput3Name.setSelectedIndex(1);
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                this.cmbDigInput3Name.setSelectedIndex(2);
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                this.cmbDigInput3Name.setSelectedIndex(3);
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
                this.cmbDigInput3Name.setSelectedIndex(4);
            }*/ else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                this.cmbDigInput3Name.setSelectedIndex(1);
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                this.cmbDigInput3Name.setSelectedIndex(2);
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_DOORCAM) {
                this.cmbDigInput3Name.setSelectedIndex(3);
            }

            if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_NONE) {
                this.cmbDigInput4Name.setSelectedIndex(0);
            }/* else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_SOS) {
                this.cmbDigInput4Name.setSelectedIndex(1);
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                this.cmbDigInput4Name.setSelectedIndex(2);
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                this.cmbDigInput4Name.setSelectedIndex(3);
            }*/ else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
                this.cmbDigInput4Name.setSelectedIndex(1);
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                this.cmbDigInput4Name.setSelectedIndex(2);
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                this.cmbDigInput4Name.setSelectedIndex(3);
            }/* else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_DOORCAM) {
                this.cmbDigInput4Name.setSelectedIndex(7);
            }*/

            lblMsg.setText("");
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        lblMsg.setText("");
        clsReadFiles obj = new clsReadFiles();

        switch (this.cmbDigInput1Name.getSelectedIndex()) {
            case 0:
                clsSharedVariables.setDigInp1Name(DIG_INPUT_NONE);
                break;
            case 1:
                clsSharedVariables.setDigInp1Name(clsDefines.DIG_INPUT_SOS);
                break;
            case 2:
                clsSharedVariables.setDigInp1Name(clsDefines.DIG_INPUT_VIDEO_RECORDING);
                clsSharedVariables.setVideoDig1FtpUploadEnable(chkVideoDi1Upload.isSelected());
                //}
                break;
            case 3:
                clsSharedVariables.setDigInp1Name(clsDefines.DIG_INPUT_STOPREQUEST);
                break;
            case 4:
                clsSharedVariables.setDigInp1Name(clsDefines.DIG_INPUT_REVERSE_GEAR);
                break;
            case 5:
                clsSharedVariables.setDigInp1Name(clsDefines.DIG_INPUT_LEFTARROW);
                break;
            case 6:
                clsSharedVariables.setDigInp1Name(clsDefines.DIG_INPUT_RIGHTARROW);
                break;
            //DOORCAM
            case 7:
                clsSharedVariables.setDigInp1Name(clsDefines.DIG_INPUT_DOORCAM);
                break;
            default:
                clsSharedVariables.setDigInp1Name(clsDefines.DIG_INPUT_NONE);
                break;

        }

        switch (this.cmbDigInput2Name.getSelectedIndex()) {
            case 0:
                clsSharedVariables.setDigInp2Name(DIG_INPUT_NONE);
                break;
            case 1:
                clsSharedVariables.setDigInp2Name(clsDefines.DIG_INPUT_SOS);
                break;
            case 2:
                clsSharedVariables.setDigInp2Name(clsDefines.DIG_INPUT_VIDEO_RECORDING);
                //  if(chkVideoDi1Upload.isSelected()==true){
                clsSharedVariables.setVideoDig2FtpUploadEnable(chkVideoDi2Upload.isSelected());
                //}
                break;
            case 3:
                clsSharedVariables.setDigInp2Name(clsDefines.DIG_INPUT_STOPREQUEST);
                break;
            case 4:
                clsSharedVariables.setDigInp2Name(clsDefines.DIG_INPUT_REVERSE_GEAR);
                break;
            case 5:
                clsSharedVariables.setDigInp2Name(clsDefines.DIG_INPUT_LEFTARROW);
                break;
            case 6:
                clsSharedVariables.setDigInp2Name(clsDefines.DIG_INPUT_RIGHTARROW);
                break;
            default:
                clsSharedVariables.setDigInp2Name(clsDefines.DIG_INPUT_NONE);
        }
        switch (this.cmbDigInput3Name.getSelectedIndex()) {
            case 0:
                clsSharedVariables.setDigInp3Name(DIG_INPUT_NONE);
                break;
            /*case 1:
                clsSharedVariables.setDigInp3Name(clsDefines.DIG_INPUT_SOS);
                break;
            case 2:
                clsSharedVariables.setDigInp3Name(clsDefines.DIG_INPUT_VIDEO_RECORDING);
                clsSharedVariables.setVideoDig3FtpUploadEnable(chkVideoDi3Upload.isSelected());
                break;
            case 3:
                clsSharedVariables.setDigInp3Name(clsDefines.DIG_INPUT_STOPREQUEST);
                break;
            case 4:
                clsSharedVariables.setDigInp3Name(clsDefines.DIG_INPUT_REVERSE_GEAR);
                break;*/
            case 1:
                clsSharedVariables.setDigInp3Name(clsDefines.DIG_INPUT_LEFTARROW);
                break;
            case 2:
                clsSharedVariables.setDigInp3Name(clsDefines.DIG_INPUT_RIGHTARROW);
                break;
            //DOORCAM
            case 3:
                clsSharedVariables.setDigInp3Name(clsDefines.DIG_INPUT_DOORCAM);
                break;
            default:
                clsSharedVariables.setDigInp3Name(clsDefines.DIG_INPUT_NONE);
                break;
        }

        switch (this.cmbDigInput4Name.getSelectedIndex()) {
            case 0:
                clsSharedVariables.setDigInp4Name(DIG_INPUT_NONE);
                break;
            /*case 1:
                clsSharedVariables.setDigInp4Name(clsDefines.DIG_INPUT_SOS);
                break;
            case 2:
                clsSharedVariables.setDigInp4Name(clsDefines.DIG_INPUT_VIDEO_RECORDING);
                clsSharedVariables.setVideoDig4FtpUploadEnable(chkVideoDi4Upload.isSelected());
                //}
                break;
            case 3:
                clsSharedVariables.setDigInp4Name(clsDefines.DIG_INPUT_STOPREQUEST);
                break;*/
            case 1:
                clsSharedVariables.setDigInp4Name(clsDefines.DIG_INPUT_REVERSE_GEAR);
                break;
            case 2:
                clsSharedVariables.setDigInp4Name(clsDefines.DIG_INPUT_LEFTARROW);
                break;
            case 3:
                clsSharedVariables.setDigInp4Name(clsDefines.DIG_INPUT_RIGHTARROW);
                break;
            //DOORCAM
           /*case 7:
                clsSharedVariables.setDigInp4Name(clsDefines.DIG_INPUT_DOORCAM);
                break;*/

            default:
                clsSharedVariables.setDigInp4Name(DIG_INPUT_NONE);
                break;
        }

        if (cmbDig1Switch.getSelectedIndex() == 0) { //open
            clsSharedVariables.setDigInp1DefaultClosedState(false);
        } else {
            clsSharedVariables.setDigInp1DefaultClosedState(true);
        }
        if (cmbDig2Switch.getSelectedIndex() == 0) { //open
            clsSharedVariables.setDigInp2DefaultClosedState(false);
        } else {
            clsSharedVariables.setDigInp2DefaultClosedState(true);
        }
        if (cmbDig3Switch.getSelectedIndex() == 0) { //open
            clsSharedVariables.setDigInp3DefaultClosedState(false);
        } else {
            clsSharedVariables.setDigInp3DefaultClosedState(true);
        }
        if (cmbDig4Switch.getSelectedIndex() == 0) { //open
            clsSharedVariables.setDigInp4DefaultClosedState(false);
        } else {
            clsSharedVariables.setDigInp4DefaultClosedState(true);
        }

        if (clsSharedVariables.getDigInp1DefaultClosedState() == true) {
            DigInp1CheckState = PinState.HIGH;
        } else {
            DigInp1CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp2DefaultClosedState() == true) {
            DigInp2CheckState = PinState.HIGH;
        } else {
            DigInp2CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp3DefaultClosedState() == true) {
            DigInp3CheckState = PinState.HIGH;
        } else {
            DigInp3CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp4DefaultClosedState() == true) {
            DigInp4CheckState = PinState.HIGH;
        } else {
            DigInp4CheckState = PinState.LOW;
        }
        // System.out.print("Need to check :"+obj.write_cfg_data_file());

        if (obj.write_cfg_data_file()) {
            lblMsg.setText(lblMsg.getText() + " Configuration Saved");
        } else {
            lblMsg.setText(lblMsg.getText() + " Configuration Not Saved");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void chkVideoDi4UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVideoDi4UploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVideoDi4UploadActionPerformed

    private void cmbDigInput4NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDigInput4NameActionPerformed
        // TODO add your handling code here:
      /*  if (cmbDigInput4Name.getSelectedIndex() == 2) {
            chkVideoDi4Upload.setVisible(true);
        } else {
            chkVideoDi4Upload.setVisible(false);
        }*/
    }//GEN-LAST:event_cmbDigInput4NameActionPerformed

    private void cmbDigInput4NameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDigInput4NameItemStateChanged
        // TODO add your handling code here:
     /*   if (cmbDigInput4Name.getSelectedIndex() == 2) {
            chkVideoDi4Upload.setVisible(true);
        } else {
            chkVideoDi4Upload.setVisible(false);
        }*/
    }//GEN-LAST:event_cmbDigInput4NameItemStateChanged

    private void chkVideoDi3UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVideoDi3UploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVideoDi3UploadActionPerformed

    private void cmbDigInput3NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDigInput3NameActionPerformed
        // TODO add your handling code here:
      /*  if (cmbDigInput3Name.getSelectedIndex() == 2) {
            chkVideoDi3Upload.setVisible(true);
        } else {
            chkVideoDi3Upload.setVisible(false);
        }*/
    }//GEN-LAST:event_cmbDigInput3NameActionPerformed

    private void cmbDigInput3NameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDigInput3NameItemStateChanged
        // TODO add your handling code here:
       /* if (cmbDigInput3Name.getSelectedIndex() == 2) {
            chkVideoDi3Upload.setVisible(true);
        } else {
            chkVideoDi3Upload.setVisible(false);
        }*/
    }//GEN-LAST:event_cmbDigInput3NameItemStateChanged

    private void chkVideoDi2UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVideoDi2UploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVideoDi2UploadActionPerformed

    private void cmbDigInput2NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDigInput2NameActionPerformed
        // TODO add your handling code here:
        if (cmbDigInput2Name.getSelectedIndex() == 2) {
            chkVideoDi2Upload.setVisible(true);
        } else {
            chkVideoDi2Upload.setVisible(false);
        }
    }//GEN-LAST:event_cmbDigInput2NameActionPerformed

    private void cmbDigInput2NameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDigInput2NameItemStateChanged
        // TODO add your handling code here:
        if (cmbDigInput2Name.getSelectedIndex() == 2) {
            chkVideoDi2Upload.setVisible(true);
        } else {
            chkVideoDi2Upload.setVisible(false);
        }
    }//GEN-LAST:event_cmbDigInput2NameItemStateChanged

    private void chkVideoDi1UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVideoDi1UploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVideoDi1UploadActionPerformed

    private void cmbDigInput1NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDigInput1NameActionPerformed
        // TODO add your handling code here:
        if (cmbDigInput1Name.getSelectedIndex() == 2) {
            chkVideoDi1Upload.setVisible(true);
        } else {
            chkVideoDi1Upload.setVisible(false);
        }
    }//GEN-LAST:event_cmbDigInput1NameActionPerformed

    private void cmbDigInput1NameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDigInput1NameItemStateChanged
        // TODO add your handling code here:
        if (cmbDigInput1Name.getSelectedIndex() == 2) {
            chkVideoDi1Upload.setVisible(true);
        } else {
            chkVideoDi1Upload.setVisible(false);
        }
    }//GEN-LAST:event_cmbDigInput1NameItemStateChanged

    private void cmbDig4SwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDig4SwitchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDig4SwitchActionPerformed

    private void cmbDig3SwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDig3SwitchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDig3SwitchActionPerformed

    private void cmbDig2SwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDig2SwitchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDig2SwitchActionPerformed

    private void cmbDig1SwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDig1SwitchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDig1SwitchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkVideoDi1Upload;
    private javax.swing.JCheckBox chkVideoDi2Upload;
    private javax.swing.JCheckBox chkVideoDi3Upload;
    private javax.swing.JCheckBox chkVideoDi4Upload;
    private javax.swing.JComboBox cmbDig1Switch;
    private javax.swing.JComboBox cmbDig2Switch;
    private javax.swing.JComboBox cmbDig3Switch;
    private javax.swing.JComboBox cmbDig4Switch;
    private javax.swing.JComboBox cmbDigInput1Name;
    private javax.swing.JComboBox cmbDigInput2Name;
    private javax.swing.JComboBox cmbDigInput3Name;
    private javax.swing.JComboBox cmbDigInput4Name;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblSosActive2;
    private javax.swing.JLabel lblSosActive3;
    private javax.swing.JLabel lblSosActive4;
    private javax.swing.JLabel lblSosActive5;
    // End of variables declaration//GEN-END:variables
}
