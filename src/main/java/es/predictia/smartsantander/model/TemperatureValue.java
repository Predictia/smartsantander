package es.predictia.smartsantander.model;


import java.util.function.Predicate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemperatureValue extends NodeValue {

	private Float temperature;
	
	private String location;

	/**
	 * Predicado que sera true cuando la fecha (
	 * {@link TemperatureValue#getDate()}) y la temperatura (
	 * {@link TemperatureValue#getTemperature()}) tienen valor
	 */
	public static final Predicate<TemperatureValue> IS_VALID = input -> {
		if(input.getDate()==null) return false;
		if(Float.isNaN(input.getTemperature())) return false;
		return true;
	};

	private static final long serialVersionUID = -617598610586147830L;
	
}