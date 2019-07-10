package com.xl.message;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

public class EventDeserializer implements Deserializer<Event> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@Override
	public Event deserialize(String topic, byte[] data) {
		if(data == null) {
			return null;
		}
		Event event = new Event();
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		DatumReader<Event> userDatumReader = new SpecificDatumReader<>(event.getSchema());
		JsonDecoder decoder = null;
		try {
			decoder = DecoderFactory.get().jsonDecoder(event.getSchema(), in);
			event = userDatumReader.read(null, decoder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Event(event.getSnapshotId(), event.getTimestamp(), event.getRequestHeaders(), event.getUri(), event.getResponseHeaders(), event.getChannelAction(),event.getChannelType(), event.getHttpMethod());
	}

	@Override
	public void close() {

	}
}
