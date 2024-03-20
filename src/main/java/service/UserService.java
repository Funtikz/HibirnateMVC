package service;

import dto.UserDto;
import entity.User;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final  UserMapper userMapper;
    public boolean delete(Integer id){
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(user.getId()));
        return maybeUser.isPresent();
    }

    public void update(User user){
        userRepository.update(user);
    }


    public Optional<UserDto> findById(Integer id){
        return userRepository.findById(id)
                .map(userMapper::mapFrom);
    }

    public List<UserDto> findAll(){
        return userRepository.findAll().stream().map(userMapper::mapFrom).toList();
    }
}
