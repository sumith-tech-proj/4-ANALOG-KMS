
package obuits;

import javax.swing.JPanel;

public class PanMessages extends javax.swing.JPanel {

    private JPanel objPanel = null;
    public PanMessages() {
        initComponents();

        if (objPanel != null) {
            objPanel = null;
        }
        objPanel = new PanPreloads();
        PanMessageCenter.removeAll();
        PanMessageCenter.setLayout(new java.awt.BorderLayout());
        PanMessageCenter.add(objPanel);
        PanMessageCenter.revalidate();
        PanMessageCenter.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPreload = new javax.swing.JButton();
        PanMessageCenter = new javax.swing.JPanel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(700, 430));

        jPanel1.setBackground(new java.awt.Color(23, 29, 32));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(698, 54));

        btnPreload.setBackground(new java.awt.Color(85, 85, 85));
        btnPreload.setFont(btnPreload.getFont().deriveFont(btnPreload.getFont().getStyle() | java.awt.Font.BOLD, btnPreload.getFont().getSize()+10));
        btnPreload.setForeground(new java.awt.Color(255, 255, 255));
        btnPreload.setText("Preloads");
        btnPreload.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPreload.setPreferredSize(new java.awt.Dimension(193, 40));
        btnPreload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPreload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(497, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPreload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanMessageCenter.setOpaque(false);
        PanMessageCenter.setPreferredSize(new java.awt.Dimension(698, 370));

        javax.swing.GroupLayout PanMessageCenterLayout = new javax.swing.GroupLayout(PanMessageCenter);
        PanMessageCenter.setLayout(PanMessageCenterLayout);
        PanMessageCenterLayout.setHorizontalGroup(
            PanMessageCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        PanMessageCenterLayout.setVerticalGroup(
            PanMessageCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanMessageCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanMessageCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreloadActionPerformed
        // TODO add your handling code here: 
        if (objPanel != null) {
            objPanel = null;
        }
        objPanel = new PanPreloads();
        PanMessageCenter.removeAll();
        PanMessageCenter.setLayout(new java.awt.BorderLayout());
        PanMessageCenter.add(objPanel);
        PanMessageCenter.revalidate();
        PanMessageCenter.repaint();
    }//GEN-LAST:event_btnPreloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanMessageCenter;
    private javax.swing.JButton btnPreload;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
