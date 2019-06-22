package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Purchase {
    @Id
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "Creater", nullable = false)
    private long creater;
    @Basic
    @Column(name = "CreatedDate", nullable = false)
    private Timestamp createdDate;
    @Basic
    @Column(name = "Status", nullable = false)
    private int status;
    @Basic
    @Column(name = "TotalPrice", nullable = false, precision = 0)
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "Publisher", referencedColumnName = "ID", nullable = false)
    private Publisher publisher;


}
