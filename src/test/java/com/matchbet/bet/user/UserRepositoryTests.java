package com.matchbet.bet.user;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepo;

    @Test
    @DirtiesContext
    public void UserRepository_SaveUser_ReturnSavedUser() {
        UserEntity savedEntity = createAndSaveUserEntity();
        Assertions.assertThat(savedEntity).isNotNull();
        Assertions.assertThat(savedEntity.getId()).isGreaterThan(0);
        Assertions.assertThat(savedEntity.getCreatedAt()).isNotNull();
        Assertions.assertThat(savedEntity.getBets()).isNull();
    }

    @Test
    @DirtiesContext
    public void UserRepository_FindById_ReturnUser() {
        UserEntity savedEntity = createAndSaveUserEntity();
        UserEntity user = userRepo.findById(savedEntity.getId()).get();
        Assertions.assertThat(user).isNotNull();
    }

    @Test
    @DirtiesContext
    public void UserRepository_FindAll_ReturnListOfUsers() {
        createAndSaveUserEntity();
        createAndSaveUserEntity();

        List<UserEntity> users = userRepo.findAll();
        System.out.println(userRepo.findAll().size());

        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.size()).isEqualTo(2);
        Assertions.assertThat(users.stream().map(UserEntity::getId).toList()).doesNotHaveDuplicates();
    }

    @Test
    @DirtiesContext
    public void UserRepository_UpdateUser_ReturnUserNotNull() {
        UserEntity savedEntity = createAndSaveUserEntity();

        UserEntity user = userRepo.findById(savedEntity.getId()).get();
        user.setUsername("mat");
        user.setEmail("mat@mat.pl");

        UserEntity updatedUser = userRepo.save(user);

        Assertions.assertThat(updatedUser.getUsername()).isNotNull();
        Assertions.assertThat(updatedUser.getEmail()).isNotNull();
    }

    @Test
    @DirtiesContext
    public void UserRepository_Delete_ReturnUserIsEmpty() {
        UserEntity savedEntity = createAndSaveUserEntity();
        userRepo.deleteById(savedEntity.getId());

        Optional<UserEntity> user = userRepo.findById(savedEntity.getId());

        Assertions.assertThat(user).isEmpty();
    }

    @Test
    @DirtiesContext
    public void UserRepository_FindByUsername_ReturnUser() {
        UserEntity savedEntity = createAndSaveUserEntity();
        UserEntity user = userRepo.findByUsername(savedEntity.getUsername()).get();
        Assertions.assertThat(user).isNotNull();
    }

    private UserEntity createAndSaveUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@test.pl");
        userEntity.setUsername("test");
        userEntity.setPassword("123");

        return userRepo.save(userEntity);
    }
}
