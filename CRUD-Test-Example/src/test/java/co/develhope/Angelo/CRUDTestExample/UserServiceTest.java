package co.develhope.Angelo.CRUDTestExample;

import co.develhope.Angelo.CRUDTestExample.entities.User;
import co.develhope.Angelo.CRUDTestExample.repositories.UserRepository;
import co.develhope.Angelo.CRUDTestExample.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void checkUserActivation()throws Exception{
        User user = new User();
        user.setActive(true);
        user.setName("Angelo");
        user.setSurname("Zammataro");
        user.setAge(45);

        User userFromDB = userRepository.save(user);
        assertThat(userFromDB).isNotNull();
        assertThat(userFromDB.getId()).isNotNull();

        User userFromService = userService.setUserActivationStatus(user.getId(),true);
        assertThat(userFromService).isNotNull();
        assertThat(userFromService.getId()).isNotNull();
        assertThat(userFromService.isActive()).isTrue();

        User userFromFind = userRepository.findById(user.getId()).get();
        assertThat(userFromFind).isNotNull();
        assertThat(userFromFind.getId()).isNotNull();
        assertThat(userFromFind.getId()).isEqualTo(userFromDB.getId());
        assertThat(userFromFind.isActive()).isTrue();


    }
}
