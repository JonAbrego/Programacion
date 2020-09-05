package pkgVista;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author abrego
 */
public class FormularioPersona extends javax.swing.JFrame {

    /**
     * Constructor
     */
    public FormularioPersona() {
        initComponents();
    }

    
    /**
     * Metodo usado en el constructor para inicializar el frame  
     * con los campos necesarios para su uso
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaRFC = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        etiquetaApellidoP = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        etiquetaCP = new javax.swing.JLabel();
        etiquetaNumero = new javax.swing.JLabel();
        etiquetaCalle = new javax.swing.JLabel();
        etiquetaColonia = new javax.swing.JLabel();
        txtApellidoM = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtApellidoP = new javax.swing.JTextField();
        txtCP = new javax.swing.JTextField();
        etiquetaApellidoM = new javax.swing.JLabel();
        txtRFC = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCalle = new javax.swing.JTextField();
        boxEstado = new javax.swing.JComboBox();
        etiquetaCiudad = new javax.swing.JLabel();
        boxAno = new javax.swing.JComboBox();
        boxMes = new javax.swing.JComboBox();
        boxDia = new javax.swing.JComboBox();
        etiquetaFecha = new javax.swing.JLabel();
        etiquetaSexo = new javax.swing.JLabel();
        boxSexo = new javax.swing.JComboBox();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        etiquetaPersona = new javax.swing.JLabel();
        boxTipo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formulario Persona");

        etiquetaRFC.setText("RFC");

        etiquetaNombre.setText("Nombre");

        etiquetaApellidoP.setText("Apellido P");

        etiquetaCP.setText("C.P");

        etiquetaNumero.setText("Número");

        etiquetaCalle.setText("Calle");

        etiquetaColonia.setText("Colonia");

        etiquetaApellidoM.setText("Apellido M");

        boxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baja California Sur ", "Campeche ", "Coahuila ", "Colima ", "Chiapas ", "Chihuahua ", "Distrito Federal", "Durango", "Guanajuato ", "Guerrero ", "Hidalgo", "Guadalajara", "Estado de México", "Morelia", "Cuernavaca", "Nayarit", "Nuevo León", "Oaxaca – Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas " }));

        etiquetaCiudad.setText("Ciudad");

        boxAno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999" }));

        boxMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        boxDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        etiquetaFecha.setText("Fecha Nacimiento");

        etiquetaSexo.setText("Sexo");

        boxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));

        botonGuardar.setText("Guardar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        etiquetaPersona.setText("Tipo Persona");

        boxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cliente", "Empleado" }));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.darkGray));

        jRadioButton1.setText("Telefono/Email");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaRFC)
                        .addGap(31, 31, 31)
                        .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaNombre)
                        .addGap(12, 12, 12)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(etiquetaApellidoP)
                        .addGap(12, 12, 12)
                        .addComponent(txtApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(etiquetaApellidoM)
                        .addGap(12, 12, 12)
                        .addComponent(txtApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaFecha)
                        .addGap(12, 12, 12)
                        .addComponent(boxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(boxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(boxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(etiquetaSexo)
                        .addGap(12, 12, 12)
                        .addComponent(boxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(etiquetaPersona)
                        .addGap(12, 12, 12)
                        .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaNumero)
                        .addGap(12, 12, 12)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(etiquetaCP)
                        .addGap(12, 12, 12)
                        .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonCancelar)
                        .addGap(158, 158, 158)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(botonGuardar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaCiudad)
                        .addGap(18, 18, 18)
                        .addComponent(boxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(etiquetaCalle)
                        .addGap(18, 18, 18)
                        .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(etiquetaColonia)
                        .addGap(18, 18, 18)
                        .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(etiquetaRFC))
                    .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaNombre)
                                    .addComponent(etiquetaApellidoP)
                                    .addComponent(etiquetaApellidoM))))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaFecha)
                                    .addComponent(etiquetaSexo)
                                    .addComponent(etiquetaPersona))))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaCiudad)
                                    .addComponent(etiquetaCalle)
                                    .addComponent(etiquetaColonia))))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaNumero)
                                    .addComponent(etiquetaCP))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonGuardar)
                            .addComponent(botonCancelar))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        pkgModelo.OPGPersona persona= new pkgModelo.OPGPersona();        
        
        String fecha;
        fecha=boxAno.getSelectedItem().toString()+"-"+boxMes.getSelectedItem().toString()+"-"+boxDia.getSelectedItem().toString();
        persona.setRfc(txtRFC.getText());
        persona.setNombre(txtNombre.getText());
        persona.setApellidoP(txtApellidoP.getText());
        persona.setApellidoM(txtApellidoM.getText());
        persona.setFechaNacimiento(fecha);        
        persona.setSexo(boxSexo.getSelectedItem().toString());
        persona.setCiudad(boxEstado.getSelectedItem().toString());
        persona.setColonia(txtColonia.getText());
        persona.setCalle(txtCalle.getText());
        persona.setNumero(txtNumero.getText());
        persona.setCodigoPostal(txtCP.getText());
        int tipo=boxTipo.getSelectedIndex();
        try {
            if(persona.insertar())                
                JOptionPane.showMessageDialog(this, "Se inserto correctamente");                                       
            pkgVista.FormularioEmpleado empleado=new pkgVista.FormularioEmpleado();
            pkgVista.FormularioCliente cliente=new pkgVista.FormularioCliente();
            if(jRadioButton1.isSelected()){
                cliente.radioC=jRadioButton1.isSelected();
                empleado.radioE=jRadioButton1.isSelected();
            }
            if(tipo==0){                
                cliente.recibirRFC1.setText(txtRFC.getText());
                this.setVisible(false);                       
                cliente.setVisible(true);              
            }else{                
                empleado.recibirRFC.setText(txtRFC.getText());                    
                this.setVisible(false);                       
                empleado.setVisible(true);                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar");
        }
    }

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        pkgVista.Principal principal=new pkgVista.Principal();
        principal.setVisible(true);
        this.setVisible(false);
    }

    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JComboBox boxAno;
    public javax.swing.JComboBox boxDia;
    private javax.swing.JComboBox boxEstado;
    public javax.swing.JComboBox boxMes;
    public javax.swing.JComboBox boxSexo;
    private javax.swing.JComboBox boxTipo;
    private javax.swing.JLabel etiquetaApellidoM;
    private javax.swing.JLabel etiquetaApellidoP;
    private javax.swing.JLabel etiquetaCP;
    private javax.swing.JLabel etiquetaCalle;
    private javax.swing.JLabel etiquetaCiudad;
    private javax.swing.JLabel etiquetaColonia;
    private javax.swing.JLabel etiquetaFecha;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel etiquetaNumero;
    private javax.swing.JLabel etiquetaPersona;
    private javax.swing.JLabel etiquetaRFC;
    private javax.swing.JLabel etiquetaSexo;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField txtApellidoM;
    private javax.swing.JTextField txtApellidoP;
    private javax.swing.JTextField txtCP;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    public javax.swing.JTextField txtRFC;

}
