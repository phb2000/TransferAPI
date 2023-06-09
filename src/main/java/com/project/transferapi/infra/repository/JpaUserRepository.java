package com.project.transferapi.infra.repository;

import com.project.transferapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface JpaUserRepository extends JpaRepository<User, Long> {
   @Query("select u from User u where u.email = ?1")
   Optional<User> findByEmail(String email);

   @Query("select (count(u) > 0) from User u where upper(u.legalDocumentNumber) = upper(?1)")
   boolean existsByLegalDocumentNumberAllIgnoreCase(String legalDocumentNumber);

   @Query("select (count(u) > 0) from User u where u.email = ?1")
   boolean existsByEmail(String email);
}
