package com.iudigital.funcionarios.rh.iud.domain;

import java.util.Date;

public class Funcionario {
    
    private int id;
    private String documento;
    private String documentoNombre;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String sexo;
    private String sexoNombre;
    private String estadoCivil;
    private String estadoCivilNombre;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;

    public Funcionario() {
    }

    public Funcionario(int id) {
        this.id = id;
    }

    public Funcionario(int id, String documento, String documentoNombre, String numeroDocumento, String nombres, String apellidos, String sexo, String sexoNombre, String estadoCivil, String estadoCivilNombre, String direccion, String telefono, Date fechaNacimiento) {
        this.id = id;
        this.documento = documento;
        this.documentoNombre = documentoNombre;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.sexoNombre = sexoNombre;
        this.estadoCivil = estadoCivil;
        this.estadoCivilNombre = estadoCivilNombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumentoNombre() {
        return documentoNombre;
    }

    public void setDocumentoNombre(String documentoNombre) {
        this.documentoNombre = documentoNombre;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexoNombre() {
        return sexoNombre;
    }

    public void setSexoNombre(String sexoNombre) {
        this.sexoNombre = sexoNombre;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEstadoCivilNombre() {
        return estadoCivilNombre;
    }

    public void setEstadoCivilNombre(String estadoCivilNombre) {
        this.estadoCivilNombre = estadoCivilNombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
    @Override
    public String toString() {
        return this.nombres + " " + this.apellidos;
    }  
    
    
}
