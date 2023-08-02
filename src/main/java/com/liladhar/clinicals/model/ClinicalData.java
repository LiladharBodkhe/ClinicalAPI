package com.liladhar.clinicals.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ClinicalData {

	@Id
	private int id;
	private String componentName;
	private String componentValue;
	private String  measuredDateTime;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_id",nullable=false)
	@JsonIgnore
	private Patient patient;
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentValue() {
		return componentValue;
	}
	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}
	public String getMeasuredDateTime() {
		return measuredDateTime;
	}
	public void setMeasuredDateTime(String measuredDateTime) {
		this.measuredDateTime = measuredDateTime;
	}
	
}
