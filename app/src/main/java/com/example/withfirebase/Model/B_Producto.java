package com.example.withfirebase.Model;

public class B_Producto {
    private String fbCodVendedor;
    private String fbCodigo;
    private String fbDescripcion;
    private String fbNombre;
    private double fbPrecio;

    public B_Producto() {
    }

    public String getFbCodVendedor() {
        return fbCodVendedor;
    }

    public void setFbCodVendedor(String fbCodVendedor) {
        this.fbCodVendedor = fbCodVendedor;
    }

    public String getFbCodigo() {
        return fbCodigo;
    }

    public void setFbCodigo(String fbCodigo) {
        this.fbCodigo = fbCodigo;
    }

    public String getFbDescripcion() {
        return fbDescripcion;
    }

    public void setFbDescripcion(String fbDescripcion) {
        this.fbDescripcion = fbDescripcion;
    }

    public String getFbNombre() {
        return fbNombre;
    }

    public void setFbNombre(String fbNombre) {
        this.fbNombre = fbNombre;
    }

    public double getFbPrecio() {
        return fbPrecio;
    }

    public void setFbPrecio(double fbPrecio) {
        this.fbPrecio = fbPrecio;
    }

    /*
    @Override
    public String toString() {return fbNombre + " - " + fbPrecio;}
    */

    @Override
    public String toString() {
        return  fbNombre + " - S/."+fbPrecio;
    }
}
