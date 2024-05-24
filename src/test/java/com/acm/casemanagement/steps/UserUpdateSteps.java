//package com.acm.casemanagement.steps;
//
//
//import com.acm.casemanagement.dto.UserDto;
//import com.acm.casemanagement.service.UserService;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.When;
//import org.junit.Assert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class UserUpdateSteps {
//
//
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ResponseEntity<UserDto> response;
//    private final String baseUrl = "http://localhost:8080/api/users";
//
//
//
//
//
//    @Given("a user with ID {id} exists")
//    public void a_user_with_id_exists(Long id) {
//        Optional<UserDto> user = userService.getUserById(id);
//        assertNotNull(user, "User with ID " + id + " does not exist");
//    }
//
//    @When("I send a PUT request to {long} with the following data")
//    public void i_send_a_put_request_to_with_the_following_data(String url, Map<String, String> data) {
//        UserDto userDto =UserDto.builder().firstname(data.get("firstname"))
//                .email(data.get("lastname")).username(data.get("username"))
//                .password(data.get("password")).build();
//        response = restTemplate.exchange(baseUrl+ "/updateUser/id", HttpMethod.PUT, new HttpEntity<>(userDto), UserDto.class);
//        Assert.assertEquals(200,response.getStatusCode().value());
//
//    }
////    @Then("the response status should be {int}")
////    public void the_response_status_should_be(int statusCode) {
////        assertEquals(statusCode, response.getStatusCode().value());
////    }
//    @And("the response should contain the updated user details")
//    public void the_response_should_contain_the_updated_user_details(Map<String, String> data) {
//        UserDto updatedUser = response.getBody();
//        assertEquals(data.get("firstname"), updatedUser.getFirstname());
//        assertEquals(data.get("lastname"), updatedUser.getLastname());
//        assertEquals(data.get("email"), updatedUser.getEmail());
//        assertEquals(data.get("username"), updatedUser.getUsername());
//        assertEquals(data.get("password"), updatedUser.getPassword());
//        Assert.assertEquals(response, updatedUser);
//
//    }
//
//}
