package springtest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import springtest.entity.User;
import springtest.repository.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    @Transactional
    public int createNewUser(String name) {
        int nextId = userRepository.findNextId();
        userRepository.newUser(new User(nextId, name));
        return nextId;
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public void deleteUserByName(String name) {
        userRepository.deleteUserByName(name);
    }

    @Override
    public List<User> findUsersByName(String name) {
        return userRepository.findUsersByName(name);
    }
}
