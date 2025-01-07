package com.api.parque_diversao.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.api.parque_diversao.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_order_item")

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private BigDecimal price;

    public OrderItem(Order order, Produto produto, Integer quantity, BigDecimal price) {
        id.setOrder(order);
        id.setProduto(produto);
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Caucula o subtotal do Item
     * 
     * @return O subtotal do item do tipo BigDecimal.
     */
    public BigDecimal getSubTotal() {
    	
        return price.multiply(new BigDecimal(quantity));
        
    }
	
}
