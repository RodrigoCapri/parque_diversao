package com.api.parque_diversao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.parque_diversao.entities.Estacionamento;
import com.api.parque_diversao.repositories.EstacionamentoRepositorie;
import com.api.parque_diversao.services.exceptions.DatabaseException;
import com.api.parque_diversao.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepositorie estacionamentoRepositorie;

    public List<Estacionamento> findAll(){
        
        return estacionamentoRepositorie.findAll();

    } 

    public Estacionamento findById(Long id){

        return estacionamentoRepositorie.findById(id).orElseThrow( () -> new ResourceNotFoundException(id) );

    }

    public Estacionamento insert( Estacionamento obj ){

        return estacionamentoRepositorie.save(obj);

    }

    public Estacionamento update( Long id, Estacionamento newObj ){

        try{
            //Retorn uma referencia de estacionamento existente no id indicado
            Estacionamento obj = estacionamentoRepositorie.getReferenceById(id);

            obj.setEndereco( newObj.getEndereco() != null ? newObj.getEndereco() : obj.getEndereco() );
            obj.setVagasDisponiveis( newObj.getVagasDisponiveis() != null ? newObj.getVagasDisponiveis() : obj.getVagasDisponiveis() );

            return this.estacionamentoRepositorie.save(obj);

        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }

    }

    public void delete(Long id){

        try {

			this.estacionamentoRepositorie.deleteById(id);

		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) { //Violação de integridade de dados
			throw new DatabaseException(e.getMessage()); 
		}

    }

}
