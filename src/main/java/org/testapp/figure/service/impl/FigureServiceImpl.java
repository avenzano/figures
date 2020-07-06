package org.testapp.figure.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testapp.figure.dto.FigureData;
import org.testapp.figure.dto.SearchData;
import org.testapp.figure.model.FigureFactory;
import org.testapp.figure.model.GeometricFigure;
import org.testapp.figure.service.FigureService;

@Service
public class FigureServiceImpl implements FigureService {

	@Autowired
	private FigureFactory figureFactory;
	
	private Map<String, GeometricFigure> storage = new HashMap<>();
	
	@Override
	public GeometricFigure getById(String id) {
		if ( ! storage.containsKey(id) ) {
			throw new IllegalArgumentException("There is no Figure stored with the id "+id);
		}
		return storage.get(id);
	}

	@Override
	public GeometricFigure save(FigureData data) {
		data.setId(UUID.randomUUID().toString());
		GeometricFigure f = figureFactory.createFigure(data.getType(), data);
		storage.put(f.getId(), f);
		return f;
	}

	@Override
	public List<FigureData> search(SearchData searchData) {
		return storage.values().stream()
			.filter(searchData.asPredicate())
			.map(f -> f.asData())
			.collect(Collectors.toList());
	}

}
