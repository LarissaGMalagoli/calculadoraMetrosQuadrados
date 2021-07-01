package br.com.meli.calculadorametrosquadrados.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import br.com.meli.calculadorametrosquadrados.rest.entity.Casa;
import br.com.meli.calculadorametrosquadrados.rest.entity.Comodo;

public class CasaDAO {
    //private static List<Comodo> comodo1 = new ArrayList<Comodo>();
    //private static List<Comodo> comodo2 = new ArrayList<Comodo>();


    public CasaDAO() {
    }

    private List<Casa> casas = new ArrayList<>(Arrays.asList(
            new Casa(1, "casa1", "endereco")
    ));


    public List<Casa> getList(){
        return casas;
    }

    public void adicionar(Casa casa) {
        casas.add(casa);
    }

    public Casa get(long id) {
        Optional<Casa> casaOpt = casas.stream().filter(a -> a.getId() == id).findFirst();
        if(casaOpt.isPresent()) {
            return casaOpt.get();
        }
        return null;
    }

    public void addComodo(long id, Comodo comodo){
        for (Casa c: casas) {
            if(c.getId()==id){
                c.setComodo(comodo);
            }
        }

    }

}
