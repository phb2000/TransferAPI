package com.project.transferapi.domain.ports;

import com.project.transferapi.domain.entity.User;

import java.util.Optional;

public interface IFindUserById {
    Optional<User> findUserById(Long id);
}