package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Order_Detail", schema = "dbo", catalog = "BookShop3")
public class OrderDetail {


    @Basic
    @Column(name = "Quantity", nullable = true)
    private Integer quantity;
    @Basic
    @Column(name = "Price", nullable = true, precision = 0)
    private Integer price;
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "ID", nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "ID", nullable = false)
    private Order order;

}
