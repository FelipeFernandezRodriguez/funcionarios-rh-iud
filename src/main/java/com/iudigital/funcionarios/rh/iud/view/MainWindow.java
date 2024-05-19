package com.iudigital.funcionarios.rh.iud.view;

import com.iudigital.funcionarios.rh.iud.controller.EstadoCivilController;
import com.iudigital.funcionarios.rh.iud.controller.FuncionarioController;
import com.iudigital.funcionarios.rh.iud.controller.SexoController;
import com.iudigital.funcionarios.rh.iud.controller.TipoDocumentoController;
import com.iudigital.funcionarios.rh.iud.domain.EstadoCivil;
import com.iudigital.funcionarios.rh.iud.domain.Funcionario;
import com.iudigital.funcionarios.rh.iud.domain.Sexo;
import com.iudigital.funcionarios.rh.iud.domain.TipoDocumento;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends javax.swing.JFrame {

    private final FuncionarioController funcionarioController;
    private final TipoDocumentoController tipoDocumentoController;
    private final SexoController sexoController;
    private final EstadoCivilController estadoCivilController;
    private static final String[] COLUMNS = {"ID", "ID TIPO DOCUMENTO", "TIPO DOCUMENTO",
            "NUMERO IDENTIFICACION", "NOMBRES", "APELLIDOS", "ID SEXO","SEXO", "ID ESTADO CIVIL", 
            "ESTADO CIVIL", "DIRECCIÓN", "TELÉFONO", "FECHA DE NACIMIENTO"};
    private static final String TIPOS_DOCUMENTOS = "-- TIPOS DOCUMENTOS --";
    private static final String SEXOS = "-- SEXOS --";
    private static final String ESTADO_CIVIL = "-- ESTADOS CIVILES --";
    private static final String SELECCIONE = "-- SELECCIONE --";
    
    public MainWindow() {
        initComponents();
        
        funcionarioController = new FuncionarioController();
        tipoDocumentoController = new TipoDocumentoController();
        sexoController = new SexoController();
        estadoCivilController = new EstadoCivilController();
        txtIdFuncionario.setEditable(false);
        listFuncionarios();
        listTiposDocumentos();
        listSexos();
        listEstadoCivil();
        addListener();
        
    }

    private void listFuncionarios(){
        cbxFuncionarios.removeAllItems();
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setNombres(SELECCIONE);
        funcionario1.setApellidos("");
        cbxFuncionarios.addItem(funcionario1);
        
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        for (String COLUMN : COLUMNS) {
            defaultTableModel.addColumn(COLUMN);
        }
        
        tblFuncionarios.setModel(defaultTableModel);
        
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            if (funcionarios.isEmpty()){
                return;
            }
            defaultTableModel.setRowCount(funcionarios.size());
            int row = 0;
            for (Funcionario funcionario : funcionarios){
                defaultTableModel.setValueAt(funcionario.getId(), row, 0);
                defaultTableModel.setValueAt(funcionario.getDocumento(), row, 1);
                defaultTableModel.setValueAt(funcionario.getDocumentoNombre(), row, 2);
                defaultTableModel.setValueAt(funcionario.getNumeroDocumento(), row, 3);
                defaultTableModel.setValueAt(funcionario.getNombres(), row, 4);
                defaultTableModel.setValueAt(funcionario.getApellidos(), row, 5);
                defaultTableModel.setValueAt(funcionario.getSexo(), row, 6);
                defaultTableModel.setValueAt(funcionario.getSexoNombre(), row, 7);
                defaultTableModel.setValueAt(funcionario.getEstadoCivil(), row, 8);
                defaultTableModel.setValueAt(funcionario.getEstadoCivilNombre(), row, 9);
                defaultTableModel.setValueAt(funcionario.getDireccion(), row, 10);
                defaultTableModel.setValueAt(funcionario.getTelefono(), row, 11);
                defaultTableModel.setValueAt(funcionario.getFechaNacimiento(), row, 12);
                row++;
                
                cbxFuncionarios.addItem(funcionario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void listTiposDocumentos() {
        cbxTipoDocumento.removeAllItems();
        TipoDocumento tipoDocumento1 = new TipoDocumento();
        tipoDocumento1.setNombre(TIPOS_DOCUMENTOS);
        cbxTipoDocumento.addItem(tipoDocumento1);
        try {
            List<TipoDocumento> tiposDocumentos = tipoDocumentoController.getTiposDocumentos();
            for (TipoDocumento tipoDocumento : tiposDocumentos){
                cbxTipoDocumento.addItem(tipoDocumento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void listSexos() {
        cbxSexo.removeAllItems();
        Sexo sexo1 = new Sexo();
        sexo1.setNombre(SEXOS);
        cbxSexo.addItem(sexo1);
        try {
            List<Sexo> sexos = sexoController.getSexos();
            for (Sexo sexo : sexos){
                cbxSexo.addItem(sexo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void listEstadoCivil() {
        cbxEstadoCivil.removeAllItems();
        EstadoCivil estadoCivil1 = new EstadoCivil();
        estadoCivil1.setNombre(ESTADO_CIVIL);
        cbxEstadoCivil.addItem(estadoCivil1);
        try {
            List<EstadoCivil> estadosCiviles = estadoCivilController.getEstadosCiviles();
            for (EstadoCivil estadoCivil : estadosCiviles){
                cbxEstadoCivil.addItem(estadoCivil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void addListener(){
        cbxFuncionarios.addItemListener(event -> {
           Funcionario selectedFuncionario = (Funcionario) event.getItem();
           if (selectedFuncionario.getNombres().equals(SELECCIONE)){
               txtIdFuncionario.setText("");
               txtIdTipoDocumentoEdit.setText("");
               txtTipoDocumentoEdit.setText("");
               txtNumeroDocumentoEdit.setText("");
               txtNombresEdit.setText("");
               txtApellidosEdit.setText("");
               txtIdSexoEdit.setText("");
               txtSexoEdit.setText("");
               txtIdEstadoCivilEdit.setText("");    
               txtEstadoCivilEdit.setText("");               
               txtDireccionEdit.setText("");
               txtTelefonoEdit.setText("");
               txtFechaNacimientoEdit.setText("");
           } else {
               txtIdFuncionario.setText(String.valueOf(selectedFuncionario.getId()));
               txtIdTipoDocumentoEdit.setText(selectedFuncionario.getDocumento());
               txtTipoDocumentoEdit.setText(selectedFuncionario.getDocumentoNombre());
               txtNumeroDocumentoEdit.setText(selectedFuncionario.getNumeroDocumento());
               txtNombresEdit.setText(selectedFuncionario.getNombres());
               txtApellidosEdit.setText(selectedFuncionario.getApellidos());
               txtIdSexoEdit.setText(selectedFuncionario.getSexo());
               txtSexoEdit.setText(selectedFuncionario.getSexoNombre());
               txtIdEstadoCivilEdit.setText(selectedFuncionario.getEstadoCivil());
               txtEstadoCivilEdit.setText(selectedFuncionario.getEstadoCivilNombre());
               txtDireccionEdit.setText(selectedFuncionario.getDireccion());
               txtTelefonoEdit.setText(selectedFuncionario.getTelefono());
               txtFechaNacimientoEdit.setText(String.valueOf(selectedFuncionario.getFechaNacimiento()));
           }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbxTipoDocumento = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumeroIdentificacion = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbxSexo = new javax.swing.JComboBox<>();
        cbxEstadoCivil = new javax.swing.JComboBox<>();
        txtDireccion = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionarios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbxFuncionarios = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtIdTipoDocumentoEdit = new javax.swing.JTextField();
        txtIdFuncionario = new javax.swing.JTextField();
        txtTipoDocumentoEdit = new javax.swing.JTextField();
        txtNombresEdit = new javax.swing.JTextField();
        txtSexoEdit = new javax.swing.JTextField();
        txtIdSexoEdit = new javax.swing.JTextField();
        txtApellidosEdit = new javax.swing.JTextField();
        txtIdEstadoCivilEdit = new javax.swing.JTextField();
        txtDireccionEdit = new javax.swing.JTextField();
        txtEstadoCivilEdit = new javax.swing.JTextField();
        txtTelefonoEdit = new javax.swing.JTextField();
        txtFechaNacimientoEdit = new javax.swing.JTextField();
        txtNumeroDocumentoEdit = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("GESTIÓN DE FUNCIONARIOS");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Llenar los siguientes campos"));

        cbxTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoDocumentoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo Documento");

        jLabel3.setText("Numero de Identificación");

        jLabel6.setText("Nombres");

        jLabel7.setText("Apellidos");

        jLabel4.setText("Sexo");

        jLabel5.setText("Estado Civil");

        jLabel8.setText("Dirección");

        jLabel9.setText("Teléfono");

        jLabel10.setText("Fecha de Nacimiento");

        cbxSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSexoActionPerformed(evt);
            }
        });

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(txtFechaNacimiento)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumeroIdentificacion)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblFuncionarios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("GUARDAR / LISTAR", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar los siguientes datos para actualizar"));

        jLabel11.setText("FUNCIONARIOS");

        cbxFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFuncionariosActionPerformed(evt);
            }
        });

        jLabel12.setText("ID");

        txtIdTipoDocumentoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTipoDocumentoEditActionPerformed(evt);
            }
        });

        txtIdFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdFuncionarioActionPerformed(evt);
            }
        });

        txtTipoDocumentoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoDocumentoEditActionPerformed(evt);
            }
        });

        txtNombresEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresEditActionPerformed(evt);
            }
        });

        txtSexoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSexoEditActionPerformed(evt);
            }
        });

        txtIdSexoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSexoEditActionPerformed(evt);
            }
        });

        txtApellidosEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosEditActionPerformed(evt);
            }
        });

        txtIdEstadoCivilEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEstadoCivilEditActionPerformed(evt);
            }
        });

        txtDireccionEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionEditActionPerformed(evt);
            }
        });

        txtEstadoCivilEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoCivilEditActionPerformed(evt);
            }
        });

        txtTelefonoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoEditActionPerformed(evt);
            }
        });

        txtFechaNacimientoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoEditActionPerformed(evt);
            }
        });

        txtNumeroDocumentoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroDocumentoEditActionPerformed(evt);
            }
        });

        btnUpdate.setText("ACTUALIZAR");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("ELIMINAR");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel13.setText("ID Tipo Documento");

        jLabel14.setText("Tipo Documento");

        jLabel15.setText("# Documento");

        jLabel16.setText("Nombres");

        jLabel17.setText("Apellidos");

        jLabel18.setText("ID Sexo");

        jLabel19.setText("Sexo");

        jLabel20.setText("Estado Civil");

        jLabel21.setText("ID Estado Civil");

        jLabel22.setText("Dirección");

        jLabel23.setText("Teléfono");

        jLabel24.setText("Fecha de Nacimiento");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                    .addGap(14, 14, 14)
                                                    .addComponent(txtIdSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnUpdate)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTipoDocumentoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(txtSexoEdit))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)))))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdTipoDocumentoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumeroDocumentoEdit))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEstadoCivilEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 18, Short.MAX_VALUE))
                            .addComponent(txtNombresEdit)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txtIdEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdTipoDocumentoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoDocumentoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroDocumentoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombresEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("EDITAR / ELIMINAR", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void cbxSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSexoActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (cbxTipoDocumento.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de documento de la lista");
            cbxTipoDocumento.requestFocus();
            return;
        }
        
        if(txtNumeroIdentificacion.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el número de identificación");
            txtNumeroIdentificacion.requestFocus();
            return;
        }
        
        if(txtNombres.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el nombre");
            txtNombres.requestFocus();
            return;
        }
        
        if(txtApellidos.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el apellido");
            txtApellidos.requestFocus();
            return;
        }        
        
        if (cbxSexo.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un sexo de la lista");
            cbxSexo.requestFocus();
            return;
        }
        
        if (cbxEstadoCivil.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado civil de la lista");
            cbxEstadoCivil.requestFocus();
            return;
        }
       
        if(txtDireccion.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite la dirección");
            txtDireccion.requestFocus();
            return;
        }
        
        if(txtTelefono.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el telefono");
            txtTelefono.requestFocus();
            return;
        }
        
        if(txtFechaNacimiento.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite la fecha de nacimiento");
            txtFechaNacimiento.requestFocus();
            return;
        }
        
        try {
            Funcionario funcionario = new Funcionario();

            funcionario.setDocumento(String.valueOf(cbxTipoDocumento.getSelectedIndex()+1)); 
            funcionario.setDocumentoNombre(txtTipoDocumentoEdit.getText().trim());
            funcionario.setNumeroDocumento(txtNumeroIdentificacion.getText().trim());
            funcionario.setNombres(txtNombres.getText().trim());
            funcionario.setApellidos(txtApellidos.getText().trim());
            funcionario.setSexo(String.valueOf(cbxSexo.getSelectedIndex()+1)); 
            funcionario.setSexoNombre(txtSexoEdit.getText().trim());
            funcionario.setEstadoCivil(String.valueOf(cbxEstadoCivil.getSelectedIndex()+1));
            funcionario.setEstadoCivilNombre(txtEstadoCivilEdit.getText().trim());
            funcionario.setDireccion(txtDireccion.getText().trim());
            funcionario.setTelefono(txtTelefono.getText().trim());

            String strFecha = txtFechaNacimiento.getText().trim();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = null;
            try {
                utilDate = formatter.parse(strFecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            funcionario.setFechaNacimiento(sqlDate);


            funcionarioController.crear(funcionario);           
            
            cbxTipoDocumento.setSelectedIndex(-1);
            txtNumeroIdentificacion.setText("");
            txtNombres.setText("");
            txtApellidos.setText("");
            cbxSexo.setSelectedIndex(-1);
            cbxEstadoCivil.setSelectedIndex(-1);
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtFechaNacimiento.setText("");

            listFuncionarios();
            
            JOptionPane.showMessageDialog(null, "Funcionario creado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo crear el funcionario");
        }      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFuncionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFuncionariosActionPerformed

    private void txtIdTipoDocumentoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTipoDocumentoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTipoDocumentoEditActionPerformed

    private void txtIdFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdFuncionarioActionPerformed

    private void txtTipoDocumentoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoDocumentoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocumentoEditActionPerformed

    private void txtNombresEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresEditActionPerformed

    private void txtSexoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSexoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoEditActionPerformed

    private void txtIdSexoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSexoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSexoEditActionPerformed

    private void txtApellidosEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosEditActionPerformed

    private void txtIdEstadoCivilEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEstadoCivilEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEstadoCivilEditActionPerformed

    private void txtDireccionEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionEditActionPerformed

    private void txtEstadoCivilEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoCivilEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoCivilEditActionPerformed

    private void txtTelefonoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoEditActionPerformed

    private void txtFechaNacimientoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoEditActionPerformed

    private void txtNumeroDocumentoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroDocumentoEditActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        
        if(txtIdFuncionario.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione un funcionario de la lista");
            txtIdFuncionario.requestFocus();
            return;
        }
        
        if(txtIdTipoDocumentoEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el número de identificación");
            txtIdTipoDocumentoEdit.requestFocus();
            return;
        }
        
        if(txtNombresEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el nombre");
            txtNombresEdit.requestFocus();
            return;
        }
        
        if(txtApellidosEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el apellido");
            txtApellidosEdit.requestFocus();
            return;
        }
        
        if(txtIdSexoEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el estado civil");
            txtIdSexoEdit.requestFocus();
            return;
        }
        
        if(txtSexoEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el sexo");
            txtSexoEdit.requestFocus();
            return;
        }
        
        if(txtIdEstadoCivilEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el sexo");
            txtIdEstadoCivilEdit.requestFocus();
            return;
        }
        
        if(txtEstadoCivilEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el sexo");
            txtEstadoCivilEdit.requestFocus();
            return;
        }
        
        if(txtDireccionEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite la dirección");
            txtDireccionEdit.requestFocus();
            return;
        }
        
        if(txtTelefonoEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite el telefono");
            txtTelefonoEdit.requestFocus();
            return;
        }
        
        if(txtFechaNacimientoEdit.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Digite la fecha de nacimiento");
            txtFechaNacimientoEdit.requestFocus();
            return;
        }

        Funcionario funcionario = new Funcionario();

        funcionario.setDocumento(txtIdTipoDocumentoEdit.getText().trim());
        funcionario.setDocumentoNombre(txtTipoDocumentoEdit.getText().trim());
        funcionario.setNumeroDocumento(txtNumeroDocumentoEdit.getText().trim());
        funcionario.setNombres(txtNombresEdit.getText().trim());
        funcionario.setApellidos(txtApellidosEdit.getText().trim());
        funcionario.setSexo(txtIdSexoEdit.getText().trim());
        funcionario.setSexoNombre(txtSexoEdit.getText().trim());
        funcionario.setEstadoCivil(txtIdEstadoCivilEdit.getText().trim());
        funcionario.setEstadoCivilNombre(txtEstadoCivilEdit.getText().trim());
        funcionario.setDireccion(txtDireccionEdit.getText().trim());
        funcionario.setTelefono(txtTelefonoEdit.getText().trim());

        String strFecha = txtFechaNacimientoEdit.getText().trim();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = formatter.parse(strFecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        funcionario.setFechaNacimiento(sqlDate);

        int opt = JOptionPane.showConfirmDialog(null, "¿Desea actualizar el funcionario?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opt == 0) {
            try {
                funcionarioController.actualizarFuncionario(Integer.parseInt(txtIdFuncionario.getText()), funcionario);

                txtIdFuncionario.setText("");
                txtIdTipoDocumentoEdit.setText("");
                txtTipoDocumentoEdit.setText("");                
                txtNumeroDocumentoEdit.setText("");
                txtNombresEdit.setText("");
                txtApellidosEdit.setText("");
                txtIdSexoEdit.setText("");
                txtSexoEdit.setText("");
                txtIdEstadoCivilEdit.setText("");
                txtEstadoCivilEdit.setText("");
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");

                listFuncionarios();

                JOptionPane.showMessageDialog(null, "Funcionario actualizado correctamente");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el funcionario");
            }
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        if(txtIdFuncionario.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione un funcionario de la lista");
            txtIdFuncionario.requestFocus();
            return;
        }
        
        int opt = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el funcionario?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opt == 0) {
            try {
                funcionarioController.eliminarFuncionario(Integer.parseInt(txtIdFuncionario.getText()));

                txtIdFuncionario.setText("");
                txtIdTipoDocumentoEdit.setText("");
                txtTipoDocumentoEdit.setText("");                
                txtNumeroDocumentoEdit.setText("");
                txtNombresEdit.setText("");
                txtApellidosEdit.setText("");
                txtIdSexoEdit.setText("");
                txtSexoEdit.setText("");
                txtIdEstadoCivilEdit.setText("");
                txtEstadoCivilEdit.setText("");
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");

                listFuncionarios();

                JOptionPane.showMessageDialog(null, "Funcionario eliminado correctamente");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el funcionario");
            }
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

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
                if ("Window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<EstadoCivil> cbxEstadoCivil;
    private javax.swing.JComboBox<Funcionario> cbxFuncionarios;
    private javax.swing.JComboBox<Sexo> cbxSexo;
    private javax.swing.JComboBox<TipoDocumento> cbxTipoDocumento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblFuncionarios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosEdit;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionEdit;
    private javax.swing.JTextField txtEstadoCivilEdit;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFechaNacimientoEdit;
    private javax.swing.JTextField txtIdEstadoCivilEdit;
    private javax.swing.JTextField txtIdFuncionario;
    private javax.swing.JTextField txtIdSexoEdit;
    private javax.swing.JTextField txtIdTipoDocumentoEdit;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombresEdit;
    private javax.swing.JTextField txtNumeroDocumentoEdit;
    private javax.swing.JTextField txtNumeroIdentificacion;
    private javax.swing.JTextField txtSexoEdit;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoEdit;
    private javax.swing.JTextField txtTipoDocumentoEdit;
    // End of variables declaration//GEN-END:variables
}
