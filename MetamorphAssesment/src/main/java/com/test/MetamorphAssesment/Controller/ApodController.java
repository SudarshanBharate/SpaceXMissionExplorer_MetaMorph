package com.test.MetamorphAssesment.Controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.MetamorphAssesment.model.ApodResponseDto;
import com.test.MetamorphAssesment.service.ApodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/apod")
public class ApodController {
	
	private final ApodService service;

    public ApodController(ApodService service) {
        this.service = service;
    }

    @GetMapping("/today")
    public ApodResponseDto getTodayApod() {
        return service.getTodayApod();
    }

    @GetMapping
    public ApodResponseDto getApodByDate(@RequestParam String date) {
        return service.getApodByDate(LocalDate.parse(date));
    }

}
