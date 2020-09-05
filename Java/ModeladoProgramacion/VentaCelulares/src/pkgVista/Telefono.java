package pkgVista;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author abrego
 */
public class Telefono extends javax.swing.JFrame {
    
    /**
     * Constructor
     */
    public Telefono() {
        initComponents();
    }

    /**
     * Metodo usado en el constructor para inicializar el frame  
     * con los campos necesarios para su uso
     */
    @SuppressWarnings("unchecked")
        private void initComponents() {

        recibirRFC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btGuardar = new javax.swing.JButton();
        btMas = new javax.swing.JButton();
        txtTel = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        recibirRFC.setEditable(false);

        jLabel5.setText("RFC");

        jLabel1.setText("Telefono");

        jLabel2.setText("Email");

        btGuardar.setText("Guardar y salir");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        btMas.setText("Agregar otro");
        btMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMasActionPerformed(evt);
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
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recibirRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btGuardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                            .addComponent(btMas))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                .addComponent(txtTel)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(recibirRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMas)
                    .addComponent(btGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        pkgModelo.OPGPersona persona =new pkgModelo.OPGPersona();
        pkgModelo.OPGEmpresa emp =new pkgModelo.OPGEmpresa();        
        pkgVista.FormularioEmpresa empresa =new pkgVista.FormularioEmpresa();        
        try {
            pkgVista.FormularioCliente cliente=new pkgVista.FormularioCliente();
            pkgVista.FormularioEmpleado empleado=new pkgVista.FormularioEmpleado();
            if(empresa.radioE){
                emp.setRfc(recibirRFC.getText());
                emp.insertartEmail(txtEmail.getText());
                emp.insertartTel(txtTel.getText());                
                JOptionPane.showMessageDialog(this, "Telefono e Email de Empresa Guardado");                 
            }else{
                if(cliente.radioC || empleado.radioE){
                    persona.setRfc(recibirRFC.getText());
                    persona.insertartEmail(txtEmail.getText());
                    persona.insertartTel(txtTel.getText());                                    
                    JOptionPane.showMessageDialog(this, "Telefono e Email de Persona Guardado");                 
                }
            }
            empresa.radioE=false;                
            cliente.radioC=false;
            empleado.radioE=false;
            this.setVisible(false);
            new pkgVista.Principal().setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Fallo"); 
        }        
    }

    private void btMasActionPerformed(java.awt.event.ActionEvent evt) {
        pkgModelo.OPGPersona persona =new pkgModelo.OPGPersona();
        pkgModelo.OPGEmpresa emp =new pkgModelo.OPGEmpresa();
        pkgVista.FormularioEmpresa empresa =new pkgVista.FormularioEmpresa();        
        try {
            pkgVista.FormularioCliente cliente=new pkgVista.FormularioCliente();
            pkgVista.FormularioEmpleado empleado=new pkgVista.FormularioEmpleado();
            if(empresa.radioE){
                emp.setRfc(recibirRFC.getText());
                emp.insertartEmail(txtEmail.getText());
                emp.insertartTel(txtTel.getText());                
                JOptionPane.showMessageDialog(this, "Telefono e Email de  Empresa Guardado");                 
            }else{
                if(cliente.radioC || empleado.radioE){
                    persona.setRfc(recibirRFC.getText());
                    persona.insertartEmail(txtEmail.getText());
                    persona.insertartTel(txtTel.getText());                
                    JOptionPane.showMessageDialog(this, "Telefono e Email de Persona Guardado");                 
                }
            }
            txtEmail.setText("");
            txtTel.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Fallo"); 
        }        
    }


    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btMas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JTextField recibirRFC;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtTel;
 
}
