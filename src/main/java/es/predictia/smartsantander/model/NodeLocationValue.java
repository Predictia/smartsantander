package es.predictia.smartsantander.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NodeLocationValue extends NodeValue {

	private NodeValue nodeValue;
	
	private String type;
	
	private Float longitude, latitude;

	private static final long serialVersionUID = 3765924035904293965L;

}
