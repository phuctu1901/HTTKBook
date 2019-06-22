package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class ShippingType {
    @Id
    @Column(name = "ID", nullable = false)
    private long id;
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
