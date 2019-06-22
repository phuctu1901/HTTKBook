package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="[order]")
public class Order {
    @Id
    @Column(name = "ID", nullable = false)
    private long id;
//    @Basic
//    @Column(name = "CreateDate", nullable = true)
//    private Timestamp createDate;
    @Basic
    @Column(name = "CreatID", nullable = true)
    private Long creatId;
    @Basic
    @Column(name = "ShipName", nullable = true, length = 50)
    private String shipName;
    @Basic
    @Column(name = "ShipMobile", nullable = true, length = 50)
    private String shipMobile;
    @Basic
    @Column(name = "ShipEmail", nullable = true, length = 50)
    private String shipEmail;
    @Basic
    @Column(name = "ShipAdress", nullable = true, length = 255)
    private String shipAdress;

    @Basic
    @Column(name = "ShippedDate", nullable = true)
    private Timestamp shippedDate;
    @Basic
    @Column(name = "TotalPrice", nullable = true, precision = 0)
    private Integer totalPrice;
    @ManyToOne
    @JoinColumn(name = "ShippingType", referencedColumnName = "ID")
    private ShippingType shippingType;
    @ManyToOne
    @JoinColumn(name = "Status", referencedColumnName = "ID")
    private StatusOrder statusOrder;


}
