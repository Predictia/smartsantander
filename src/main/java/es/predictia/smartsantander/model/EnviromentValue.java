package es.predictia.smartsantander.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnviromentValue extends NodeLocationValue {

	private Float temperature, light, noise;

	private static final long serialVersionUID = -3437032397256627826L;
	
}
