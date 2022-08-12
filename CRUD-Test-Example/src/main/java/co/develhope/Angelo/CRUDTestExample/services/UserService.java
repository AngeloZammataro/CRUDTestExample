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

    public void setUserActivationStatus(Long userId, boolean isActive){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) return;
        user.get().setActive(isActive);
        userRepository.save(user.get());
    }
}
