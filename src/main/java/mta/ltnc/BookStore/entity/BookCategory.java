package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class BookCategory {
    @Id
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "Name", nullable = true, length = 250)
    private String name;

    @Basic
    @Column(name = "DisplayOrder", nullable = true)
    private Integer displayOrder;
    @Basic
    @Column(name = "Status", nullable = true)
    private Boolean status;
    @Basic
    @Column(name = "CreateDate", nullable = true)
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "ParentID", referencedColumnName = "ID")
    private Category category;

}
