package com.sos.servico.rest.pojo;

/**
 * Created by eduardo on 6/5/16.
 */
public class Cliente {

    private Long id;
    private String nome;
    private String sexo;
    private String senha;
    private String cartao_id;
    private String email;
    private Cartao _cartao_cache;

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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCartao_id() {
        return cartao_id;
    }

    public void setCartao_id(String cartao_id) {
        this.cartao_id = cartao_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cartao get_cartao_cache() {
        return _cartao_cache;
    }

    public void set_cartao_cache(Cartao _cartao_cache) {
        this._cartao_cache = _cartao_cache;
    }
}
