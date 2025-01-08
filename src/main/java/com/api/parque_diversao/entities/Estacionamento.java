package com.api.parque_diversao.entities;

import java.io.Serializable;
import java.util.Set;

import com.api.parque_diversao.services.exceptions.VagaEstacionamentoException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "tb_estacionamento")

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString

public class Estacionamento implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private Integer vagasTotal;
    private Integer vagasDisponiveis;

    @OneToMany(mappedBy = "estacionamento")
    private Set<VagaEstacionamento> vagas_estacionamento;

    public Estacionamento(Long id, String endereco, Integer vagasTotal) {
        this.id = id;
        this.endereco = endereco;
        this.vagasTotal = vagasTotal;
        this.vagasDisponiveis = this.vagasTotal;
    }

    public void preencherUmaVaga(){

        if(vagasDisponiveis == 0)
            throw new VagaEstacionamentoException("Não há vaga disponível!");

        this.vagasDisponiveis--;
    }

    public void esvaziarUmaVaga(){

        if(this.vagasDisponiveis >= this.vagasTotal)
            throw new VagaEstacionamentoException("Não há vagas preenchidas!");

        this.vagasDisponiveis++;
    }
    
}
