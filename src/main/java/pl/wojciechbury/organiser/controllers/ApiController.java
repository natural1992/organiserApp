package pl.wojciechbury.organiser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojciechbury.organiser.models.forms.RegisterForm;
import pl.wojciechbury.organiser.models.forms.UserForm;
import pl.wojciechbury.organiser.models.services.ApiService;

@RestController
public class ApiController {

    final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService){
        this.apiService = apiService;
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id){
        return apiService.getUserById(id);
    }

    @GetMapping("/api/user")
    public ResponseEntity getListOfUsers(){
        return apiService.getListOfUsers();
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id){
        return apiService.deleteUserById(id);
    }

    @PostMapping(value = "/api/user", consumes = "application/json")
    public ResponseEntity saveNewUser(@RequestBody RegisterForm user){
        return apiService.saveUser(user);
    }
}
