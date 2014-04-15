package es.predictia.smartsantander.model;

public class MobileValue extends NodeLocationValue {
	
	private Float temperature, altitude, CO, particles, ozone, NO2;
	
	private Integer speed, course, odometer;

	public Float getTemperature() {
		return temperature;
	}
	public Float getAltitude() {
		return altitude;
	}
	public Float getCO() {
		return CO;
	}
	public Float getParticles() {
		return particles;
	}
	public Float getOzone() {
		return ozone;
	}
	public Float getNO2() {
		return NO2;
	}
	public Integer getSpeed() {
		return speed;
	}
	public Integer getCourse() {
		return course;
	}
	public Integer getOdometer() {
		return odometer;
	}

	@Override
	public String toString() {
		return "MobileValue [CO=" + CO + ", NO2=" + NO2 + ", altitude="
				+ altitude + ", course=" + course + ", odometer=" + odometer
				+ ", ozone=" + ozone + ", particles=" + particles + ", speed="
				+ speed + ", temperature=" + temperature + ", toString()="
				+ super.toString() + "]";
	}

	private static final long serialVersionUID = 4345269492374591961L;
		
}
