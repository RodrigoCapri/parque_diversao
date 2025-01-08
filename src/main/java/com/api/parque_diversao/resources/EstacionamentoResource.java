package com.api.parque_diversao.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parque_diversao.entities.Estacionamento;
import com.api.parque_diversao.services.EstacionamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/api/estacionamento")
public class EstacionamentoResource {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @GetMapping
    public ResponseEntity< List<Estacionamento> > findAll() {
        
        return ResponseEntity.ok().body(estacionamentoService.findAll());

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity< Estacionamento > findById(@PathVariable Long id){

        return ResponseEntity.ok().body(estacionamentoService.findById(id));

    }

    @GetMapping(value = "/{id}/vagas")
    public ResponseEntity< Integer > vagasDisponiveis(@PathVariable Long id){

        return ResponseEntity.ok().body(estacionamentoService.findById(id).getVagasDisponiveis());
        
    }

}
