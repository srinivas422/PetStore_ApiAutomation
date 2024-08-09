package au.com.data.pet;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@Builder
public class PetStoreData {
    private int          id;
    private String       firstname;
    private String       lastname;
    private String       userName;
    private String       password;
    private String       email;
    private String       mobile;
    private int          userStatus;
    private int          petId;
    private String       petName;
    private int          quantity;
    private String       shipDate;
    private String       status;
    private boolean      complete;
    private String       petStatus;
    private Category     category;
    private List<String> photoUrls;
    private List<Tag>    tags;


    @Data
    @Builder
    public static class Category {
        private int id;
        private String name;
    }

    @Data
    @Builder
    public static class Tag {
        private int id;
        private String name;

        // Getters, Setters, Builder pattern, etc.
    }
}