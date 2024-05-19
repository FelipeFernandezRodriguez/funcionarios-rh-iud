package com.iudigital.funcionarios.rh.iud.dao;

import com.iudigital.funcionarios.rh.iud.domain.Sexo;
import com.iudigital.funcionarios.rh.iud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SexoDao {
    
    private static final String GET_SEX = "SELECT * FROM sexo ";
    
    public List<Sexo> getSexos() throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        List<Sexo> sexos = new ArrayList<>();
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_SEX);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                Sexo sexo = new Sexo();
                
                sexo.setId(resultSet.getInt("id_sexo"));
                sexo.setNombre(resultSet.getString("nombre"));
                
                sexos.add(sexo);
            }
            return sexos;
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
