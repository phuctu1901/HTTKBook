package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Author {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "Name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "DateOfBirth", nullable = true)
    private Date dateOfBirth;
    @Basic
    @Column(name = "Description", nullable = true, length = 2147483647)
    private String description;

    @ManyToOne
    @JoinColumn(name = "Image", referencedColumnName = "ID")
    private Image imageByImage;
    @ManyToOne
    @JoinColumn(name = "AuthorType", referencedColumnName = "ID")
    private AuthorType authorType;

}
