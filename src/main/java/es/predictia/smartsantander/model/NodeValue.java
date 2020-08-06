package es.predictia.smartsantander.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NodeValue implements Serializable {

	private Integer nodeId;
	private Float battery;
	private LocalDateTime date;
	
	private static final long serialVersionUID = 1774322847739022698L;
	
}
