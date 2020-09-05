package pkgVista;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import javax.swing.table.DefaultTableModel;
import pkgModelo.Auxiliares;

/**
 *
 * @author abrego
 */
public class OpcionesAvanzadas extends javax.swing.JFrame {

    private pkgControlador.Conexion conexion=new pkgControlador.Conexion();;
    private DefaultTableModel modelo =new DefaultTableModel();
    private Auxiliares aux=new Auxiliares();

    /**
     * Constructor
     */
    public OpcionesAvanzadas() throws SQLException {
        initComponents();       
        aux.llenaBox(boxHistorialC,"SELECT * FROM Historial;");
    }
   
    /**
     * Metodo usado en el constructor para inicializar el frame  
     * con los campos necesarios para su uso
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        TipoBusqueda = new javax.swing.ButtonGroup();
        Tabla = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        radioPalabra = new javax.swing.JRadioButton();
        radioSQL = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioPersona = new javax.swing.JRadioButton();
        radioEmpresa = new javax.swing.JRadioButton();
        radioCelular = new javax.swing.JRadioButton();
        radioServicio = new javax.swing.JRadioButton();
        radioCliente = new javax.swing.JRadioButton();
        radioEmpleado = new javax.swing.JRadioButton();
        botonSQL = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaConsultas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        radioInsertar = new javax.swing.JRadioButton();
        radioBusqueda = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        radioActualizar = new javax.swing.JRadioButton();
        radioBorra = new javax.swing.JRadioButton();
        boxHistorialC = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 12), java.awt.Color.darkGray)); // NOI18N

        TipoBusqueda.add(radioPalabra);
        radioPalabra.setText("Escribe Palabra");

        TipoBusqueda.add(radioSQL);
        radioSQL.setText("Comando SQL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioPalabra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(radioSQL)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioPalabra)
                    .addComponent(radioSQL))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione Tabla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 12), java.awt.Color.darkGray)); // NOI18N

        jLabel1.setText("Solo funciona con el radio boton \"Escribe Palabra\"");

        Tabla.add(radioPersona);
        radioPersona.setText("Persona");

        Tabla.add(radioEmpresa);
        radioEmpresa.setText("Empresa");

        Tabla.add(radioCelular);
        radioCelular.setText("Celular");

        Tabla.add(radioServicio);
        radioServicio.setText("Servicio");

        Tabla.add(radioCliente);
        radioCliente.setText("Cliente");

        Tabla.add(radioEmpleado);
        radioEmpleado.setText("Empleado");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(radioPersona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioCelular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioServicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioEmpleado))
                    .addComponent(jLabel1))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioPersona)
                    .addComponent(radioEmpresa)
                    .addComponent(radioCelular)
                    .addComponent(radioServicio)
                    .addComponent(radioCliente)
                    .addComponent(radioEmpleado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonSQL.setText("Ejecutar SQL");
        botonSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSQLActionPerformed(evt);
            }
        });

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        tablaConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaConsultas);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Comandos Validos SQL")));

        Tabla.add(radioInsertar);
        radioInsertar.setText("Insertar");

        Tabla.add(radioBusqueda);
        radioBusqueda.setText("Busquedas");

        jLabel2.setText("Solo funcionan con el radio boton \"Comando SQL\"");

        Tabla.add(radioActualizar);
        radioActualizar.setText("Actualizar");

        Tabla.add(radioBorra);
        radioBorra.setText("Borrar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(radioInsertar)
                        .addGap(17, 17, 17)
                        .addComponent(radioBusqueda)
                        .addGap(12, 12, 12)
                        .addComponent(radioActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioBorra))
                    .addComponent(jLabel2))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBusqueda)
                    .addComponent(radioInsertar)
                    .addComponent(radioActualizar)
                    .addComponent(radioBorra))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel3.setText("Historial de Consultas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonSQL)))
                        .addGap(0, 303, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxHistorialC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(438, 438, 438))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxHistorialC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSQL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salir)
                .addContainerGap())
        );

        pack();
    }

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {
        if(jTextField1.getText().isEmpty()){
            modelo=new DefaultTableModel();
        }else            
            try {          
            if(radioPalabra.isSelected()){    
                String tabla=null;
                String attu=null;
                if(radioPersona.isSelected()){
                    tabla=radioPersona.getText();
                    attu="rfc";
                }
                if(radioEmpresa.isSelected()){
                    tabla=radioEmpresa.getText();
                    attu="rfc";
                }
                if(radioCelular.isSelected()){
                    tabla=radioCelular.getText();
                    attu="imei";
                }
                if(radioServicio.isSelected()){
                    tabla=radioServicio.getText();
                    attu="id";
                }
                if(radioCliente.isSelected()){
                    tabla=radioCliente.getText();
                    attu="rfc";
                }
                if(radioEmpleado.isSelected()){
                    tabla=radioEmpleado.getText();
                    attu="rfc";
                }                
                String busqueda=jTextField1.getText();
                ResultSet rs=conexion.buscaDatos("Select * from "+tabla+" where "+attu+" LIKE '"+busqueda+"%';");
                modelo=new DefaultTableModel();
                ResultSetMetaData rsm=rs.getMetaData();
                int numCol=rsm.getColumnCount();                
                this.tablaConsultas.setModel(modelo);
                for(int i=1;i<=numCol;i++){
                    modelo.addColumn(rsm.getColumnLabel(i));
                }
                while(rs.next()){
                    Object [] tupla=new Object[numCol];                    
                    for(int i=0;i<numCol;i++){
                        tupla[i]=rs.getObject(i+1);                        
                    }
                    modelo.addRow(tupla);
                }
            }            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Fallo");            
            }
    }

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new pkgVista.Principal().setVisible(true);
    }

    private void botonSQLActionPerformed(java.awt.event.ActionEvent evt) {
pkgControlador.Conexion con=new  pkgControlador.Conexion();        
        modelo=new DefaultTableModel();
            try {
                if(radioSQL.isSelected()){
                    String sql = jTextField1.getText();
                    if(aux.palabraDrop(sql)){
                        JOptionPane.showMessageDialog(this, "Comando SQL no permitido");   
                    }else{
                        if(radioBusqueda.isSelected()){                        
                        ResultSet rs=con.buscaDatos(sql);
                        ResultSetMetaData rsm=rs.getMetaData();
                        int numCol=rsm.getColumnCount();                
                        this.tablaConsultas.setModel(modelo);
                        for(int i=1;i<=numCol;i++){
                            modelo.addColumn(rsm.getColumnLabel(i));
                        }
                        while(rs.next()){
                            Object [] tupla=new Object[numCol];                    
                            for(int i=0;i<numCol;i++){
                                tupla[i]=rs.getObject(i+1);                        
                            }
                        modelo.addRow(tupla);
                        }
                        String [] opcs = {"Si","No"};
                        String opcion;
                        opcion = (String) showInputDialog(botonSQL, "Guardar Consulta :", "Elige una opcion", 
                                          QUESTION_MESSAGE,null, opcs, 
                                          opcs[0]);                       
                        if(opcion!=null){
                            if(opcion.equals(opcs[0])){
                                conexion.insertar("INSERT INTO Historial VALUES('"+jTextField1.getText()+"');");
                                JOptionPane.showMessageDialog(this,"!!Guardado¡¡");
                            }
                            if(opcion.equals(opcs[1])){
                            JOptionPane.showMessageDialog(this,"!!No se Guardo¡¡");
                            }
                            }else
                            JOptionPane.showMessageDialog(this,"Operacion Cancelada");
                        }
                    
                    if(radioBorra.isSelected())
                        conexion.borrarBD(sql);                        
                    if(radioInsertar.isSelected())    
                        conexion.insertar(sql);
                    if(radioActualizar.isSelected())    
                        conexion.actualizarBD(sql);                    
                    JOptionPane.showMessageDialog(this, "Comando SQL ejecutado");   
                    }                                    
                }    
                jTextField1.setText("");
            }catch (SQLException e) {
            jTextField1.setText("");
            JOptionPane.showMessageDialog(this, "No se pudo ejecutar la consulta");   
        }
    }
       
    private javax.swing.ButtonGroup Tabla;
    private javax.swing.ButtonGroup TipoBusqueda;
    private javax.swing.JButton botonSQL;
    private javax.swing.JComboBox boxHistorialC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton radioActualizar;
    private javax.swing.JRadioButton radioBorra;
    private javax.swing.JRadioButton radioBusqueda;
    private javax.swing.JRadioButton radioCelular;
    private javax.swing.JRadioButton radioCliente;
    private javax.swing.JRadioButton radioEmpleado;
    private javax.swing.JRadioButton radioEmpresa;
    private javax.swing.JRadioButton radioInsertar;
    private javax.swing.JRadioButton radioPalabra;
    private javax.swing.JRadioButton radioPersona;
    private javax.swing.JRadioButton radioSQL;
    private javax.swing.JRadioButton radioServicio;
    private javax.swing.JButton salir;
    private javax.swing.JTable tablaConsultas;
}
