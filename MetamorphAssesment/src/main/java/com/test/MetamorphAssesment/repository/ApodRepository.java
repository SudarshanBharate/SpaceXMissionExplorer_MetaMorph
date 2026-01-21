package com.test.MetamorphAssesment.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.MetamorphAssesment.entity.ApodEntity;


public interface ApodRepository extends JpaRepository<ApodEntity, Long>{
	
	Optional<ApodEntity> findByApodDate(LocalDate apodDate);

}
