package au.com.data.pet;

import au.com.actions.ReadCSVFile;
import com.opencsv.exceptions.CsvException;
import net.datafaker.Faker;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class PetStoreUserDataBuilder {

    private static final Faker FAKER = new Faker ();
    public static PetStoreData getPetStoreData(String path) throws URISyntaxException, IOException, CsvException {
        String[] csvData = ReadCSVFile.cSVDataRead(path);
        return PetStoreData.builder()
            .id(FAKER.number()
                .numberBetween(1, 1000))
            .firstname (FAKER.name().firstName ())
            .lastname (FAKER.name().lastName ())
            .userName(FAKER.name ()
                    .username ())
            .password(FAKER.internet().password())
            .email(csvData[0])
            .mobile(csvData[1])
            .userStatus(FAKER.number().numberBetween(0, 1))
            .build();
    }

    public static PetStoreData getPetInventoryData(String path) throws URISyntaxException, IOException, CsvException {
        SimpleDateFormat formatter = new SimpleDateFormat ("YYYY-MM-dd");
        String[] csvData = ReadCSVFile.cSVDataRead(path);
        return PetStoreData.builder()
                .id(FAKER.number()
                        .numberBetween(1, 1000))
                .petId(FAKER.number()
                        .numberBetween(1, 1000))
                .quantity(FAKER.number()
                        .numberBetween(1, 10))
                .shipDate (formatter.format (FAKER.date ()
                        .past (5, TimeUnit.DAYS)))
                .lastname (FAKER.name().lastName ())
                .userName(FAKER.name ()
                        .username ())
                .status(csvData[2])
                .complete(Boolean.parseBoolean(csvData[3]))
                .build();
    }

    public static PetStoreData getPetData(String path) throws URISyntaxException, IOException, CsvException {
        String[] csvData = ReadCSVFile.cSVDataRead(path);
        return PetStoreData.builder()
                .petId(FAKER.number()
                        .numberBetween(1, 1000))
                .petStatus(csvData[4])
                .build();
    }

    public static PetStoreData getPetDetailsData(String path) throws URISyntaxException, IOException, CsvException {
        String[] csvData = ReadCSVFile.cSVDataRead(path);

        return PetStoreData.builder()
                .id(FAKER.number().numberBetween(1, 1000))
                .category(PetStoreData.Category.builder()
                        .id(FAKER.number().numberBetween(1, 100))
                        .name(FAKER.animal().name())
                        .build())
                .petName(FAKER.animal().name())
                .photoUrls(Collections.singletonList(FAKER.internet().url()))
                .tags(Collections.singletonList(PetStoreData.Tag.builder()
                        .id(FAKER.number().numberBetween(1, 100))
                        .name(FAKER.commerce().productName())
                        .build()))
                .status(csvData[2])
                .build();
    }
}