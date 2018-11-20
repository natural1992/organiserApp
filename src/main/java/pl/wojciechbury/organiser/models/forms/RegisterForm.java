package pl.wojciechbury.organiser.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegisterForm {
    private String login, password, passwordRepeat, city;

    @Pattern(regexp = "[0-9]{5}")
    private String postalCode;
}
