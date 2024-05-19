package com.iudigital.funcionarios.rh.iud.dao;

import com.iudigital.funcionarios.rh.iud.domain.EstadoCivil;
import com.iudigital.funcionarios.rh.iud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoCivilDao {

    private static final String GET_ESTADO_CIVIL = "SELECT * FROM estado_civil ";
    
    public List<EstadoCivil> getEstadosCiviles() throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        List<EstadoCivil> estadosCiviles = new ArrayList<>();
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ESTADO_CIVIL);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                EstadoCivil estadoCivil = new EstadoCivil();
                
                estadoCivil.setId(resultSet.getInt("id_estado_civil"));
                estadoCivil.setNombre(resultSet.getString("nombre"));
                
                estadosCiviles.add(estadoCivil);
            }
            return estadosCiviles;
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
    
}
