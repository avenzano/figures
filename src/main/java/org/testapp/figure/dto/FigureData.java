package org.testapp.figure.dto;

import java.math.BigDecimal;

import org.testapp.figure.model.FigureType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FigureData {

	private String id;
	
	private FigureType type;
	
	private BigDecimal height;
	
	private BigDecimal base;
	
	private BigDecimal diameter;
	
	private BigDecimal surface;

	@JsonProperty
	public String getId() {
		return id;
	}

	@JsonIgnore
	public void setId(String id) {
		this.id = id;
	}

	public FigureType getType() {
		return type;
	}

	public void setType(FigureType type) {
		this.type = type;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public BigDecimal getDiameter() {
		return diameter;
	}

	public void setDiameter(BigDecimal diameter) {
		this.diameter = diameter;
	}

	public BigDecimal getSurface() {
		return surface;
	}

	public void setSurface(BigDecimal surface) {
		this.surface = surface;
	}
	
}
