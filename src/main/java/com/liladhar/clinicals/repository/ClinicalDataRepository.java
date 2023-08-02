package com.liladhar.clinicals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liladhar.clinicals.model.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

	
	List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);

}
