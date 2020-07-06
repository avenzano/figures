package org.testapp.figure.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.testapp.figure.dto.FigureData;
import org.testapp.figure.dto.SearchData;
import org.testapp.figure.model.GeometricFigure;
import org.testapp.figure.service.FigureService;

@RestController
@RequestMapping("/figures")
public class FigureController {

	@Autowired
	private FigureService figureSrv;
	
	@GetMapping("/{id}")
	public FigureData getFigureById(@PathVariable("id") String id) {
		GeometricFigure f = figureSrv.getById(id);
		return f.asData();
	}
	
	@GetMapping
	public List<FigureData> getSearchFigures(@RequestParam Map<String, String> params) {
		SearchData searchData = new SearchData(params);
		List<FigureData> results = figureSrv.search(searchData);
		return results;
	}
	
	@PostMapping
	public FigureData saveFigure(@RequestBody FigureData data) {
		GeometricFigure saved = figureSrv.save(data);
		return saved.asData();
	}
}
