package pl.wojciechbury.organiser.models.forms;

import lombok.Data;

@Data
public class RegisterForm {
    private String login, password, passwordRepeat, city, postalCode;
}
