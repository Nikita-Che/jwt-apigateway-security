package org.spring.childrenservice.persistance.repository;

import org.spring.childrenservice.persistance.model.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, Long> {
}