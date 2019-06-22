package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Publisher {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "Name", nullable = true, columnDefinition = "nvarchar(255)")
    private String name;
    @Basic
    @Column(name = "Address", nullable = true, columnDefinition = "nvarchar(255)")
    private String address;
    @Basic
    @Column(name = "Description", nullable = true, length = 500)
    private String description;
    @Basic
    @Column(name = "Status", nullable = false)
    private boolean status;
    @Basic
    @Column(name = "CreatedDate", nullable = true)
    private Timestamp createdDate;


}
