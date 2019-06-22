package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Role {
    @Id
    @Column(name = "ID", nullable = false, length = 50)
    private String id;
    @Basic
    @Column(name = "Name", nullable = true, length = 50)
    private String name;


}
