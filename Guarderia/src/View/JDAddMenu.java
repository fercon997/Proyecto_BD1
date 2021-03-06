/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.*;

/**
 *
 * @author gabrielbaron
 */
public class JDAddMenu extends javax.swing.JDialog {
    AddMenuController controladorAM = new AddMenuController(this);
    java.awt.Frame padre;
    /**
     * Creates new form JDAddMenu
     */
    public JDAddMenu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        padre = parent;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("AgregarMenu");
        //this.Agregar.setEnabled(false);
        controladorAM.llenarComboBoxGuarderias(guarderiaCombo);
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
        tablaPlatos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        guarderiaCombo = new javax.swing.JComboBox<>();
        Fecha_inicioTxt = new javax.swing.JTextField();
        Fecha_finTxt = new javax.swing.JTextField();
        CostoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Plato1Txt = new javax.swing.JTextField();
        PlatoBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Plato2Txt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Plato3Txt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Plato4Txt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Plato5Txt = new javax.swing.JTextField();
        Agregar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        AgregarPlato = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaPlatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Comidas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPlatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPlatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPlatos);

        jLabel2.setText("Fecha Inicio:");

        jLabel3.setText("Fecha Fin:");

        jLabel4.setText("Costo:");

        jLabel11.setText("Guardería:");

        guarderiaCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guarderiaComboActionPerformed(evt);
            }
        });

        jLabel5.setText("Plato1:");

        PlatoBtn.setText("+");
        PlatoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlatoBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Plato2:");

        jLabel7.setText("Plato3:");

        jLabel8.setText("Plato4:");

        jLabel9.setText("Plato5:");

        Agregar.setText("Agregar");
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });

        Cancelar.setText("Cancelar");

        AgregarPlato.setText(" Crear Plato");
        AgregarPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarPlatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(33, 33, 33)
                        .addComponent(guarderiaCombo, 0, 109, Short.MAX_VALUE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(57, 57, 57)
                                .addComponent(CostoTxt))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Fecha_inicioTxt)
                                    .addComponent(Fecha_finTxt)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(54, 54, 54)
                                .addComponent(Plato1Txt))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(54, 54, 54)
                                .addComponent(Plato2Txt))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(54, 54, 54)
                                .addComponent(Plato5Txt))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Plato3Txt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Plato4Txt))))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Agregar)
                            .addComponent(PlatoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(Cancelar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(AgregarPlato)
                        .addGap(254, 254, 254))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(guarderiaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Fecha_inicioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(Fecha_finTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(CostoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Plato1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AgregarPlato))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Plato2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Plato3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(PlatoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Plato4Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Plato5Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Agregar)
                    .addComponent(Cancelar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPlatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPlatosMouseClicked
        controladorAM.platosClickTabla(tablaPlatos);// TODO add your handling code here:
    }//GEN-LAST:event_tablaPlatosMouseClicked

    private void guarderiaComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guarderiaComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guarderiaComboActionPerformed

    private void PlatoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlatoBtnActionPerformed
        controladorAM.mostrarplatos(this.tablaPlatos);        // TODO add your handling code here:
    }//GEN-LAST:event_PlatoBtnActionPerformed

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
        controladorAM.agregarMenu();// TODO add your handling code here:
    }//GEN-LAST:event_AgregarActionPerformed

    private void AgregarPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarPlatoActionPerformed
        // TODO add your handling code here:
        JDAddPlato vistaAP = new JDAddPlato(padre, true);
        vistaAP.setVisible(true);
    }//GEN-LAST:event_AgregarPlatoActionPerformed

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
            java.util.logging.Logger.getLogger(JDAddMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDAddMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDAddMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDAddMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDAddMenu dialog = new JDAddMenu(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Agregar;
    public javax.swing.JButton AgregarPlato;
    private javax.swing.JButton Cancelar;
    public javax.swing.JTextField CostoTxt;
    public javax.swing.JTextField Fecha_finTxt;
    public javax.swing.JTextField Fecha_inicioTxt;
    public javax.swing.JTextField Plato1Txt;
    public javax.swing.JTextField Plato2Txt;
    public javax.swing.JTextField Plato3Txt;
    public javax.swing.JTextField Plato4Txt;
    public javax.swing.JTextField Plato5Txt;
    private javax.swing.JButton PlatoBtn;
    public javax.swing.JComboBox<String> guarderiaCombo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPlatos;
    // End of variables declaration//GEN-END:variables
}
