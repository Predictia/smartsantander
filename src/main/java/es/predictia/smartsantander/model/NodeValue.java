package es.predictia.smartsantander.model;

import java.io.Serializable;

import org.joda.time.DateTime;

public class NodeValue implements Serializable{

	private Integer nodeId;
	private Float battery;
	private DateTime date;
	
	public Integer getNodeId() {
		return nodeId;
	}
	public Float getBattery() {
		return battery;
	}
	public DateTime getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return "NodeValue [nodeId=" + nodeId + ", date=" + date + "]";
	}

	private static final long serialVersionUID = 1774322847739022698L;
	
}
