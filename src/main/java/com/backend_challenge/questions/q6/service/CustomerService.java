package com.backend_challenge.questions.q6.service;

import com.backend_challenge.questions.q6.domain.Customer;
import com.backend_challenge.questions.q6.domain.Role;
import com.backend_challenge.questions.q6.domain.enums.RoleType;
import com.backend_challenge.questions.q6.dto.CustomerDTO;
import com.backend_challenge.questions.q6.dto.mapper.CustomerMapper;
import com.backend_challenge.questions.q6.dto.request.CustomerUpdateRequest;
import com.backend_challenge.questions.q6.dto.request.RegisterRequest;
import com.backend_challenge.questions.q6.dto.request.UpdatePasswordRequest;
import com.backend_challenge.questions.q6.exception.BadRequestException;
import com.backend_challenge.questions.q6.exception.ConflictException;
import com.backend_challenge.questions.q6.exception.ResourceNotFoundException;
import com.backend_challenge.questions.q6.exception.message.ErrorMessage;
import com.backend_challenge.questions.q6.repository.CustomerRepository;
import com.backend_challenge.questions.q6.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CustomerMapper customerMapper;

    public void register(RegisterRequest registerRequest) {
        if (customerRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST, registerRequest.getEmail()));
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        Role role = roleRepository.findByName(RoleType.ROLE_CUSTOMER).orElseThrow(() -> new ResourceNotFoundException(
                String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_CUSTOMER.name())));

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setFullName(registerRequest.getFullName());
        customer.setIdentityNumber(registerRequest.getIdentityNumber());
        customer.setEmail(registerRequest.getEmail());
        customer.setPassword(encodedPassword);
        customer.setSalary(registerRequest.getSalary());
        customer.setPhoneNumber(registerRequest.getPhoneNumber());
        customer.setRoles(roles);

        customerRepository.save(customer);
    }

    public Page<CustomerDTO> getAllUsers(Pageable pageable) {
        Page<CustomerDTO> customers = customerRepository.findAllWithPage(pageable);
        return customers;
    }

    public CustomerDTO findById(Long id) {
        Customer user = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        return customerMapper.customerToCustomerDTO(user);
    }

    public void updatePassword(Long id, UpdatePasswordRequest passwordRequest) {
        Optional<Customer> userOpt = customerRepository.findById(id);
        Customer customer = userOpt.get();

        if (!passwordEncoder.matches(passwordRequest.getOldPassword(), customer.getPassword())) {
            throw new BadRequestException(ErrorMessage.PASSWORD_NOT_MATCHED);
        }

        String hashedPassword = passwordEncoder.encode(passwordRequest.getNewPassword());
        customer.setPassword(hashedPassword);

        customerRepository.save(customer);

    }

    public void updateUser(Long id, CustomerUpdateRequest customerUpdateRequest) {
        boolean emailExist = customerRepository.existsByEmail(customerUpdateRequest.getEmail());

        Customer user = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ErrorMessage.ROLE_NOT_FOUND_MESSAGE));

        if (emailExist && !customerUpdateRequest.getEmail().equals(user.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST,user.getEmail()));
        }

        Customer updateUser = customerMapper.customerRequestToCustomer(customerUpdateRequest);
        updateUser.setId(user.getId());
        updateUser.setRoles(user.getRoles());
        updateUser.setPassword(user.getPassword());

        customerRepository.save(updateUser);
    }

    public void removeById(Long id) {
        Customer user = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        customerRepository.deleteById(id);
    }
}
