package com.backend_challenge.questions.q6.controller;

import com.backend_challenge.questions.q6.dto.CustomerDTO;
import com.backend_challenge.questions.q6.dto.request.CustomerUpdateRequest;
import com.backend_challenge.questions.q6.dto.request.UpdatePasswordRequest;
import com.backend_challenge.questions.q6.dto.response.CAResponse;
import com.backend_challenge.questions.q6.dto.response.ResponseMessage;
import com.backend_challenge.questions.q6.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class CustomerController {

	private CustomerService customerService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<CustomerDTO>> getAllUsers(@RequestParam(required = false, value = "page",defaultValue = "0") int page,
														 @RequestParam(required = false, value = "size",defaultValue = "20") int size,
														 @RequestParam(required = false, value = "sort",defaultValue = "id") String prop,
														 @RequestParam(required = false, value = "direction",defaultValue = "DESC") Sort.Direction direction){

		Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
		Page<CustomerDTO> users= customerService.getAllUsers(pageable);

	    return ResponseEntity.ok(users);
	}
	

	@GetMapping("/user")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<CustomerDTO> getUserById(HttpServletRequest request){
		 Long id= (Long) request.getAttribute("id");
		CustomerDTO userDTO= customerService.findById(id);
		 
		 return ResponseEntity.ok(userDTO);
	}

	/*
	 * http://localhost:8080/users/update-password { "newPassword":"testup",
	 * "oldPassword":"test1" }
	 */
	@PatchMapping("/update-password")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<CAResponse> updatePassword(HttpServletRequest httpServletRequest,
													 @RequestBody UpdatePasswordRequest passwordRequest ){
		Long id=(Long) httpServletRequest.getAttribute("id");
		customerService.updatePassword(id, passwordRequest);

		CAResponse response=new CAResponse();
		response.setMessage(ResponseMessage.PASSWORD_CHANGED_MESSAGE);
		response.setSuccess(true);
		
		return ResponseEntity.ok(response);
	}
	
	
	@PutMapping("update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<CAResponse> updateUser(HttpServletRequest httpServletRequest,
												 @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
		Long id=(Long) httpServletRequest.getAttribute("id");
		customerService.updateUser(id,customerUpdateRequest);

		CAResponse response=new CAResponse();
		response.setMessage(ResponseMessage.UPDATE_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return ResponseEntity.ok(response);
		
	}
	
	//http://localhost:8080/users/delete/2
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CAResponse> deleteUser(@PathVariable Long id){
		customerService.removeById(id);

		CAResponse response=new CAResponse();
		response.setMessage(ResponseMessage.DELETE_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return ResponseEntity.ok(response);
	}
	

}
