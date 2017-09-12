package com.sos.servico.rest.pojo;

/**
 * Created by eduardo on 6/8/16.
 */
public class Item {

    private Long id;
    private String nome;
    private String valorUnit;
    private String tamanho;
    private String detalhe;
    private String codigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(String valorUnit) {
        this.valorUnit = valorUnit;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
