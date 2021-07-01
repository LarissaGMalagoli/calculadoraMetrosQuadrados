package br.com.meli.calculadorametrosquadrados.rest.entity;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    private long id;
    private String nome;
    private String endereco;
    private List<Comodo> comodos = new ArrayList<>();

    public Casa(long id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Comodo> getComodos() {
        return comodos;
    }

    public void setComodo(Comodo comodo) {
        comodos.add(comodo);
        //System.out.println(comodos.get(0).getNome());
    }

}
