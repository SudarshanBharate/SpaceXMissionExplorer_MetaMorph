package com.test.MetamorphAssesment.service;

import java.time.LocalDate;

import com.test.MetamorphAssesment.model.ApodResponseDto;

public interface ApodService {

	ApodResponseDto getApodByDate(LocalDate date);

	ApodResponseDto getTodayApod();

}
