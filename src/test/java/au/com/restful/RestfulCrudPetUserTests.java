package au.com.restful;

import au.com.data.pet.PetStoreData;
import au.com.data.pet.PetStoreUserDataBuilder;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static au.com.data.pet.PetStoreUserDataBuilder.getPetStoreData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestfulCrudPetUserTests extends BaseSetup {

    private PetStoreData newPetStoreData;
    private String userName;
    private String password;
    public String path = "src/test/resources/testData/petData.csv";


    @BeforeTest
    public void testSetup() throws URISyntaxException, IOException, CsvException {
        newPetStoreData = getPetStoreData(path);
    }

    @Test(priority = 0)
    @Description("Creating new user Record - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Pet store User using rest-assured")
    @Step("Create new User record")
    public void createUsersTest() throws URISyntaxException, IOException, CsvException {
        given().body(newPetStoreData)
                .header("Content-Type", "application/json")
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body( "code", equalTo(200))
                .extract()
                .path("code");

        userName = PetStoreUserDataBuilder.getPetStoreData(path).getUserName();
        password = PetStoreUserDataBuilder.getPetStoreData(path).getPassword();
    }

    @Test(priority = 1)
    @Description("Creating new array of usrs Record - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("PetStore User using rest-assured")
    @Step("Create new User record")
    public void createArrayOfUsersTest() throws URISyntaxException, IOException, CsvException {
        Map<String,Object> userPayload = new HashMap();
        userPayload.put("id", PetStoreUserDataBuilder.getPetStoreData(path).getId());
        userPayload.put("firstName", PetStoreUserDataBuilder.getPetStoreData(path).getFirstname());
        userPayload.put("lastName", PetStoreUserDataBuilder.getPetStoreData(path).getLastname());
        userPayload.put("userName", PetStoreUserDataBuilder.getPetStoreData(path).getUserName());
        userPayload.put("password", PetStoreUserDataBuilder.getPetStoreData(path).getPassword());
        userPayload.put("email", PetStoreUserDataBuilder.getPetStoreData(path).getEmail());
        userPayload.put("mobile", PetStoreUserDataBuilder.getPetStoreData(path).getMobile());
        userPayload.put("userStatus", PetStoreUserDataBuilder.getPetStoreData(path).getUserStatus());

        List<Map<String,Object>> arrayJSONPayload = new ArrayList<>();
        arrayJSONPayload.add(userPayload);

        given().body(arrayJSONPayload)
                .header("Content-Type", "application/json")
                .when()
                .post("/user/createWithArray")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body( "code", equalTo(200), "message", equalTo("ok"))
                .extract()
                .path("code");
    }
    @Test (priority = 2)
    @Description("User Logout scenario - Get request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Logout using rest-assured")
    @Step("Get user logout")
    public void getUserLogOutTest() {
        given().get("/user/logout")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("code", equalTo(200), "message", equalTo("ok"));
    }

    @Test (priority = 3)
    @Description("Retrieve User details scenario - Get request")
    @Severity(SeverityLevel.NORMAL)
    @Story("User retrieval using rest-assured")
    @Step("Get user details")
    public void getUserDetailsTest() {
        given().get("/user/login?username="+userName)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("code", equalTo(200));
    }

    @Test (priority = 4)
    @Description("User LogIn scenario - Get request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User LogIn using rest-assured")
    @Step("Get user logout")
    public void getUserLogInTest() {
        given().get("/user/login?username="+userName+"&password="+password)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("code", equalTo(200));
    }

    @Test (priority = 5)
    @Description("Delete User scenario - Delete request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Delete User using rest-assured")
    @Step("Delete user")
    public void deleteUserTest() {
        given().delete("/user/"+userName)
                .then()
                .statusCode(404);
    }

    @Test (priority = 6)
    @Description("Update User scenario - Put request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Update User using rest-assured")
    @Step("Update user")
    public void updateUserTest() {
         given().body(newPetStoreData)
                .header("Content-Type", "application/json")
                .put("/user/"+userName)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("code", equalTo(200));
    }

}
