package pkgVista;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author abrego
 */
public class BorrarServicio extends javax.swing.JFrame {

    /**
     * Constructor
     */
    public BorrarServicio() throws SQLException {
        initComponents();
        pkgModelo.Auxiliares aux=new pkgModelo.Auxiliares();       
        aux.llenaBox(boxServicio,"SELECT id FROM Servicio;");
    }

    
    /**
     * Metodo usado en el constructor para inicializar el frame  
     * con los campos necesarios para su uso
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        botonBorrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        boxServicio = new javax.swing.JComboBox();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Borrar Servicio");

        botonBorrar.setText("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Servicio");

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(botonBorrar))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxServicio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(boxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonBorrar)
                    .addComponent(botonCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        pkgVista.Principal prin = new pkgVista.Principal();
        pkgModelo.OPGServicio ser= new pkgModelo.OPGServicio();   
        String id=boxServicio.getSelectedItem().toString();
        try {
            if(ser.deleteServicio(id)){
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
    private javax.swing.JComboBox boxServicio;
    private javax.swing.JLabel jLabel1;
    
}
