package com.iudigital.funcionarios.rh.iud.controller;

import com.iudigital.funcionarios.rh.iud.dao.SexoDao;
import com.iudigital.funcionarios.rh.iud.domain.Sexo;
import java.sql.SQLException;
import java.util.List;

public class SexoController {
    
    private SexoDao sexoDao;
    
    public SexoController() {
        sexoDao = new SexoDao();
    }
    
    public List<Sexo> getSexos () throws SQLException {
        return sexoDao.getSexos();
    }
}
