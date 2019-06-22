package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class NewsType {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "Name", nullable = true, length = 250)
    private String name;


}
