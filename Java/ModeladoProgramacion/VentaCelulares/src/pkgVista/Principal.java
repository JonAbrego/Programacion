
package pkgVista;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import javax.swing.table.DefaultTableModel;
import pkgModelo.Auxiliares;

/**
 *
 * @author abrego
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Constructor
     */
    public Principal() {
        initComponents();
    }

    /**
     * Metodo usado en el constructor para inicializar el frame  
     * con los campos necesarios para su uso
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        botonAvanzadas = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();
        boxConsultas = new javax.swing.JComboBox();
        botonConsultar = new javax.swing.JButton();
        panel = new javax.swing.JScrollPane();
        tablaConsultas = new javax.swing.JTable();
        botonInsertar = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Principal");

        botonAvanzadas.setText("Opciones Avanzadas");
        botonAvanzadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonAvanzadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAvanzadasActionPerformed(evt);
            }
        });

        botonActualizar.setText("Actualizar");
        botonActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        boxConsultas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "El empleado que ha hecho mas ventas", "La marca de telefonos mas vendidad", "La compañia que mas telefono ha vendido", "Clientes que hicieron al menos 1 compra ", "Empleado que gana mas en cada compañia" }));

        botonConsultar.setText("Consultar");
        botonConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarActionPerformed(evt);
            }
        });

        tablaConsultas.setForeground(new java.awt.Color(145, 131, 131));
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
        tablaConsultas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        panel.setViewportView(tablaConsultas);

        botonInsertar.setText("Insertar");
        botonInsertar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInsertarActionPerformed(evt);
            }
        });

        botonBorrar.setText("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(botonInsertar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonBorrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonAvanzadas))
                            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boxConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonConsultar)
                        .addGap(0, 282, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAvanzadas)
                    .addComponent(botonActualizar)
                    .addComponent(botonInsertar)
                    .addComponent(botonBorrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonConsultar))
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    private void botonInsertarActionPerformed(java.awt.event.ActionEvent evt) {
        pkgVista.FormularioPersona per = new pkgVista.FormularioPersona();
        pkgVista.FormularioEmpresa emp=new pkgVista.FormularioEmpresa();
        pkgVista.FormularioCelular tel=new pkgVista.FormularioCelular();
        pkgVista.FormularioServicio ser = new pkgVista.FormularioServicio();               
        String [] opcs = {"Persona", "Empresa","Celular","Servicio","Compra"};
        String opcion;
        opcion = (String) showInputDialog(botonInsertar, "Ingresar :", "Elige una opcion", 
                                          QUESTION_MESSAGE,null, opcs, 
                                          opcs[0]);   
        if(opcion!=null){
            if(opcion.equals(opcs[0])){
                per.setVisible(true);
                this.setVisible(false);
            }
            if(opcion.equals(opcs[1])){
                emp.setVisible(true);
                this.setVisible(false);
            }
            if(opcion.equals(opcs[2])){
                tel.setVisible(true);
                this.setVisible(false);
            }
            if(opcion.equals(opcs[3])){
               ser.setVisible(true);
               this.setVisible(false);
            }
            if(opcion.equals(opcs[4])){
               try {
                   new pkgVista.FormularioCompra().setVisible(true);
                   this.setVisible(false);
               } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(this,"Fallo en el sistema");
               }  
            }
        }else{
            JOptionPane.showMessageDialog(this,"Operacion Cancelada");
        }
    }

    private void botonConsultarActionPerformed(java.awt.event.ActionEvent evt) {
        pkgControlador.Conexion con=new  pkgControlador.Conexion();
        pkgModelo.Auxiliares aux=new Auxiliares();
            try {
                String sql = aux.consultas(boxConsultas.getSelectedIndex());
                ResultSet rs=con.buscaDatos(sql);
                ResultSetMetaData rsm=rs.getMetaData();
                int numCol=rsm.getColumnCount();
                DefaultTableModel mod =new DefaultTableModel();
                this.tablaConsultas.setModel(mod);
                for(int i=1;i<=numCol;i++){
                    mod.addColumn(rsm.getColumnLabel(i));
                }
                while(rs.next()){
                   Object [] tupla=new Object[numCol];                    
                   for(int i=0;i<numCol;i++){
                        tupla[i]=rs.getObject(i+1);                        
                    }
                   mod.addRow(tupla);
                }
                con.desconectar();
            }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "No se pudo hacer la consulta");   
        }
    }

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String [] opcs = {"Persona","Empresa","Celular","Servicio","Empleado"};
            String opcion;
            opcion = (String) showInputDialog(botonActualizar, "Modificar :", "Elige una opcion", 
                                          QUESTION_MESSAGE,null, opcs, 
                                          opcs[0]);   
            if(opcion!=null){
                if(opcion.equals(opcs[0])){
                    new ActualizarPersona().setVisible(true);
                    this.setVisible(false);
                }
                if(opcion.equals(opcs[1])){
                    new ActualizarEmpresa().setVisible(true);
                    this.setVisible(false);
                }
                if(opcion.equals(opcs[2])){
                    new ActualizarCelular().setVisible(true);
                    this.setVisible(false);
                }
                if(opcion.equals(opcs[3])){
                    new ActualizarServicio().setVisible(true);
                    this.setVisible(false);
                }
                if(opcion.equals(opcs[4])){
                    new ActualizarEmpleado().setVisible(true);
                    this.setVisible(false);
                }
            }else{
                JOptionPane.showMessageDialog(this,"Operacion Cancelada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Fallo en el sistema");
        }
    }

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String [] opcs = {"Persona","Empresa","Celular","Servicio"};
            String opcion;
            opcion = (String) showInputDialog(botonBorrar, "Borrar :", "Elige una opcion", 
                                          QUESTION_MESSAGE,null, opcs, 
                                          opcs[0]);   
            if(opcion!=null){
                if(opcion.equals(opcs[0])){
                    new BorrarPersona().setVisible(true);
                    this.setVisible(false);
                }
                if(opcion.equals(opcs[1])){
                    new BorrarEmpresa().setVisible(true);
                    this.setVisible(false);
                }
                if(opcion.equals(opcs[2])){
                    new BorrarCelular().setVisible(true);
                    this.setVisible(false);
                }
                if(opcion.equals(opcs[3])){
                    new BorrarServicio().setVisible(true);
                    this.setVisible(false);
                }
            }else{
                JOptionPane.showMessageDialog(this,"Operacion Cancelada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Fallo en el sistema");
        }
    }

    private void botonAvanzadasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            this.setVisible(false);
            new OpcionesAvanzadas().setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Fallo en el sistema");
        }
    }

    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAvanzadas;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonConsultar;
    private javax.swing.JButton botonInsertar;
    private javax.swing.JComboBox boxConsultas;
    private javax.swing.JScrollPane panel;
    private javax.swing.JTable tablaConsultas;

}
