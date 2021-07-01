package br.com.meli.calculadorametrosquadrados.rest.dto;

import br.com.meli.calculadorametrosquadrados.rest.entity.Casa;
import br.com.meli.calculadorametrosquadrados.rest.entity.Comodo;

import java.util.List;
import java.util.stream.Collectors;

public class ComodoDTO {
    private String nome;
    private double largura;
    private double comprimento;

    public ComodoDTO(String nome, double largura, double comprimento) {
        this.nome = nome;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public static List<ComodoDTO> converteComodos(List<Comodo> comodos) {
        return comodos.stream().map(a -> new ComodoDTO(a.getNome(), a.getLargura(), a.getComprimento())).collect(Collectors.toList());
    }

    public static Comodo converte(ComodoDTO comodo) {
        return new Comodo(comodo.getNome(), comodo.getLargura(), comodo.getComprimento());
    }
}
