package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Image {
    @Id
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "Name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "CreateDate", nullable = true)
    private Date createDate;
    @Basic
    @Column(name = "Description", nullable = true, length = 100)
    private String description;
    @Basic
    @Column(name = "Link", nullable = true, length = 255)
    private String link;

}
