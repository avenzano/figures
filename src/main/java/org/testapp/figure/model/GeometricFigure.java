package org.testapp.figure.model;

import java.math.BigDecimal;

import org.testapp.figure.dto.FigureData;

public interface GeometricFigure {

	String getId();
	
	BigDecimal getSurface();
	
	BigDecimal getHeight();
	
	BigDecimal getBase();
	
	BigDecimal getDiameter();
	
	FigureType getType();

	FigureData asData();
}
