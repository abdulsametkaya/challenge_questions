package com.backend_challenge.questions.q6.dto;

import com.backend_challenge.questions.q6.domain.Customer;
import com.backend_challenge.questions.q6.domain.Role;
import com.backend_challenge.questions.q6.domain.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;

    private String fullName;

    private Long identityNumber;
    private String email;

    private String phoneNumber;

    private Set<String> roles;

    public void setRoles(Set<Role> roles) {
        Set<String> rolesStr = new HashSet<>();

        roles.forEach(r -> {
            if (r.getName().equals(RoleType.ROLE_ADMIN))
                rolesStr.add("Administrator");
            else
                rolesStr.add("Customer");
        });

        this.roles=rolesStr;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.fullName = customer.getFullName();
        this.identityNumber = customer.getIdentityNumber();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
    }
}
