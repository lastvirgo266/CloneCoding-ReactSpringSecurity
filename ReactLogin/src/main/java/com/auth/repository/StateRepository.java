package com.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.model.State;
import com.auth.model.StateName;

public interface StateRepository extends JpaRepository<State, Long> {
	Optional<State> findByName(StateName name);
	
}
