package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShippingType {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "TypeShip", nullable = false, length = 100)
    private String typeShip;
    @Basic
    @Column(name = "Cost", nullable = false, precision = 0)
    private int cost;
    @Basic
    @Column(name = "Time", nullable = true, length = 50)
    private String time;


}
