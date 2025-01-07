package com.api.parque_diversao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "tb_produto")

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString

public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
	private String description;
	private BigDecimal price;

    @OneToMany(mappedBy = "id.produto")
	private Set<OrderItem> items = new HashSet<>();

    public Produto(Long id, String nome, String description, BigDecimal price) {
        this.id = id;
        this.nome = nome;
        this.description = description;
        this.price = price;
    }
	
}
