package pkgVista;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author abrego
 */
public class BorrarEmpresa extends javax.swing.JFrame {

    /**
     * Constructor
     */
    public BorrarEmpresa() throws SQLException {
        initComponents();
        pkgModelo.Auxiliares aux=new pkgModelo.Auxiliares();       
        aux.llenaBox(boxId,"SELECT rfc FROM Empresa;");
    }

    /**
     * Metodo usado en el constructor para inicializar el frame  
     * con los campos necesarios para su uso
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        botonBorrar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        boxId = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Borrar Empresa");

        botonBorrar.setText("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Empresa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonCancelar)
                        .addGap(200, 200, 200)
                        .addComponent(botonBorrar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(boxId, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonBorrar))
                .addGap(12, 12, 12))
        );

        pack();
    }

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        pkgVista.Principal prin = new pkgVista.Principal();
        pkgModelo.OPGEmpresa tel= new pkgModelo.OPGEmpresa();
        tel.setRfc(boxId.getSelectedItem().toString());
        try {
            if(tel.deleteEmpresa()){
                JOptionPane.showMessageDialog(this, "Base Actualizada");
                this.setVisible(false);
                prin.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar");
            prin.setVisible(true);
        }
    }

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        pkgVista.Principal principal=new pkgVista.Principal();
        principal.setVisible(true);
        this.setVisible(false);
    }

    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JComboBox boxId;
    private javax.swing.JLabel jLabel1;
}
