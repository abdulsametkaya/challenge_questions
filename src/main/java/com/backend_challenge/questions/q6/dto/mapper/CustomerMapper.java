package com.backend_challenge.questions.q6.dto.mapper;

import com.backend_challenge.questions.q6.domain.Customer;
import com.backend_challenge.questions.q6.dto.CustomerDTO;
import com.backend_challenge.questions.q6.dto.request.CustomerUpdateRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	
	CustomerDTO customerToCustomerDTO(Customer customer);

	Customer customerRequestToCustomer(CustomerUpdateRequest request);




}
