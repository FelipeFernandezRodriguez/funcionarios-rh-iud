package com.iudigital.funcionarios.rh.iud.dao;

import com.iudigital.funcionarios.rh.iud.domain.TipoDocumento;
import com.iudigital.funcionarios.rh.iud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDao {
    
    private static final String GET_TIPO_DOCUMENTO = "SELECT * FROM tipo_documento ";
    
    public List<TipoDocumento> getTiposDocumentos() throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        List<TipoDocumento> tiposDocumentos = new ArrayList<>();
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_TIPO_DOCUMENTO);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                TipoDocumento tipoDocumento = new TipoDocumento();
                
                tipoDocumento.setId(resultSet.getInt("id_tipo_documento"));
                tipoDocumento.setNombre(resultSet.getString("nombre"));
                
                tiposDocumentos.add(tipoDocumento);
            }
            return tiposDocumentos;
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
