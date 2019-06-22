package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserGroup_Role", schema = "dbo", catalog = "BookShop3")
public class UserGroupRole {

    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "UserGroupID", referencedColumnName = "ID", nullable = false)
    private UserGroup userGroup;
    @ManyToOne
    @JoinColumn(name = "RoleID", referencedColumnName = "ID", nullable = false)
    private Role role;

}
