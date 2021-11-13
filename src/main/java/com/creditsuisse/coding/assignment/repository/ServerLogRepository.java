package com.creditsuisse.coding.assignment.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.creditsuisse.coding.assignment.entity.ServerLog;

public interface ServerLogRepository extends JpaRepository<ServerLog, String> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Override
	default <S extends ServerLog> S save(S entity) {
		
		  return this.save(entity);
	}


}
