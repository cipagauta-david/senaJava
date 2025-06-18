/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.dev.controllers;

import com.sena.dev.entities.NivelAcademico;
import java.io.Serializable;
import javax.inject.Named;
import javax.ejb.EJB;

import com.sena.dev.services.NivelAcademicoFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;

/**
 *
 * @author david
 */
@Named(value = "nivelAcademicoController")
@SessionScoped
public class NivelAcademicoController implements Serializable {
    private NivelAcademico nA = new NivelAcademico();
    
    @EJB
    private NivelAcademicoFacadeLocal nAFL;

    public NivelAcademico getnA() {
        return nA;
    }

    public void setnA(NivelAcademico nA) {
        this.nA = nA;
    }
    
    public List<NivelAcademico> listarNivelesAcademicos() {
        try {
            List<NivelAcademico> a = this.nAFL.findAll();
            System.out.println(a);
             return a;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Creates a new instance of NivelAcademicoController
     */
    public NivelAcademicoController() {
    }

    @PostConstruct
    public void init() {
        if (nAFL == null) {
            System.out.println("NivelAcademicoFacadeLocal no está inyectado correctamente.");
        }
    }
}
