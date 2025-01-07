package com.api.parque_diversao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_nota_fiscal")

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString

public class NotaFiscal implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDateTime dataEmissao;

    private BigDecimal valorTotal;

    @JsonIgnore
	@OneToOne
	@MapsId
    private Order order;


    public NotaFiscal(Long id, String descricao, LocalDateTime dataEmissao, Order order) {
        this.id = id;
        this.descricao = descricao;
        this.dataEmissao = dataEmissao;
        this.order = order;
        this.valorTotal = order.getTotal();
    }
	
}
