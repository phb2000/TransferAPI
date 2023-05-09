package com.project.transferapi.infra.repository;

import com.project.transferapi.domain.entity.User;
import com.project.transferapi.infra.mapper.UserModelMapper;
import com.project.transferapi.infra.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @InjectMocks
    UserRepository repository;

    @Mock
    JpaUserRepository userRepository;

    @Mock
    UserModelMapper userModelMapper;

    @Mock
    User user;

    @Mock
    UserModel userModel;

    @BeforeEach
    void setup() {
        lenient().when(userRepository.findByEmail("any_mail@mail.com")).thenReturn(Optional.of(userModel));
    }

    @Test
    void whenFindUserByEmail_givenValidEmail_thenOptionalUser() {
        Optional<User> optionalUser = repository.find("invalid_mail@mail.com");
        assertTrue(optionalUser.isPresent());
        assertEquals(user, optionalUser.get());
    }

    @Test
    void whenFindUserByEmail_givenInvalidEmail_thenOptionalEmpty() {
        when(userRepository.findByEmail("invalid_mail@mail.com")).thenReturn(Optional.empty());
        Optional<User> user = repository.find("invalid_mail@mail.com");
        assertTrue(user.isEmpty());
    }

}