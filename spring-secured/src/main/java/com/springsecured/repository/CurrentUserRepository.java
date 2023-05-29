package com.springsecured.repository;

import com.springsecured.model.CurrentUser;
import com.springsecured.model.CurrentUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentUserRepository extends JpaRepository<CurrentUserEntity, Long> {

    CurrentUserEntity findByUsername(String username);

}
