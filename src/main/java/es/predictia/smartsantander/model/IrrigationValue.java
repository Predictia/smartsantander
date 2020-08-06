package es.predictia.smartsantander.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IrrigationValue extends NodeLocationValue {

	private Float temperature, soilMoistureTension, relativeHumidity, rainfall, groundTemperature, windSpeed,
			solarRadiation, atmosphericPressure, radiationPAR;
	
	private String windDirection;
	
	private static final long serialVersionUID = -7580273769699537066L;
	
}
