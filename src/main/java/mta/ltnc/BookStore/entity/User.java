package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="[user]")

public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "UserName", nullable = true, length = 50)
    private String userName;
    @Basic
    @Column(name = "Email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "Password", nullable = true, length = 255)
    private String password;
    @Basic
    @Column(name = "Name", nullable = true, columnDefinition = "nvarchar(255)")
    private String name;
//    @Basic
//    @Column(name = "DayOfBirth", nullable = true)
//    private Date dayOfBirth;
    @Basic
    @Column(name = "Phone", nullable = true, length = 50)
    private String phone;
    @Basic
    @Column(name = "Address", nullable = true,columnDefinition = "nvarchar(255)")
    private String address;
    @Basic
    @Column(name = "Status", nullable = false)
    private Boolean status;
    @Basic
    @Column(name = "CreatedDate", nullable = false)
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name = "UserGroupID", referencedColumnName = "ID")
    private UserGroup userGroup;

}
