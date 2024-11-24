package group9.movie_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Test login
@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping()
    public ResponseEntity<String> sayHello() {

        String a = "Hello from secured endpoint";
        return ResponseEntity.ok(a);
    }
}
