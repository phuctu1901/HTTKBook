package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Feedback {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "Content", nullable = true, length = 500)
    private String content;
    @Basic
    @Column(name = "CreatedDate", nullable = true)
    private Timestamp createdDate;
    @Basic
    @Column(name = "Status", nullable = true)
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    private User user;

}
