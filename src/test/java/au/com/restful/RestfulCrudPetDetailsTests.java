package au.com.restful;

import au.com.data.pet.PetStoreData;
import au.com.data.pet.PetStoreUserDataBuilder;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static au.com.data.pet.PetStoreUserDataBuilder.getPetData;
import static au.com.data.pet.PetStoreUserDataBuilder.getPetDetailsData;
import static io.restassured.RestAssured.given;

public class RestfulCrudPetDetailsTests extends BaseSetup {

    private PetStoreData newPetData;
    private int petId;
    private String petStatus;
    public String path = "src/test/resources/testData/petData.csv";

    @BeforeTest
    public void testSetup() throws URISyntaxException, IOException, CsvException {
        newPetData = getPetData(path);
    }

    @Test(priority = 0)
    @Description("Creating new pet details - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Pet store - new pet details using rest-assured")
    @Step("Placing new pet details")
    public void createPetDetailsCategoryTest() throws URISyntaxException, IOException, CsvException {
        newPetData = getPetDetailsData(path);
        petId = PetStoreUserDataBuilder.getPetData(path).getPetId();
        petStatus = PetStoreUserDataBuilder.getPetDetailsData(path).getPetStatus();
        given().body(newPetData)
                .header("Content-Type", "application/json")
                .when()
                .post("/pet")
                .then()
                .statusCode(200);
    }

    @Test(priority = 1)
    @Description("Updating an existing pet details - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Pet store - update pet details using rest-assured")
    @Step("Updating pet details")
    public void updatePetDetailsCategoryTest() throws URISyntaxException, IOException, CsvException {
        newPetData = getPetDetailsData(path);
        petId = PetStoreUserDataBuilder.getPetData(path).getPetId();
        petStatus = PetStoreUserDataBuilder.getPetDetailsData(path).getPetStatus();
        given().body(newPetData)
                .header("Content-Type", "application/json")
                .when()
                .put("/pet")
                .then()
                .statusCode(200);
    }
    @Test(priority = 2)
    @Description("Creating new pet details - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Pet store - new pet details using rest-assured")
    @Step("Placing new pet details")
    public void createPetDetailsTest() throws URISyntaxException, IOException, CsvException {
        petId = PetStoreUserDataBuilder.getPetData(path).getPetId();
            given().when()
                    .post("/pet/"+petId)
                    .then()
                    .statusCode(404); /*details not found*/
    }

    @Test(priority = 3)
    @Description("Creating new pet details - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Pet store - new pet details using rest-assured")
    @Step("Placing new pet details")
    public void createPetImageTest() throws URISyntaxException, IOException, CsvException {
        petId = PetStoreUserDataBuilder.getPetData(path).getPetId();
        File file = new File("src/test/resources/testData/pet.jpeg");
        given().multiPart("file", file, "image/jpeg")
                .header("Content-Type", "multipart/form-data")
                .when()
                .post("/pet/" + petId + "/uploadImage")
                .then()
                .statusCode(200); /*unsupported media type*/
    }

    @Test (priority = 4)
    @Description("Retrieve Pet Details scenario - Get request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get pet details by id using rest-assured")
    @Step("Get pet details")
    public void getPetDetailsTest() {
        given().get("/pet/" + petId)
                .then()
                .statusCode(404);
    }

    @Test (priority = 5)
    @Description("Get Pet details by status scenario - Get request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Pet details by Status using rest-assured")
    @Step("Get Pet details by Status")
    public void getPetDetailsByStatusTest() throws URISyntaxException, IOException, CsvException {
        petStatus = PetStoreUserDataBuilder.getPetData(path).getPetStatus();

        given().get("/pet/findByStatus?status=" + petStatus)
                .then()
                .statusCode(200);
    }

    @Test (priority = 6)
    @Description("Delete Pet details scenario - Delete request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Delete pet details by petId using rest-assured")
    @Step("Delete Pet Details")
    public void deleteUserTest() {
        given().delete("/pet/" + petId)
                .then()
                .statusCode(404); /*Pet details not found to delete*/
    }


}
