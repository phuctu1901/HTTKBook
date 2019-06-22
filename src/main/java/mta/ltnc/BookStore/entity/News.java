package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class News {
    @Id
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "Title", nullable = true, length = 250)
    private String title;
    @Basic
    @Column(name = "Created", nullable = true)
    private Long created;
    @Basic
    @Column(name = "CreatedDate", nullable = true)
    private Timestamp createdDate;

    private Boolean isPublic;
    @Basic
    @Column(name = "Image", nullable = true)
    private Long image;

    @Basic
    @Column(name = "Content", nullable = true, length = 2147483647)
    private String content;
    @ManyToOne
    @JoinColumn(name = "Type", referencedColumnName = "ID")
    private NewsType newsType;
    @ManyToOne
    @JoinColumn(name = "Author", referencedColumnName = "ID")
    private User user;

    @Basic
    @Column(name = "isPublic", nullable = true)
    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

}
