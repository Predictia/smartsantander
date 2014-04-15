package es.predictia.smartsantander.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import es.predictia.smartsantander.model.EnviromentValue;
import es.predictia.smartsantander.model.IrrigationValue;
import es.predictia.smartsantander.model.MobileValue;
import es.predictia.smartsantander.model.Node;
import es.predictia.smartsantander.model.NodeType;
import es.predictia.smartsantander.model.TemperatureValue;
import es.predictia.util.URLs;
import es.predictia.util.date.TimeZone;

public class SENS2SOCServiceImpl implements SENS2SOCService {

	private static final String BASE_URL = "http://data.smartsantander.eu/SENS2SOC";
	private static final String NODES_URL = BASE_URL + "/GetNodes";
	private static final String LAST_VALUES_BASE_URL = BASE_URL + "/GetLastValuesbyNodeID/";
	private static final String HISTORIC_BASE_URL = BASE_URL + "/GetHistoricbyNodeID/";
	private static final String LAST_VALUES_ENVIROMENT_URL = BASE_URL + "/GetEnvMonitoringLastValues";
	private static final String LAST_VALUES_IRRIGATION_URL = BASE_URL + "/GetIrrigationLastValues";
	private static final String LAST_VALUES_MOBILE_URL = BASE_URL + "/GetMobileSensingLastValues";
	
	@Override
	public List<Node> getNodes() throws IOException {
		Type nodesType = new TypeToken<List<Node>>(){}.getType();
		List<Node> nodes = GSON.fromJson(getUrlContent(NODES_URL), nodesType);
		if(nodes == null){
			return Collections.emptyList();
		}else{
			return ImmutableList.copyOf(nodes);
		}
	}
	
	@Override
	public List<EnviromentValue> getLastEnviromentValues() throws IOException {
		Type nodesType = new TypeToken<List<EnviromentValue>>(){}.getType();
		List<EnviromentValue> values = GSON.fromJson(getUrlContent(LAST_VALUES_ENVIROMENT_URL), nodesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return ImmutableList.copyOf(values);
		}
	}

	@Override
	public List<IrrigationValue> getLastIrrigationValues() throws IOException {
		Type nodesType = new TypeToken<List<IrrigationValue>>(){}.getType();
		List<IrrigationValue> values = GSON.fromJson(getUrlContent(LAST_VALUES_IRRIGATION_URL), nodesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return ImmutableList.copyOf(values);
		}
	}

	@Override
	public List<MobileValue> getLastMobileValues() throws IOException {
		Type nodesType = new TypeToken<List<MobileValue>>(){}.getType();
		List<MobileValue> values = GSON.fromJson(getUrlContent(LAST_VALUES_MOBILE_URL), nodesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return ImmutableList.copyOf(values);
		}
	}
	
	@Override
	public List<Node> getNodes(final NodeType... nodeTypes) throws IOException {
		if(nodeTypes == null){
			return Collections.emptyList();
		}else{
			return ImmutableList.copyOf(Iterables.filter(getNodes(), new Predicate<Node>() {
				@Override
				public boolean apply(Node arg0) {
					for(NodeType nodeType : nodeTypes){
						if(nodeType.name().equalsIgnoreCase(arg0.getType())){
							return true;
						}
					}
					return false; 
				}
			}));
		}
	}

	@Override
	public List<TemperatureValue> getLastTemperatureValues(Node node) throws IOException {
		Type valuesType = new TypeToken<List<TemperatureValue>>(){}.getType();
		List<TemperatureValue> values = GSON.fromJson(getUrlContent(LAST_VALUES_BASE_URL + node.getNodeId()), valuesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return ImmutableList.copyOf(Iterables.filter(values, TemperatureValue.IS_VALID));
		}
	}
	
	@Override
	public List<TemperatureValue> getTemperatureValues(Node node, Interval interval) throws IOException {
		Type valuesType = new TypeToken<List<TemperatureValue>>(){}.getType();
		List<TemperatureValue> values = GSON.fromJson(
			getUrlContent(
				HISTORIC_BASE_URL + node.getNodeId() + "/" + FMT.print(interval.getStart()) + "/" + FMT.print(interval.getEnd())
			), 
			valuesType
		);
		if(values == null){
			return Collections.emptyList();
		}else{
			return ImmutableList.copyOf(Iterables.filter(values, TemperatureValue.IS_VALID));
		}	
	}
	
	private static String getUrlContent(String urlStr) throws IOException {
		try {
			URL url = URLs.getUrl(urlStr);
			LOGGER.debug("Retrieving : " + url);
			return URLs.getUrlContent(url, 60000);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private static final Gson GSON = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeAdapter()).create();
	
	private static class DateTimeAdapter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime>{	
		
		@Override 
		public JsonElement serialize(DateTime arg0, Type arg1, JsonSerializationContext arg2) {
			return new JsonPrimitive(FMT.print(arg0));
		}
		
		@Override
		public DateTime deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
			try{
				return FMT.parseDateTime(jsonelement.getAsString());
			}catch(IllegalArgumentException e){
				return null;
			}
		}
	}

	private static final DateTimeFormatter FMT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZone(TimeZone.getTimeZone("Madrid"));
	private final static Logger LOGGER = LoggerFactory.getLogger(SENS2SOCServiceImpl.class);

}