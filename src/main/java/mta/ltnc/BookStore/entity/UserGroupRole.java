package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserGroup_Role", schema = "dbo", catalog = "BookShop3")
public class UserGroupRole {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "UserGroupID", referencedColumnName = "ID", nullable = false)
    private UserGroup userGroup;
    @ManyToOne
    @JoinColumn(name = "RoleID", referencedColumnName = "ID", nullable = false)
    private Role role;
}
