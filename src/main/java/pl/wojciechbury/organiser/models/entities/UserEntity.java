package pl.wojciechbury.organiser.models.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    private String login, password, city, postalCode;

    @GeneratedValue
    @Id
    private int id;
}
