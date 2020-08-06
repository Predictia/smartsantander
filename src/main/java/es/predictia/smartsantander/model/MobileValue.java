package es.predictia.smartsantander.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MobileValue extends NodeLocationValue {
	
	private Float temperature, altitude, CO, particles, ozone, NO2;
	
	private Integer speed, course, odometer;

	private static final long serialVersionUID = 4345269492374591961L;
		
}
