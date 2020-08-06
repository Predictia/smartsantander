package es.predictia.smartsantander.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Node implements Serializable {

	private Integer nodeId;
    private String type;
    private Float longitude, latitude;
	
	private static final long serialVersionUID = -8393049669636644551L;
    
}
