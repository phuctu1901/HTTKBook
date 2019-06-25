package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class Book {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "Code", nullable = true, columnDefinition = "nvarchar(255)")
    private String code;
    @Basic
    @Column(name = "Name", nullable = true, columnDefinition = "nvarchar(255)")
    private String name;

    @Basic
    @Column(name = "NumberPage", nullable = true)
    private Integer numberPage;
    @Basic
    @Column(name = "Weight", nullable = true)
    private Integer weight;
    @Basic
    @Column(name = "Form", nullable = true, length = 20)
    private String form;
    @Basic
    @Column(name = "PublishDate", nullable = true)
    private Date publishDate;
    @Basic
    @Column(name = "Buys", nullable = true)
    private Integer buys;
    @Basic
    @Column(name = "Price", nullable = true, precision = 0)
    private Integer price;
    @Basic
    @Column(name = "PromotionPrice", nullable = true, precision = 0)
    private Integer promotionPrice;
    @Basic
    @Column(name = "ViewCount", nullable = true)
    private Integer viewCount;
    @Basic
    @Column(name = "Inventory", nullable = true, precision = 0)
    private Integer inventory;
    @Basic
    @Column(name = "Status", nullable = true)
    private Boolean status;
    @Basic
    @Column(name = "Description", nullable = true, columnDefinition = "text")
    private String description;
    @Basic
    @Column(name = "image1", nullable = true, columnDefinition = "text")
    private String image1;
    @Basic
    @Column(name = "CreateDate", nullable = true)
    private Timestamp createDate;
    @ManyToOne
    @JoinColumn(name = "Author", referencedColumnName = "ID")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "Publisher", referencedColumnName = "ID")
    private Publisher publisher;
    @ManyToOne
    @JoinColumn(name = "CategoryID", referencedColumnName = "ID")
    private BookCategory bookCategory;
}
