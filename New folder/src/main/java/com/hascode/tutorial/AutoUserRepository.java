package com.hascode.tutorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoUserRepository extends JpaRepository<AutoUser, Long> {

	public AutoUser findByUsername(String username);
}
