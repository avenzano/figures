package org.testapp.figure.model;

import java.math.BigDecimal;

public class Triangle extends PolygonFigure {

	Triangle(String id, BigDecimal base, BigDecimal height) {
		super(id, FigureType.TRIANGLE, base, height);
	}

	@Override
	public BigDecimal getSurface() {
		return getRectangularArea().divide(new BigDecimal(2));
	}

}
