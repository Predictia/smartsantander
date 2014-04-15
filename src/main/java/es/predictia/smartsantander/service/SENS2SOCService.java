package es.predictia.smartsantander.service;

import java.io.IOException;
import java.util.List;

import org.joda.time.Interval;

import es.predictia.smartsantander.model.EnviromentValue;
import es.predictia.smartsantander.model.IrrigationValue;
import es.predictia.smartsantander.model.MobileValue;
import es.predictia.smartsantander.model.Node;
import es.predictia.smartsantander.model.NodeType;
import es.predictia.smartsantander.model.TemperatureValue;

/** Informacion de los sensores de SmartSantander
 * @author Max
 * @see <a href="http://smartsantander.eu/wiki/index.php/Data/SEN2SOCSmartSantanderIntegration">Service Documentation</a>
 *
 */
public interface SENS2SOCService {

	public List<Node> getNodes() throws IOException;
	public List<Node> getNodes(NodeType... nodeTypes) throws IOException;

	public List<EnviromentValue> getLastEnviromentValues() throws IOException;
	public List<IrrigationValue> getLastIrrigationValues() throws IOException;
	public List<MobileValue> getLastMobileValues() throws IOException;
	
	public List<TemperatureValue> getLastTemperatureValues(Node node) throws IOException;
	public List<TemperatureValue> getTemperatureValues(Node node, Interval interval) throws IOException;
	
}
