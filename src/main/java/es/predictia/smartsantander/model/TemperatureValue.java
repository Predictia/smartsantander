package es.predictia.smartsantander.model;

import com.google.common.base.Predicate;

public class TemperatureValue extends NodeValue{

	private Float temperature;
	
	private String location;

	/**
	 * @return Temperatura o {@link Float#NaN} en caso ausente
	 */
	public Float getTemperature() {
		if(temperature == null){
			return Float.NaN;
		}else{
			return temperature;
		}
	}
	public String getLocation() {
		return location;
	}

	/**
	 * Predicado que sera true cuando la fecha (
	 * {@link TemperatureValue#getDate()}) y la temperatura (
	 * {@link TemperatureValue#getTemperature()}) tienen valor
	 */
	public static final Predicate<TemperatureValue> IS_VALID = new Predicate<TemperatureValue>() {					
		@Override
		public boolean apply(TemperatureValue input) {
			if(input.getDate()==null) return false;
			if(Float.isNaN(input.getTemperature())) return false;
			return true;
		}
	};

	@Override
	public String toString() {
		return "TemperatureValue [location=" + location + ", temperature="
				+ temperature + ", toString()=" + super.toString() + "]";
	}

	private static final long serialVersionUID = -617598610586147830L;
	
}