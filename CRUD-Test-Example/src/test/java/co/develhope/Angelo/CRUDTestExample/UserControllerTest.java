package co.develhope.Angelo.CRUDTestExample;

import co.develhope.Angelo.CRUDTestExample.controllers.UserController;
import co.develhope.Angelo.CRUDTestExample.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	void userControllerLoads() {
		assertThat(userController).isNotNull();
	}

	private User createAUser() throws Exception {
		User user = new User();
		user.setActive(true);
		user.setName("Angelo");
		user.setSurname("Zammataro");
		user.setAge(45);

		return createAUser(user);
	}

	private User createAUser(User user) throws Exception {
		MvcResult result = createAUserRequest(user);
		User userFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
		return userFromResponse;
	}
	private MvcResult createAUserRequest() throws Exception {
		User user = new User();
		user.setActive(true);
		user.setName("Angelo");
		user.setSurname("Zammataro");
		user.setAge(45);

		return createAUserRequest(user);
	}

	private MvcResult createAUserRequest(User user) throws Exception{
		if(user == null) return null;
		String userJSON = objectMapper.writeValueAsString(user);

		return this.mockMvc.perform(post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void createAUserTest() throws Exception {
		User userFromResponse = createAUser();
		assertThat(userFromResponse.getId()).isNotNull();
	}

	@Test
	void readUserList() throws Exception {
		createAUserRequest();

		MvcResult result = this.mockMvc.perform(get("/user/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		List<User> usersFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
		System.out.println("User in database are:" + usersFromResponse.size());
		assertThat(usersFromResponse.size()).isNotZero();
	}

	@Test
	void readSingleUser() throws Exception {
		User user = createAUser();
		assertThat(user.getId()).isNotNull();

		MvcResult result = this.mockMvc.perform(get("/user/" + user.getId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		User usersFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
		assertThat(usersFromResponse.getId()).isEqualTo(user.getId());
	}

	
}
