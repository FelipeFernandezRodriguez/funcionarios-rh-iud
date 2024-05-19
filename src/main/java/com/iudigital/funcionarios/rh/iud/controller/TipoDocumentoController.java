package com.iudigital.funcionarios.rh.iud.controller;

import com.iudigital.funcionarios.rh.iud.dao.TipoDocumentoDao;
import com.iudigital.funcionarios.rh.iud.domain.TipoDocumento;
import java.sql.SQLException;
import java.util.List;

public class TipoDocumentoController {
    
    private TipoDocumentoDao tipoDocumentoDao;
    
    public TipoDocumentoController() {
        tipoDocumentoDao = new TipoDocumentoDao();
    }
    
    public List<TipoDocumento> getTiposDocumentos () throws SQLException {
        return tipoDocumentoDao.getTiposDocumentos();
    }
}
