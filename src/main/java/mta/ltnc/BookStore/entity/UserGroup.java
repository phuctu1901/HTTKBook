package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserGroup {
    @Id
    @Column(name = "ID", nullable = false, length = 20)
    private String id;
    @Basic
    @Column(name = "Name", nullable = true, length = 50)
    private String name;


}
