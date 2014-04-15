package es.predictia.smartsantander.model;

public class NodeLocationValue extends NodeValue {

	private String type;
	
	private Float longitude, latitude;

	public Float getLongitude() {
		return longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "NodeLocationValue [latitude=" + latitude + ", longitude="
				+ longitude + ", toString()=" + super.toString() + "]";
	}

	private static final long serialVersionUID = 3765924035904293965L;

}
