package es.predictia.smartsantander.model;

import java.io.Serializable;

public class Node implements Serializable {

	private Integer nodeId;
    private String type;
    private Float longitude, latitude;
	
    public Node() {
    	super();
    }
	
    public Node(Integer nodeId) {
		super();
		this.nodeId = nodeId;
	}
	public Integer getNodeId() {
		return nodeId;
	}
	public String getType() {
		return type;
	}
	public Float getLongitude() {
		return longitude;
	}
	public Float getLatitude() {
		return latitude;
	}

	private static final long serialVersionUID = -8393049669636644551L;
    
}
