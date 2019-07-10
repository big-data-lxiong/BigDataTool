package com.xl.message;

import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class EventSerializer implements Serializer<Event> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@Override
	public byte[] serialize(String topic, Event data) {
		if(data == null) {
			return null;
		}
		DatumWriter<Event> writer = new SpecificDatumWriter<>(data.getSchema());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			JsonEncoder encoder = EncoderFactory.get().jsonEncoder(data.getSchema(), out);
			writer.write(data, encoder);
			encoder.flush();
		}catch (Exception e) {
			throw new SerializationException(e.getMessage());
		}
		return out.toByteArray();
	}

	@Override
	public void close() {

	}
}
