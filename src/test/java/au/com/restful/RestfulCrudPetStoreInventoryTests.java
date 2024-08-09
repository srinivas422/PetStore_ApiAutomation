package au.com.restful;

import au.com.data.pet.PetStoreData;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static au.com.data.pet.PetStoreUserDataBuilder.getPetInventoryData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestfulCrudPetStoreInventoryTests extends BaseSetup {

    private PetStoreData newPetStoreInventoryData;
    private int orderId;
    public String path = "src/test/resources/testData/petData.csv";

    @BeforeTest
    public void testSetup() throws URISyntaxException, IOException, CsvException {
        newPetStoreInventoryData = getPetInventoryData(path);
    }

    @Test(priority = 0)
    @Description("Creating new order - Post request")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Pet store new order using rest-assured")
    @Step("Placing new order")
    public void createOrderTest() throws URISyntaxException, IOException, CsvException {
        orderId = given().body(newPetStoreInventoryData)
                    .header("Content-Type", "application/json")
                    .when()
                    .post("/store/order")
                    .then()
                    .statusCode(200)
                    .and()
                    .assertThat()
                    .body( "status", equalTo(newPetStoreInventoryData.getStatus()))
                    .extract()
                    .path("id");
    }

    @Test (priority = 1)
    @Description("Retrieve Order Details scenario - Get request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get order details by id using rest-assured")
    @Step("Get order details")
    public void getUserLogOutTest() {
        given().get("/store/order/"+ orderId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("status", equalTo(newPetStoreInventoryData.getStatus()));
    }

    @Test (priority = 2)
    @Description("Delete Order scenario - Delete request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Delete Order by orderId using rest-assured")
    @Step("Delete Order")
    public void deleteUserTest() {
        given().delete("/store/order/"+ orderId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("code", equalTo(200));
    }

    @Test (priority = 3)
    @Description("Store Inventory details scenario - Get request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Store Inventory details using rest-assured")
    @Step("Get Store Inventory details")
    public void getUserLogInTest() {
        given().get("/store/inventory")
                .then()
                .statusCode(200);
    }

}
