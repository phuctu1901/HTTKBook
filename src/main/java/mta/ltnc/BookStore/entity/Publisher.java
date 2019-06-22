package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Publisher {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "Name", nullable = true, length = 250)
    private String name;
    @Basic
    @Column(name = "Address", nullable = true, length = 250)
    private String address;
    @Basic
    @Column(name = "Description", nullable = true, length = 500)
    private String description;
    @Basic
    @Column(name = "Status", nullable = false)
    private boolean status;
    @Basic
    @Column(name = "CreatedDate", nullable = true)
    private Timestamp createdDate;


}
