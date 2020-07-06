package org.testapp.figure.model;

import static org.springframework.util.StringUtils.isEmpty;
import static org.testapp.figure.model.FigureType.CIRCLE;

import java.math.BigDecimal;

import org.testapp.figure.dto.FigureData;

public class Circle implements GeometricFigure {
	
	private String id;
	
	private BigDecimal diameter;

	Circle(String figureId, BigDecimal circleDiameter) {
		if ( isEmpty(figureId) ) {
			throw new IllegalArgumentException("Figure's id is null or empty");
		}
		this.id = figureId;
		if ( circleDiameter == null ) {
			throw new IllegalArgumentException("A 'diameter' value is required to create a figure of type CIRCLE");
		}
		if ( circleDiameter.compareTo(BigDecimal.ZERO) <= 0 ) {
			throw new IllegalArgumentException("A cicle's diameter should be positive");
		}
		this.diameter = circleDiameter;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public BigDecimal getSurface() {
		return diameter.divide(new BigDecimal(2)).multiply(new BigDecimal(Math.PI));
	}

	@Override
	public BigDecimal getHeight() {
		return null;
	}

	@Override
	public BigDecimal getBase() {
		return null;
	}

	@Override
	public BigDecimal getDiameter() {
		return diameter;
	}

	@Override
	public FigureType getType() {
		return CIRCLE;
	}

	@Override
	public FigureData asData() {
		FigureData data = new FigureData();
		data.setId(id);
		data.setDiameter(diameter);
		data.setSurface(getSurface());
		data.setType(getType());
		return data;
	}

}
