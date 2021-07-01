package br.com.meli.calculadorametrosquadrados.rest.dto;


import br.com.meli.calculadorametrosquadrados.dao.CasaDAO;
import br.com.meli.calculadorametrosquadrados.rest.entity.Casa;
import br.com.meli.calculadorametrosquadrados.rest.entity.Comodo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CasaDTO {

    private String nome;
    private String endereco;
    private List<Comodo> comodos = new ArrayList<>();

    public CasaDTO(String nome, String endereco) {
        super();
        this.nome = nome;
        this.endereco = endereco;
        //this.comodos = casa.getComodos();
    }

    public CasaDTO(Casa casa) {
        super();
        this.nome = casa.getNome();
        this.endereco = casa.getEndereco();
        this.comodos = casa.getComodos();
    }

    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }

    public List<Comodo> getComodos(){ return comodos;}

    public static CasaDTO converte(Casa casa) {
        return new CasaDTO(casa);//.getNome(), casa.getEndereco());
    }
    public static Casa converte(CasaDTO casa, CasaDAO dao) {
        return new Casa(dao.getList().size()+1,casa.getNome(), casa.getEndereco());
    }
    public static List<CasaDTO> converte(List<Casa> casas) {
        return casas.stream().map(a -> new CasaDTO(a)).collect(Collectors.toList());
    }
}