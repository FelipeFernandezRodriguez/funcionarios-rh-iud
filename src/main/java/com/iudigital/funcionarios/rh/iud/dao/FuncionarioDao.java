package com.iudigital.funcionarios.rh.iud.dao;

import com.iudigital.funcionarios.rh.iud.domain.Funcionario;
import com.iudigital.funcionarios.rh.iud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncionarioDao {

    private static final String GET_FUNCIONARIOS = "SELECT funcionarios.*, "
            + "tipo_documento.nombre AS tipo_documento_nombre, "
            + "sexo.nombre AS sexo_nombre, "
            + "estado_civil.nombre AS estado_civil_nombre "
            + "FROM funcionarios "
            + "INNER JOIN tipo_documento on funcionarios.id_tipo_documento = tipo_documento.id_tipo_documento "
            + "INNER JOIN sexo on funcionarios.id_sexo = sexo.id_sexo "
            + "INNER JOIN estado_civil on funcionarios.id_estado_civil = estado_civil.id_estado_civil  "
            + "ORDER BY funcionarios.id_funcionario ";
    
    private static final String CREATE_FUNCIONARIO = "INSERT INTO funcionarios (id_tipo_documento, numero_identificacion, nombres, apellidos, id_sexo, id_estado_civil, direccion, telefono, fecha_de_nacimiento) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    
    private static final String GET_FUNCIONARIO_BY_ID = "SELECT funcionarios.*, "
            + "tipo_documento.nombre AS tipo_documento_nombre, "
            + "sexo.nombre AS sexo_nombre, "
            + "estado_civil.nombre AS estado_civil_nombre "
            + "FROM funcionarios "
            + "INNER JOIN tipo_documento on funcionarios.id_tipo_documento = tipo_documento.id_tipo_documento "
            + "INNER JOIN sexo on funcionarios.id_sexo = sexo.id_sexo "
            + "INNER JOIN estado_civil on funcionarios.id_estado_civil = estado_civil.id_estado_civil  "
            + "WHERE id_funcionario = ? ";
    
    private static final String UPDATE_FUNCIONARIO = "UPDATE funcionarios SET id_tipo_documento = ?, numero_identificacion = ?, nombres = ?, apellidos = ?, id_sexo = ?, id_estado_civil = ?, direccion = ?, telefono = ?, fecha_de_nacimiento = ? WHERE id_funcionario = ? ";
    
    private static final String DELETE_FUNCIONARIO = "DELETE FROM funcionarios WHERE id_funcionario = ? ";


    
    public List<Funcionario> obtenerFuncionarios() throws SQLException {        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIOS);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId(resultSet.getInt("id_funcionario"));
                funcionario.setDocumento(resultSet.getString("id_tipo_documento"));
                funcionario.setDocumentoNombre(resultSet.getString("tipo_documento_nombre"));
                funcionario.setNumeroDocumento(resultSet.getString("numero_identificacion"));
                funcionario.setNombres(resultSet.getString("nombres"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setSexo(resultSet.getString("id_sexo"));
                funcionario.setSexoNombre(resultSet.getString("sexo_nombre"));
                funcionario.setEstadoCivil(resultSet.getString("id_estado_civil"));
                funcionario.setEstadoCivilNombre(resultSet.getString("estado_civil_nombre"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getDate("fecha_de_Nacimiento"));
                
                funcionarios.add(funcionario);
            }
            return funcionarios;
        } finally {
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (resultSet != null){
                resultSet.close();
            }
        }        
    }
    
    
    public void crear (Funcionario funcionario) throws SQLException {        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
              
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(CREATE_FUNCIONARIO);
            
            preparedStatement.setInt(1, Integer.parseInt(funcionario.getDocumento()));
            preparedStatement.setString(2, funcionario.getNumeroDocumento());
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setInt(5, Integer.parseInt(funcionario.getSexo()));
            preparedStatement.setInt(6, Integer.parseInt(funcionario.getEstadoCivil()));
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setDate(9, (java.sql.Date) funcionario.getFechaNacimiento());
            
            preparedStatement.executeUpdate();

            } finally {
                if (connection != null){
                    connection.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }        
    }


    public Funcionario obtenerFuncionario (int id) throws SQLException {        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Funcionario funcionario = null;
                
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIO_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()){
                funcionario = new Funcionario();
                
                funcionario.setId(resultSet.getInt("id_funcionario"));
                funcionario.setDocumento(resultSet.getString("id_tipo_documento"));
                funcionario.setDocumentoNombre(resultSet.getString("tipo_documento_nombre"));
                funcionario.setNumeroDocumento(resultSet.getString("numero_identificacion"));
                funcionario.setNombres(resultSet.getString("nombres"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setSexo(resultSet.getString("id_sexo"));
                funcionario.setSexoNombre(resultSet.getString("sexo_nombre"));
                funcionario.setEstadoCivil(resultSet.getString("id_estado_civil"));
                funcionario.setEstadoCivilNombre(resultSet.getString("estado_civil_nombre"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getDate("fecha_Nacimiento"));                               
            }
            return funcionario;
        } finally {
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (resultSet != null){
                resultSet.close();
            }
        }  
    }

    public void actualizarFuncionario (int id, Funcionario funcionario) throws SQLException {        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
              
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(UPDATE_FUNCIONARIO);
            
            preparedStatement.setInt(1, Integer.parseInt(funcionario.getDocumento()));
            preparedStatement.setString(2, funcionario.getNumeroDocumento());
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setInt(5, Integer.parseInt(funcionario.getSexo()));
            preparedStatement.setInt(6, Integer.parseInt(funcionario.getEstadoCivil()));
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setDate(9, (java.sql.Date) funcionario.getFechaNacimiento());
            preparedStatement.setInt(10, id);
            
            preparedStatement.executeUpdate();

            } finally {
                if (connection != null){
                    connection.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }        
    }
    
    public void eliminarFuncionario (int id) throws SQLException {        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
                
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNCIONARIO);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }  
    }
        
}
