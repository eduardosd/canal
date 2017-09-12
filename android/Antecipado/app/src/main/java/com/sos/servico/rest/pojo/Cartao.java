package com.sos.servico.rest.pojo;

/**
 * Created by eduardo on 6/7/16.
 */
public class Cartao {

    private String id;
    private String saldo;

    public Cartao(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
