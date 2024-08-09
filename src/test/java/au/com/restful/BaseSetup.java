package au.com.restful;

import au.com.config.Configuration;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.lessThan;

public class BaseSetup {

    @BeforeClass
    public void setup () throws IOException, URISyntaxException {

        Configuration.loadProperties();
        String urlPetStore = Configuration.getProperty("petStoreURI");
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(urlPetStore)
                .addHeader ("Accept", "application/json")
                .addFilter (new RequestLoggingFilter ())
                .addFilter (new ResponseLoggingFilter ())
                .build ();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder ().expectResponseTime (lessThan (20000L))
                .build ();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

    }
}
