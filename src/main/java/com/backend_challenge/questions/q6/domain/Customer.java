package com.backend_challenge.questions.q6.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11, nullable=false)
    private String identityNumber;

    @Column(nullable=false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable=false )
    private String password;

    @Column(nullable=false)
    private String phoneNumber;

    @Column(nullable=false)
    private Double salary;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tbl_user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();

}
