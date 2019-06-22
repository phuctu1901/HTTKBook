package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Image {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
