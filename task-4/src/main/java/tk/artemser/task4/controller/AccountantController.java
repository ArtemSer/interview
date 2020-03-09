package tk.artemser.task4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.artemser.task4.service.AccountantService;

@RestController
@RequestMapping("/accountant")
public class AccountantController {

    @Autowired
    private AccountantService accountantService;

    @GetMapping
    public ResponseEntity get(){
        return new ResponseEntity<>(accountantService.summary(), HttpStatus.OK);
    }
}