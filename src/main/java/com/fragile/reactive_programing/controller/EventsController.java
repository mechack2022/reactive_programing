package com.fragile.reactive_programing.controller;

import com.fragile.reactive_programing.services.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/terra_home")
@RequiredArgsConstructor
@RestController
public class EventsController {

    private final ApiService apiService;

    @GetMapping("/events")
    public ResponseEntity<?> getEvents(){
      return new ResponseEntity<>(apiService.getEvents(), HttpStatus.OK);
    }
}
