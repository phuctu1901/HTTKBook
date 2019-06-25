package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="[order]")
public class Order {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "created_date", nullable = true)
    private Timestamp createdDate;

    @Basic
    @Column(name = "ship_name", nullable = true, length = 50)
    private String shipName;
    @Basic
    @Column(name = "ship_mobile", nullable = true, length = 50)
    private String shipMobile;
    @Basic
    @Column(name = "ship_email", nullable = true, length = 50)
    private String shipEmail;
    @Basic
    @Column(name = "ship_adress", nullable = true, length = 255)
    private String shipAdress;

    @Basic
    @Column(name = "shipped_date", nullable = true)
    private Timestamp shippedDate;
    @Basic
    @Column(name = "total_price", nullable = true, precision = 0)
    private Integer totalPrice;
    @Basic
    @Column(name = "code", nullable = true, precision = 0)
    private String code;
    @ManyToOne
    @JoinColumn(name = "shipping_type", referencedColumnName = "ID")
    private ShippingType shippingType;
    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "ID")
    private StatusOrder statusOrder;

    @ManyToOne
    @JoinColumn(name = "created_id", referencedColumnName = "ID")
    private User user;

}
