/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.dev.controllers;

import java.io.Serializable;
import javax.inject.Named;
import javax.ejb.EJB;

import com.sena.dev.entities.Usuario;
import com.sena.dev.services.UsuarioFacadeLocal;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

/**
 *
 * @author david
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    private String usuario;
    private String contrasenna;
    private Usuario usuarioActual = new Usuario();

    @EJB
    private UsuarioFacadeLocal ufl;
    
    public Login() {
    }
    
    public String iniciarSesion() {
        usuarioActual = this.ufl.iniciarSesion(usuario, contrasenna);
        if (usuarioActual != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", this);
            System.out.println("Login exitoso, redirigiendo a welcome.xhtml");
            return "welcome?faces-redirect=true";
        } else {
            System.out.println("Login fallido");
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, usuario + contrasenna, 
                "Usuario o contraseña incorrectos"));
            return null;
        }

        // System.out.println("Intentando iniciar sesión con usuario: " + usuario + " y contraseña: " + contrasenna);
        // if ("admin".equals(usuario) && "admin123".equals(contrasenna)) {
        //     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", this);
        //     System.out.println("Login exitoso, redirigiendo a welcome.xhtml");
        //     return "welcome?faces-redirect=true";
        // } else {
        //     System.out.println("Login fallido");
        //     FacesContext.getCurrentInstance().addMessage(null,
        //         new FacesMessage(FacesMessage.SEVERITY_ERROR, usuario+contrasenna, 
        //         "Usuario o contraseña incorrectos"));
        //     return null;
        // }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }
}
