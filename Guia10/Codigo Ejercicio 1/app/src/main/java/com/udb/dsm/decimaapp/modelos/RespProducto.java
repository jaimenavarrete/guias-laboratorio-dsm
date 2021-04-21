package com.udb.dsm.decimaapp.modelos;

public class RespProducto {
    private boolean ok;
    private Producto resultado;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Producto getResultado() {
        return resultado;
    }

    public void setResultado(Producto resultado) {
        this.resultado = resultado;
    }
}
