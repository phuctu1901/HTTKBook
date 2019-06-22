package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PurchaseDetail {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "Price", nullable = false, precision = 0)
    private int price;

    @ManyToOne
    @JoinColumn(name = "PurchaseID", referencedColumnName = "ID", nullable = false)
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "ID", nullable = false)
    private Book book;

}
