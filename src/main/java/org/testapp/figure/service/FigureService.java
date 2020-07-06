package org.testapp.figure.service;

import java.util.List;

import org.testapp.figure.dto.FigureData;
import org.testapp.figure.dto.SearchData;
import org.testapp.figure.model.GeometricFigure;

public interface FigureService {

	GeometricFigure getById(String id);

	GeometricFigure save(FigureData body);

	List<FigureData> search(SearchData searchData);
	
}
