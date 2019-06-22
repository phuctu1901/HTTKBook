package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class StatusOrder {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "Name", nullable = true, length = 50)
    private String name;
    @Basic
    @Column(name = "Status", nullable = false)
    private int status;

}
