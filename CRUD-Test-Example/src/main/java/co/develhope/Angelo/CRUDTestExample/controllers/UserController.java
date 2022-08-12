package co.develhope.Angelo.CRUDTestExample.controllers;

import co.develhope.Angelo.CRUDTestExample.entities.User;
import co.develhope.Angelo.CRUDTestExample.repositories.UserRepository;
import co.develhope.Angelo.CRUDTestExample.services.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    //CREATE user
    @PostMapping("")
    public @ResponseBody User create(@RequestBody User user){
        return userRepository.save(user);
    }

    //READ all users
    @GetMapping("/")
    public @ResponseBody List<User> getList(){
        return userRepository.findAll();
    }

    //READ single user
    @GetMapping("/{id}")
    public @ResponseBody User getSingle(@PathVariable long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }else {
            return null;
        }
    }

    //UPDATE single user
    @PutMapping("/{id}")
    public void update(@PathVariable long id,@RequestBody @NotNull User user){
        user.setId(id);
        userRepository.save(user);
    }

    //UPDATE single user
    @PutMapping("/{id}/activation")
    public void setUserActive(@PathVariable long id, @RequestParam("activated") boolean activated){
        userService.setUserActivationStatus(id,activated);
    }

    //DELET all
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        userRepository.deleteById(id);
    }


}
