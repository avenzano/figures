package org.testapp.figure.model;

import java.math.BigDecimal;

public class Rectangle extends PolygonFigure {

	Rectangle(String id, BigDecimal base, BigDecimal height) {
		super(id, FigureType.RECTANGLE, base, height);
	}

	@Override
	public BigDecimal getSurface() {
		return getRectangularArea();
	}

}
