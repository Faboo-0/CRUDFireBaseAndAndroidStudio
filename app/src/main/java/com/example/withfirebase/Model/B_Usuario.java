package com.example.withfirebase.Model;

public class B_Usuario {
    private String fbId;
    private String fbCodigo;
    private String fbNombre;
    private String fbApellidos;
    private String fbCorreo;

    public B_Usuario(){

    }

    public String getFbCodigo() {
        return fbCodigo;
    }

    public void setFbCodigo(String fbCodigo) {
        this.fbCodigo = fbCodigo;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFbNombre() {
        return fbNombre;
    }

    public void setFbNombre(String fbNombre) {
        this.fbNombre = fbNombre;
    }

    public String getFbApellidos() {
        return fbApellidos;
    }

    public void setFbApellidos(String fbApellidos) {
        this.fbApellidos = fbApellidos;
    }

    public String getFbCorreo() {
        return fbCorreo;
    }

    public void setFbCorreo(String fbCorreo) {
        this.fbCorreo = fbCorreo;
    }

    @Override
    public String toString() {
        return fbNombre + " - " +fbApellidos;
    }
}
