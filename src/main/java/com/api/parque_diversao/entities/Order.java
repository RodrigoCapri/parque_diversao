package com.api.parque_diversao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.api.parque_diversao.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_order")

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;

    private OrderStatus status;

    private String cpf;

    @OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>(); //Inicializa-se todas as coleções

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Pagamento payment;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private NotaFiscal notaFiscal;


    public Order(Long id, Instant moment, OrderStatus status, String cpf, Pagamento payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.cpf = cpf;
        this.payment = payment;
    }

    /**
     * Calculates the total amount for the order by summing up the subtotals of all order items.
     * Applies rounding to two decimal places in the upward direction.
     *
     * @return The total amount of the order as a BigDecimal.
     */
    public BigDecimal getTotal() {
        
        return items.stream().map(OrderItem::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.UP);

    }
	
}
