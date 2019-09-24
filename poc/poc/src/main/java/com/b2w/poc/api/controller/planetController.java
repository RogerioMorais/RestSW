package com.b2w.poc.api.controller;

import com.b2w.poc.api.document.planet;
import com.b2w.poc.api.services.iplanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import com.b2w.poc.api.responses.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/planet/")
public class planetController {

    @Autowired
    private iplanetService service;

    @GetMapping
    public ResponseEntity<response<List<planet>>> listarTodos(){
        return ResponseEntity.ok(new response<List<planet>>(this.service.listarTodos()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<response<Optional<planet>>>listaPorId(@PathVariable(name="id") String id){
        return  ResponseEntity.ok(new response<Optional<planet>>(this.service.listarPorId(id)));
    }
    @GetMapping(path = "/name/{nome}")
    public ResponseEntity<response<Optional<planet>>>listarPorNome(@PathVariable(name="nome") String nome){
        return  ResponseEntity.ok(new response<Optional<planet>>(this.service.listarPorNome(nome)));
    }
    @GetMapping(path = "/swapi/")
    public Object swapiplanet(){
        return getswapiplanet("https://swapi.co/api/planets");
    }

    @PostMapping(path = "/swapi/")
    public void registerPlanetsAuto(){
        /*Metodo criado somente para facilitar a criação de dados*/
        ResponseEntity<ListPlanetResponse> response=(ResponseEntity<ListPlanetResponse>)getswapiplanet("https://swapi.co/api/planets");
        ListPlanetResponse mylist=(ListPlanetResponse)response.getBody();
        while(mylist.getNext()!=null){
            for (PlanetResponse pr :mylist.getResults() ) {
                planet c =new planet();
                c.setNome(pr.getName());
                c.setClima(pr.getClimate());
                c.setTerreno(pr.getTerrain());
                c.setNrFilms(pr.getFilms().length);
                this.service.Cadastrar(c);
            }
            response=(ResponseEntity<ListPlanetResponse>)getswapiplanet(mylist.getNext());
            mylist=(ListPlanetResponse)response.getBody();
        }

    }

    private Object getswapiplanet(String url){
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ResponseEntity<ListPlanetResponse> response = restTemplate.exchange(url, HttpMethod.GET,entity,ListPlanetResponse.class);
            System.out.println(response);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex;
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<response<planet>>Cadastrar(@Valid @RequestBody planet c, BindingResult result){
        if(result.hasErrors()){
            List<String> erros =new ArrayList<String>();
            result.getAllErrors().forEach(erro ->erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new response<planet>(erros));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new  response<planet>(this.service.Cadastrar(c)));
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<response<planet>>Atualizar(@PathVariable(name="id") String id,@Valid  @RequestBody planet c, BindingResult result){
       if(result.hasErrors()){
            List<String> erros =new ArrayList<String>();
            result.getAllErrors().forEach(erro ->erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new response<planet>(erros));
        }

        c.setId(id);
        return ResponseEntity.ok(new response<planet>(this.service.Atualizar(c)));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<response<Integer>>Remover(@PathVariable(name="id") String id){
        this.service.remover(id);
        return ResponseEntity.ok(new response<Integer>(1));
    }
}
