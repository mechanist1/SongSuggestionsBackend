package org.example.apitestingwitherrorthrowing.Controllers;


import lombok.AllArgsConstructor;
import org.example.apitestingwitherrorthrowing.Dtos.ErrorRequest;
import org.example.apitestingwitherrorthrowing.Services.ErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("error")
@AllArgsConstructor
public class ErrorController {

    ErrorService errorService;


//    @GetMapping
//    public ResponseEntity<String> errorhandling(){
//        return ResponseEntity.status(200).body("We caught an error ");
//    }

    @PostMapping
    public ResponseEntity<String> errorhandling(@RequestBody String body){
        return ResponseEntity.status(200).body(body);
    }

    @PostMapping("v1/error")
    public ResponseEntity<String> errorhandlingV1(@RequestBody ErrorRequest fail){
        errorService.doWork(fail.getValue());
        return ResponseEntity.accepted().body("worked");
    }


}
