package com.iudigital.funcionarios.rh.iud.controller;

import com.iudigital.funcionarios.rh.iud.dao.EstadoCivilDao;
import com.iudigital.funcionarios.rh.iud.domain.EstadoCivil;
import java.sql.SQLException;
import java.util.List;

public class EstadoCivilController {
    
    private EstadoCivilDao estadoCivilDao;
    
    public EstadoCivilController() {
        estadoCivilDao = new EstadoCivilDao();
    }
    
    public List<EstadoCivil> getEstadosCiviles () throws SQLException {
        return estadoCivilDao.getEstadosCiviles();
    }
}
