package com.test.MetamorphAssesment.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.MetamorphAssesment.daoLayer.ApodDao;
import com.test.MetamorphAssesment.entity.ApodEntity;
import com.test.MetamorphAssesment.model.ApodResponseDto;
import com.test.MetamorphAssesment.exceptionLayer.*;

@Service
public class ApodServiceImpl implements ApodService {

	private final ApodDao dao;
	private final RestTemplate restTemplate;

	@Value("${nasa.api.key}")
	private String apiKey;

	@Value("${nasa.api.url}")
	private String apiUrl;

	public ApodServiceImpl(ApodDao dao, RestTemplate restTemplate) {
		this.dao = dao;
		this.restTemplate = restTemplate;
	}

	@Override
	public ApodResponseDto getApodByDate(LocalDate date) {

		return dao.findByDate(date).map(this::mapToDto).orElseGet(() -> fetchFromNasa(date));

	}

	@Override
	public ApodResponseDto getTodayApod() {
		return getApodByDate(LocalDate.now());

	}

	private ApodResponseDto fetchFromNasa(LocalDate date) {

		String url = apiUrl + "?api_key=" + apiKey + "&date=" + date;

		try {
			ApodEntity entity = restTemplate.getForObject(url, ApodEntity.class);
			dao.save(entity);
			return mapToDto(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExternalApiException("NASA API failed");
		}
	}

	private ApodResponseDto mapToDto(ApodEntity entity) {
		ApodResponseDto dto = new ApodResponseDto();
		dto.setDate(entity.getApodDate());
		dto.setTitle(entity.getTitle());
		dto.setExplanation(entity.getExplanation());
		dto.setImageUrl(entity.getUrl());
		dto.setMediaType(entity.getMediaType());
		dto.setCopyright(entity.getCopyright());
		return dto;
	}

}
