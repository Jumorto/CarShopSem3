//package com.example.Sem3_CarShop.persistence.impl;
//
//import com.example.Sem3_CarShop.persistence.entity.UserEntity;
//import com.example.Sem3_CarShop.persistence.UserRepository;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class FakeUserRepositoryImpl implements UserRepository {
//
//    private static long NEXT_ID = 1;
//
//    private final List<UserEntity> savedUsers;
//
//    private List<UserEntity> GenerateUsers() {
//        List<UserEntity> TestUsers = new ArrayList<>();
//        for (NEXT_ID = 1;NEXT_ID < 10; NEXT_ID++) {
//            UserEntity ue = UserEntity.builder().id(NEXT_ID).type(1).username("John" + NEXT_ID).email(NEXT_ID + "doe@sahj.com").password(NEXT_ID + "sdajk" + NEXT_ID).build();
//            TestUsers.add(ue);
//        }
//        return TestUsers;
//    }
//
//    public FakeUserRepositoryImpl() {
//        this.savedUsers = GenerateUsers();
//    }
//
//    @Override
//    public Optional<UserEntity> findById(long userId) {
//        return this.savedUsers.stream()
//                .filter(userEntity -> userEntity.getId().equals(userId))
//                .findFirst();
//    }
//
//    @Override
//    public Optional<UserEntity> findByName(String username) {
//        return this.savedUsers.stream()
//                .filter(userEntity -> userEntity.getUsername().equals(username))
//                .findFirst();
//    }
//
//    @Override
//    public List<UserEntity> findAllByType(Integer type) {
//        return this.savedUsers
//                .stream()
//                .filter(userEntity -> userEntity.getType().equals(type))
//                .toList();
//    }
//
//    @Override
//    public List<UserEntity> findAll() {
//        return Collections.unmodifiableList(this.savedUsers);
//    }
//
//    //Checks fo existing user with the e-mail
//    public boolean containsEmail(final List<UserEntity> list, final String email){
//        return list.stream().filter(em -> em.getEmail().equals(email)).findFirst().isPresent();
//    }
//    @Override
//    public UserEntity save(UserEntity user) {
//        if (user.getId() == null) {
//            if(containsEmail(savedUsers, user.getEmail())){
//                System.out.println("This user already exists!!!!!");
//            }else{
//                user.setId(NEXT_ID);
//                NEXT_ID++;
//                this.savedUsers.add(user);
//            }
//        }
//        return user;
//    }
//
//    @Override
//    public void deleteById(long userId) {
//        this.savedUsers.removeIf(userEntity -> userEntity.getId().equals(userId));
//    }
//
//    @Override
//    public long count() {
//        return savedUsers.size();
//    }
//
//}
