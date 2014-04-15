package es.predictia.smartsantander.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.predictia.smartsantander.model.EnviromentValue;
import es.predictia.smartsantander.model.IrrigationValue;
import es.predictia.smartsantander.model.MobileValue;
import es.predictia.smartsantander.model.Node;
import es.predictia.smartsantander.model.TemperatureValue;

public class SENS2SOCServiceTest{

	private SENS2SOCService service;
	
	@Before
	public void init() throws Exception{
		this.service = new SENS2SOCServiceImpl();		
	}
	
	@Test
	public void testNodes() throws Exception{
		List<Node> nodes = service.getNodes();
		Assert.assertFalse(nodes.isEmpty());
	}

	@Test
	public void testLastEnviromentValues() throws Exception{
		List<EnviromentValue> values = service.getLastEnviromentValues();
		Assert.assertFalse(values.isEmpty());
		Assert.assertNotNull(values.get(0).getDate());
		Assert.assertNotNull(values.get(0).getTemperature());
	}

	@Test
	public void testLastIrrigationValues() throws Exception{
		List<IrrigationValue> values = service.getLastIrrigationValues();
		Assert.assertFalse(values.isEmpty());
		Assert.assertNotNull(values.get(0).getDate());
	}
	
	@Test
	public void testLastMobileValues() throws Exception{
		List<MobileValue> values = service.getLastMobileValues();
		Assert.assertFalse(values.isEmpty());
		Assert.assertNotNull(values.get(0).getDate());
	}
	
	@Test
	public void testLastValue() throws Exception{
		List<TemperatureValue> nodes = service.getLastTemperatureValues(new Node(3));
		Assert.assertFalse(nodes.isEmpty());
	}
	
	@Test
	public void testValue() throws Exception{
		List<TemperatureValue> lastValues = service.getLastTemperatureValues(new Node(3));
		DateTime lastValue = lastValues.get(0).getDate();
		List<TemperatureValue> nodes = service.getTemperatureValues(new Node(3), new Interval(lastValue.minusDays(1), lastValue));
		Assert.assertFalse(nodes.isEmpty());
	}
	
}
