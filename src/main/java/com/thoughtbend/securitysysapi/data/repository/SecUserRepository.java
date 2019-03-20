package com.thoughtbend.securitysysapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thoughtbend.securitysysapi.data.entity.SecUser;

@Repository
public interface SecUserRepository extends JpaRepository<SecUser, Long> {

}
