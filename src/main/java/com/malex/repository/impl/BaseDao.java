package com.malex.repository.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BaseDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public void refreshObject(Object entity) {
		entityManager.refresh(entity);
	}

	// Updates an entity
	public void update(Object entity) {
		entityManager.merge(entity);
	}

	// First tries to save if fails then updates
	public void saveOrUpdate(Object entity) {
		entityManager.merge(entity);
	}

	// Save an entity
	public void save(Object entity) {
		entityManager.persist(entity);
	}

	// Deletes the entity from the database, row is removed, use it judiciously
	public void delete(Object entity) {
		entityManager.remove(entity);
	}

}
