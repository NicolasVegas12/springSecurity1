package com.example.vcnb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Getter@Setter
    private Long id;

    @Column(name = "first_name")
    @Getter@Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter@Setter
    private String lastName;

    @Column(name = "email")
    @Getter@Setter
    private String email;

    @Column(name = "password")
    @Getter@Setter
    private String password;

    @Column(name = "active")
    @Getter@Setter
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    @Getter@Setter
    private Collection<Role> roles;

    public User( String firstName, String lastName, String email, String password,  Collection<Role> roles) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, String firstName, String lastName, String email, String password, Boolean active, Collection<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User() {

    }
}

