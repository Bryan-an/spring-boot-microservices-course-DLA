package academy.digitallab.store.shopping.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "tbl_invoice_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Positive(message = "The stock must be greater than zero")
    private Double quantity;

    private Double price;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Double subTotal;

    public InvoiceItem() {
        this.quantity = 0D;
        this.price = 0D;
    }

    public Double getSubTotal() {
        if (this.price > 0 && this.quantity > 0) {
            return this.quantity * this.price;
        } else {
            return 0D;
        }
    }
}

