package es.predictia.smartsantander.model;

public class EnviromentValue extends NodeLocationValue {

	private Float temperature, light, noise;
	
	public Float getTemperature() {
		return temperature;
	}
	public Float getLight() {
		return light;
	}
	public Float getNoise() {
		return noise;
	}
	
	@Override
	public String toString() {
		return "EnviromentValue [temperature=" + temperature + ", light="
				+ light + ", noise=" + noise + ", toString()="
				+ super.toString() + "]";
	}

	private static final long serialVersionUID = -3437032397256627826L;
	
}
