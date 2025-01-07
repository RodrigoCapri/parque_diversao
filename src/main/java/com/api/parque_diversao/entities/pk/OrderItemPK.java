package com.api.parque_diversao.entities.pk;

import java.io.Serializable;

import com.api.parque_diversao.entities.Order;
import com.api.parque_diversao.entities.Produto;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable

@Getter
@Setter
@EqualsAndHashCode(of = {"order", "produto"})

public class OrderItemPK implements Serializable{

	private static final long serialVersionUID = 1L;
		
	//Aqui vão ser muitos para um com produto
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
		
}
