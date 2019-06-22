package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Slide {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "DisplayOrder", nullable = true)
    private Integer displayOrder;
    @Basic
    @Column(name = "BookID", nullable = true)
    private Long bookId;
    @Basic
    @Column(name = "Link", nullable = true, length = 255)
    private String link;
    @Basic
    @Column(name = "Status", nullable = false)
    private boolean status;
    @Basic
    @Column(name = "CreatedDate", nullable = true)
    private Timestamp createdDate;
    @ManyToOne
    @JoinColumn(name = "Image", referencedColumnName = "ID", nullable = false)
    private Image image;

}
