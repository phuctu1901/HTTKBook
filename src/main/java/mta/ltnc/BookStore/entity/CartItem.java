package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class CartItem {
    @Basic
    @Column(name = "Quantity", nullable = true)
    private Integer quantity;
    @Basic
    @Column(name = "DateAdded", nullable = true)
    private Timestamp dateAdded;
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "ID", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "ItemID", referencedColumnName = "ID", nullable = false)
    private Book book;

}
