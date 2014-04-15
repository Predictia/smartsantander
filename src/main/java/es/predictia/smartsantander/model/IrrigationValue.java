package es.predictia.smartsantander.model;

public class IrrigationValue extends NodeLocationValue {

	private Float temperature, soilMoistureTension, relativeHumidity, rainfall, groundTemperature, windSpeed, solarRadiation, atmosphericPressure, radiationPAR;
	private String windDirection;
	
	public Float getTemperature() {
		return temperature;
	}
	public Float getSoilMoistureTension() {
		return soilMoistureTension;
	}
	public Float getRelativeHumidity() {
		return relativeHumidity;
	}
	public Float getRainfall() {
		return rainfall;
	}
	public Float getGroundTemperature() {
		return groundTemperature;
	}
	public Float getWindSpeed() {
		return windSpeed;
	}
	public Float getSolarRadiation() {
		return solarRadiation;
	}
	public Float getAtmosphericPressure() {
		return atmosphericPressure;
	}
	public Float getRadiationPAR() {
		return radiationPAR;
	}
	public String getWindDirection() {
		return windDirection;
	}

	@Override
	public String toString() {
		return "IrrigationValue [atmosphericPressure=" + atmosphericPressure
				+ ", groundTemperature=" + groundTemperature + ", rainfall="
				+ rainfall + ", relativeHumidity=" + relativeHumidity
				+ ", soilMoistureTension=" + soilMoistureTension
				+ ", solarRadiation=" + solarRadiation + ", temperature="
				+ temperature + ", windDirection=" + windDirection
				+ ", windSpeed=" + windSpeed + ", toString()="
				+ super.toString() + "]";
	}

	private static final long serialVersionUID = -7580273769699537066L;
	
}
