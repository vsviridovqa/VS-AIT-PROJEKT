package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static User positiveUser(){
        User user = User.builder()
                .username(faker.internet().emailAddress())
                .password("Qwerty143!")
                .build();
        return user;

    }
}
