package com.liladhar.clinicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liladhar.clinicals.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
