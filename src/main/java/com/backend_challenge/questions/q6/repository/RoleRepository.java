package com.backend_challenge.questions.q6.repository;

import com.backend_challenge.questions.q6.domain.Role;
import com.backend_challenge.questions.q6.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> 	{
	 Optional<Role> findByName(RoleType name);
}
