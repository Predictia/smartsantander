package es.predictia.smartsantander.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
			return Collections.unmodifiableList(nodes);
		}
	}
	
	@Override
	public List<EnviromentValue> getLastEnviromentValues() throws IOException {
		Type nodesType = new TypeToken<List<EnviromentValue>>(){}.getType();
		List<EnviromentValue> values = GSON.fromJson(getUrlContent(LAST_VALUES_ENVIROMENT_URL), nodesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return Collections.unmodifiableList(values);
		}
	}

	@Override
	public List<IrrigationValue> getLastIrrigationValues() throws IOException {
		Type nodesType = new TypeToken<List<IrrigationValue>>(){}.getType();
		List<IrrigationValue> values = GSON.fromJson(getUrlContent(LAST_VALUES_IRRIGATION_URL), nodesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return Collections.unmodifiableList(values);
		}
	}

	@Override
	public List<MobileValue> getLastMobileValues() throws IOException {
		Type nodesType = new TypeToken<List<MobileValue>>(){}.getType();
		List<MobileValue> values = GSON.fromJson(getUrlContent(LAST_VALUES_MOBILE_URL), nodesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return Collections.unmodifiableList(values);
		}
	}
	
	@Override
	public List<Node> getNodes(final NodeType... nodeTypes) throws IOException {
		if(nodeTypes == null){
			return Collections.emptyList();
		}else{
			return getNodes().stream().filter(arg0 -> {
				for(NodeType nodeType : nodeTypes){
					if(nodeType.name().equalsIgnoreCase(arg0.getType())){
						return true;
					}
				}
				return false;
			})
			.collect(Collectors.toList());
		}
	}

	@Override
	public List<TemperatureValue> getLastTemperatureValues(Node node) throws IOException {
		Type valuesType = new TypeToken<List<TemperatureValue>>(){}.getType();
		List<TemperatureValue> values = GSON.fromJson(getUrlContent(LAST_VALUES_BASE_URL + node.getNodeId()), valuesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return values.stream()
				.filter(TemperatureValue.IS_VALID)
				.collect(Collectors.toList());
		}
	}
	
	@Override
	public List<TemperatureValue> getTemperatureValues(Node node, LocalDateTime start, LocalDateTime end) throws IOException {
		Type valuesType = new TypeToken<List<TemperatureValue>>(){}.getType();
		var startStr = URLEncoder.encode(FMT.format(start), StandardCharsets.UTF_8);
		var endStr = URLEncoder.encode(FMT.format(end), StandardCharsets.UTF_8);
		var urlStr = String.format("%s%s/%s/%s", HISTORIC_BASE_URL, node.getNodeId(), startStr, endStr);
		List<TemperatureValue> values = GSON.fromJson(getUrlContent(urlStr), valuesType);
		if(values == null){
			return Collections.emptyList();
		}else{
			return values.stream()
				.filter(TemperatureValue.IS_VALID)
				.collect(Collectors.toList());
		}	
	}
	
	private static String getUrlContent(String urlStr) throws IOException {
		log.debug("Fetching contents from {}", urlStr);
		var url = new URL(urlStr);
		try (var scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8)) {
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}
	}

	private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new DateTimeAdapter()).create();
	
	private static class DateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime>{	
		
		@Override 
		public JsonElement serialize(LocalDateTime arg0, Type arg1, JsonSerializationContext arg2) {
			return new JsonPrimitive(FMT.format(arg0));
		}
		
		@Override
		public LocalDateTime deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
			try{
				return LocalDateTime.parse(jsonelement.getAsString(), FMT);
			}catch(IllegalArgumentException e){
				return null;
			}
		}
	}

	private static final DateTimeFormatter FMT = DateTimeFormatter
		.ofPattern("yyyy-MM-dd HH:mm:ss")
		.withZone(ZoneId.of("Europe/Madrid"));

}