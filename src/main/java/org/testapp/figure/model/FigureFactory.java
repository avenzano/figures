package org.testapp.figure.model;

import org.springframework.stereotype.Component;
import org.testapp.figure.dto.FigureData;

@Component
public class FigureFactory {
	
	public GeometricFigure createFigure(FigureType type, FigureData data) {
		GeometricFigure figure = null;
		switch (data.getType()) {
		case CIRCLE:
			figure = new Circle(data.getId(), data.getDiameter());
			break;
		case RECTANGLE:
			figure = new Rectangle(data.getId(), data.getBase(), data.getHeight());
			break;
		case TRIANGLE:
			figure = new Triangle(data.getId(), data.getBase(), data.getHeight());
			break;
		default:
			throw new IllegalArgumentException("Unexpected figure type: "+figure);
		}
		return figure;
	}

}
