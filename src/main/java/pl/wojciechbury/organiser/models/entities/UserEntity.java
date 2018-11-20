package pl.wojciechbury.organiser.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wojciechbury.organiser.models.forms.RegisterForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class UserEntity {

    private String login, city, postalCode;

    @JsonIgnore
    private String  password;

    @GeneratedValue
    @Id
    private int id;

    public UserEntity(RegisterForm registerForm){
        login = registerForm.getLogin();
        password = registerForm.getPassword();
        city = registerForm.getCity();
        postalCode = registerForm.getPostalCode();
    }
}
