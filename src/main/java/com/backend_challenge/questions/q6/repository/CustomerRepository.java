package com.backend_challenge.questions.q6.repository;

import com.backend_challenge.questions.q6.domain.Customer;
import com.backend_challenge.questions.q6.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);
	Boolean existsByEmail(String email);

	@Query("select new com.backend_challenge.questions.q6.dto.CustomerDTO(user) from Customer user")
    Page<CustomerDTO> findAllWithPage(Pageable pageable);
}
