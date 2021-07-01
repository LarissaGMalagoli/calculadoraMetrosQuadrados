package br.com.meli.calculadorametrosquadrados.rest;

import br.com.meli.calculadorametrosquadrados.rest.dto.ComodoDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.meli.calculadorametrosquadrados.dao.CasaDAO;
import br.com.meli.calculadorametrosquadrados.rest.dto.CasaDTO;
import br.com.meli.calculadorametrosquadrados.rest.entity.Casa;
import br.com.meli.calculadorametrosquadrados.rest.entity.Comodo;

@RestController
@RequestMapping("/casa")
public class CasaController {
    private CasaDAO dao = new CasaDAO();

    @GetMapping
    @RequestMapping("/teste")
    public CasaDTO endPointTeste() {
        Comodo comodo = new Comodo("sala", 2.3, 3.0);
        List<Comodo> l1 = new ArrayList<>();
        //l1.add(comodo);
        Casa casa = new Casa(1, "casa3", "rua3");
        dao.addComodo(1, comodo);
        return CasaDTO.converte(casa);
    }

    @PostMapping
    public ResponseEntity<CasaDTO> cadastra(@RequestBody CasaDTO casaDTO, UriComponentsBuilder uriBuilder) {
        Casa casa = CasaDTO.converte(casaDTO, dao);
        dao.adicionar(casa);
        URI uri = uriBuilder.path("/casa/{id}").buildAndExpand(casa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<CasaDTO> cadastraComodo(@PathVariable Long id, @RequestBody ComodoDTO comodo) {
        dao.addComodo(id, comodo.converte(comodo));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public CasaDTO obterCasa(@PathVariable long id) {
        Casa casa = dao.get(id);
        CasaDTO casaDTO = CasaDTO.converte(casa);
        return casaDTO;
    }

    @GetMapping
    public List<CasaDTO> obterCasas() {
        List<Casa> casas = dao.getList();
        List<CasaDTO> dtos = CasaDTO.converte(casas);
        return dtos;
    }

    @GetMapping("/{id}/metrosq")
    public String metrosQuadrados(@PathVariable long id) {
        List<Casa> casas = dao.getList();
        Casa casa = casas.get(((int)id)-1);

        double metrosQuadrados = 0;
        CasaDTO casaDTO = CasaDTO.converte(casa);
        List<Comodo> comodos = casaDTO.getComodos();
        for (Comodo c : comodos){
            metrosQuadrados+= c.getComprimento()*c.getLargura();
        }
        //}
        return "Total de metros quadrados: " + metrosQuadrados;
    }

    @GetMapping("/{id}/valor")
    public String valorCasa(@PathVariable long id) {
        List<Casa> casas = dao.getList();
        Casa casa = casas.get(((int)id)-1);

        double metrosQuadrados = 0;
        CasaDTO casaDTO = CasaDTO.converte(casa);
        List<Comodo> comodos = casaDTO.getComodos();
        for (Comodo c : comodos){
            metrosQuadrados+= c.getComprimento()*c.getLargura();
        }
        //}
        return "Valor da casa: " + (metrosQuadrados*800);
    }

    @GetMapping("/{id}/maior")
    public String maiorComodo(@PathVariable long id) {
        List<Casa> casas = dao.getList();
        Casa casa = casas.get(((int)id)-1);


        CasaDTO casaDTO = CasaDTO.converte(casa);
        List<Comodo> comodos = casaDTO.getComodos();
        double maiorTamanho = comodos.get(0).getComprimento()*comodos.get(0).getLargura();
        String maiorComodo = comodos.get(0).getNome();
        for (Comodo c : comodos){
            double tamanhoAtual = c.getComprimento()*c.getLargura();
            if(tamanhoAtual>maiorTamanho){
                maiorTamanho=tamanhoAtual;
                maiorComodo=c.getNome();
            }
        }
        //}
        return "Maior comodo da casa: " + maiorComodo;
    }

    @GetMapping("/{id}/metrosc")
    public String metrosComodo(@PathVariable long id) {
        List<Casa> casas = dao.getList();
        Casa casa = casas.get(((int)id)-1);


        CasaDTO casaDTO = CasaDTO.converte(casa);
        List<Comodo> comodos = casaDTO.getComodos();
        String resultado = "";
        for (Comodo c : comodos){
            double metrosComodo = c.getComprimento()*c.getLargura();
            resultado+= " " + c.getNome() + ": " + metrosComodo;
        }
        //}
        return resultado;
    }
}