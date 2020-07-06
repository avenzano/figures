package org.testapp.figure.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Predicate;

import org.testapp.figure.model.FigureType;
import org.testapp.figure.model.GeometricFigure;

@SuppressWarnings("unused")
public class SearchData {

	private BigDecimal minArea;
	
	private BigDecimal maxArea;
	
	private BigDecimal minHeight;
	
	private BigDecimal maxHeight;

	private BigDecimal minBase;
	
	private BigDecimal maxBase;
	
	private BigDecimal minDiameter;
	
	private BigDecimal maxDiameter;
	
	private FigureType type;
	
	public SearchData(Map<String, String> params) {
		if ( params == null ) {
			throw new IllegalArgumentException("parameter map should not be null");
		}
		for (Map.Entry<String, String> pair : params.entrySet()) {
			checkAreaParameters(pair);
		}
	}
	
	private void checkAreaParameters(Map.Entry<String, String> pair) {
		if ( "type".equalsIgnoreCase(pair.getKey()) ) {
			type = getFigureType(pair.getValue());
		}
		if ( "minArea".equalsIgnoreCase(pair.getKey()) ) {
			minArea = getNumber(pair.getValue(), null);
		}
		if ( "maxArea".equalsIgnoreCase(pair.getKey()) ) {
			maxArea = getNumber(pair.getValue(), null);
		}
		if ( "minHeight".equalsIgnoreCase(pair.getKey()) ) {
			minHeight = getNumber(pair.getValue(), null);
		}
		if ( "maxHeight".equalsIgnoreCase(pair.getKey()) ) {
			maxHeight = getNumber(pair.getValue(), null);
		}
		if ( "minBase".equalsIgnoreCase(pair.getKey()) ) {
			minBase = getNumber(pair.getValue(), null);
		}
		if ( "maxBase".equalsIgnoreCase(pair.getKey()) ) {
			maxBase = getNumber(pair.getValue(), null);
		}
		if ( "minDiameter".equalsIgnoreCase(pair.getKey()) ) {
			minDiameter = getNumber(pair.getValue(), null);
		}
		if ( "maxDiameter".equalsIgnoreCase(pair.getKey()) ) {
			maxDiameter = getNumber(pair.getValue(), null);
		}
	}
	
	private FigureType getFigureType(String value) {
		for (FigureType ft : FigureType.values()) {
			if ( value.equalsIgnoreCase(ft.name())) {
				return ft;
			}
		}
		return null;
	}

	private BigDecimal getNumber(String value, BigDecimal defValue) {
		BigDecimal numValue;
		try {
			numValue = new BigDecimal(value);
		} catch (NumberFormatException e) {
			numValue = defValue;
		}
		return numValue;
	}

	public Predicate<? super GeometricFigure> asPredicate() {
		return new Predicate<GeometricFigure>() {
			@Override
			public boolean test(GeometricFigure t) {
				return 	checkType(type, t.getType()) &&
						checkRange(minArea, maxArea, t.getSurface()) &&
						checkRange(minBase, maxBase, t.getBase()) &&
						checkRange(minDiameter, maxDiameter, t.getDiameter()) &&
						checkRange(minHeight, maxHeight, t.getHeight());
			}
		};
	}

	protected boolean checkType(FigureType expected, FigureType actualType) {
		return expected == null ? true : expected == actualType;
	}

	protected boolean checkRange(BigDecimal min, BigDecimal max, BigDecimal value) {
		// no boundaries defined -> pass
		if ( min == null && max == null ) {
			return true;
		}
		// boundaries but no value -> fail
		else if ( value == null ) {
			return false;
		}
		// check full range
		else if ( min != null && max != null ) {
			return min.compareTo(value) <= 0 && max.compareTo(value) >= 0;
		}
		// else check the non-null limit
		else {
			return min != null ? min.compareTo(value) <= 0 : max.compareTo(value) >= 0;
		}
	}
	
}
