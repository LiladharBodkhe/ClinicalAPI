package com.liladhar.clinicals.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liladhar.clinicals.model.ClinicalData;
import com.liladhar.clinicals.model.Patient;
import com.liladhar.clinicals.repository.PatientRepository;
import com.liladhar.clinicals.util.BMICalculator;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {

	private PatientRepository patientRepository;
	Map<String,String> filters =new HashMap<>();
	
	@Autowired
	PatientController(PatientRepository patientRepository){
		this.patientRepository= patientRepository;
	}
	
	@RequestMapping(value="/patients",method=RequestMethod.GET)
	public List<Patient> getPatients(){
		return patientRepository.findAll();
	}
	
	@RequestMapping(value="/patients/{id}")
	public Patient getPatient(@PathVariable("id")int id) {
		return  patientRepository.findById(id).get();
	}
	
	@RequestMapping(value="/patients",method=RequestMethod.POST)
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	@RequestMapping(value="/patients/analyze/{id}",method=RequestMethod.GET)
	public Patient analyze(@PathVariable("id")int id) {
		Patient patient=patientRepository.findById(id).get();
		List<ClinicalData> clinicalData =patient.getClinicalData();
		List<ClinicalData> duplicateClinicalData =new ArrayList<>(clinicalData);
		for(ClinicalData eachEntry:duplicateClinicalData) {
			
				if(filters.containsKey(eachEntry.getComponentName())) {
					clinicalData.remove(eachEntry);
					continue;
				}else {
					filters.put(eachEntry.getComponentName(), null);
				}
				BMICalculator.calculateBMI(clinicalData, eachEntry);
				
			}
		filters.clear();
		return patient;
	}

	
}
