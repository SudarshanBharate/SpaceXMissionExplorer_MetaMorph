package com.test.MetamorphAssesment.daoLayer;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.MetamorphAssesment.entity.ApodEntity;
import com.test.MetamorphAssesment.repository.ApodRepository;


@Repository
public class ApodDao{

	private final ApodRepository repository;
	
	public ApodDao(ApodRepository repository) {
        this.repository = repository;
    }

    public Optional<ApodEntity> findByDate(LocalDate date) {
        return repository.findByApodDate(date);
    }

    public ApodEntity save(ApodEntity entity) {
        return repository.save(entity);
    }
}
