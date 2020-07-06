package org.testapp.figure.model;

import static org.springframework.util.StringUtils.isEmpty;
import static org.testapp.figure.util.FigureUtil.isPositive;

import java.math.BigDecimal;

import org.testapp.figure.dto.FigureData;

public abstract class PolygonFigure implements GeometricFigure {

	private String id;
	
	private BigDecimal base;
	
	private BigDecimal height;
	
	private FigureType type;
	
	protected PolygonFigure(String id, FigureType fType, BigDecimal base, BigDecimal height) {
		if ( isEmpty(id) ) {
			throw new IllegalArgumentException("A figure's id cannot be null or empty");
		}
		this.id = id;
		if ( fType == null ) {
			throw new IllegalArgumentException("A figure type must be privided");
		}
		this.type = fType;
		if ( !isPositive(base) || !isPositive(height)) {
			throw new IllegalArgumentException("'base' and 'height' are required values of a polygon (and should be positive numbers!)");
		}
		this.base = base;
		this.height = height;
	}
	
	BigDecimal getRectangularArea() {
		return base.multiply(height);
	}
	
	public String getId() {
		return id;
	}

	@Override
	public BigDecimal getHeight() {
		return height;
	}

	@Override
	public BigDecimal getBase() {
		return base;
	}

	@Override
	public FigureType getType() {
		return type;
	}
	
	@Override
	public BigDecimal getDiameter() {
		return null;
	}

	@Override
	public FigureData asData() {
		FigureData data = new FigureData();
		data.setId(id);
		data.setType(type);
		data.setBase(base);
		data.setHeight(height);
		data.setSurface(getSurface());
		return data;
	}
	
	
}
