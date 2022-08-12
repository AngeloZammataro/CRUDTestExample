package co.develhope.Angelo.CRUDTestExample.services;

import co.develhope.Angelo.CRUDTestExample.entities.User;
import co.develhope.Angelo.CRUDTestExample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User setUserActivationStatus(Long userId, boolean isActive){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) return null;
        user.get().setActive(isActive);
        return userRepository.save(user.get());
    }
}
