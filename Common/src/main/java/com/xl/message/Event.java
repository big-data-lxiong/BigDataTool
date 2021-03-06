/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.xl.message;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Event extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8493236787301313240L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Event\",\"namespace\":\"com.xl.message\",\"fields\":[{\"name\":\"snapshot_id\",\"type\":\"long\"},{\"name\":\"timestamp\",\"type\":\"long\"},{\"name\":\"request_headers\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"uri\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"response_headers\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"channel_action\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"channel_type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"http_method\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Event> ENCODER =
      new BinaryMessageEncoder<Event>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Event> DECODER =
      new BinaryMessageDecoder<Event>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Event> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Event> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Event>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Event to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Event from a ByteBuffer. */
  public static Event fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long snapshot_id;
  @Deprecated public long timestamp;
  @Deprecated public java.lang.CharSequence request_headers;
  @Deprecated public java.lang.CharSequence uri;
  @Deprecated public java.lang.CharSequence response_headers;
  @Deprecated public java.lang.CharSequence channel_action;
  @Deprecated public java.lang.CharSequence channel_type;
  @Deprecated public java.lang.CharSequence http_method;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Event() {}

  /**
   * All-args constructor.
   * @param snapshot_id The new value for snapshot_id
   * @param timestamp The new value for timestamp
   * @param request_headers The new value for request_headers
   * @param uri The new value for uri
   * @param response_headers The new value for response_headers
   * @param channel_action The new value for channel_action
   * @param channel_type The new value for channel_type
   * @param http_method The new value for http_method
   */
  public Event(java.lang.Long snapshot_id, java.lang.Long timestamp, java.lang.CharSequence request_headers, java.lang.CharSequence uri, java.lang.CharSequence response_headers, java.lang.CharSequence channel_action, java.lang.CharSequence channel_type, java.lang.CharSequence http_method) {
    this.snapshot_id = snapshot_id;
    this.timestamp = timestamp;
    this.request_headers = request_headers;
    this.uri = uri;
    this.response_headers = response_headers;
    this.channel_action = channel_action;
    this.channel_type = channel_type;
    this.http_method = http_method;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return snapshot_id;
    case 1: return timestamp;
    case 2: return request_headers;
    case 3: return uri;
    case 4: return response_headers;
    case 5: return channel_action;
    case 6: return channel_type;
    case 7: return http_method;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: snapshot_id = (java.lang.Long)value$; break;
    case 1: timestamp = (java.lang.Long)value$; break;
    case 2: request_headers = (java.lang.CharSequence)value$; break;
    case 3: uri = (java.lang.CharSequence)value$; break;
    case 4: response_headers = (java.lang.CharSequence)value$; break;
    case 5: channel_action = (java.lang.CharSequence)value$; break;
    case 6: channel_type = (java.lang.CharSequence)value$; break;
    case 7: http_method = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'snapshot_id' field.
   * @return The value of the 'snapshot_id' field.
   */
  public java.lang.Long getSnapshotId() {
    return snapshot_id;
  }

  /**
   * Sets the value of the 'snapshot_id' field.
   * @param value the value to set.
   */
  public void setSnapshotId(java.lang.Long value) {
    this.snapshot_id = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
  }

  /**
   * Gets the value of the 'request_headers' field.
   * @return The value of the 'request_headers' field.
   */
  public java.lang.CharSequence getRequestHeaders() {
    return request_headers;
  }

  /**
   * Sets the value of the 'request_headers' field.
   * @param value the value to set.
   */
  public void setRequestHeaders(java.lang.CharSequence value) {
    this.request_headers = value;
  }

  /**
   * Gets the value of the 'uri' field.
   * @return The value of the 'uri' field.
   */
  public java.lang.CharSequence getUri() {
    return uri;
  }

  /**
   * Sets the value of the 'uri' field.
   * @param value the value to set.
   */
  public void setUri(java.lang.CharSequence value) {
    this.uri = value;
  }

  /**
   * Gets the value of the 'response_headers' field.
   * @return The value of the 'response_headers' field.
   */
  public java.lang.CharSequence getResponseHeaders() {
    return response_headers;
  }

  /**
   * Sets the value of the 'response_headers' field.
   * @param value the value to set.
   */
  public void setResponseHeaders(java.lang.CharSequence value) {
    this.response_headers = value;
  }

  /**
   * Gets the value of the 'channel_action' field.
   * @return The value of the 'channel_action' field.
   */
  public java.lang.CharSequence getChannelAction() {
    return channel_action;
  }

  /**
   * Sets the value of the 'channel_action' field.
   * @param value the value to set.
   */
  public void setChannelAction(java.lang.CharSequence value) {
    this.channel_action = value;
  }

  /**
   * Gets the value of the 'channel_type' field.
   * @return The value of the 'channel_type' field.
   */
  public java.lang.CharSequence getChannelType() {
    return channel_type;
  }

  /**
   * Sets the value of the 'channel_type' field.
   * @param value the value to set.
   */
  public void setChannelType(java.lang.CharSequence value) {
    this.channel_type = value;
  }

  /**
   * Gets the value of the 'http_method' field.
   * @return The value of the 'http_method' field.
   */
  public java.lang.CharSequence getHttpMethod() {
    return http_method;
  }

  /**
   * Sets the value of the 'http_method' field.
   * @param value the value to set.
   */
  public void setHttpMethod(java.lang.CharSequence value) {
    this.http_method = value;
  }

  /**
   * Creates a new Event RecordBuilder.
   * @return A new Event RecordBuilder
   */
  public static com.xl.message.Event.Builder newBuilder() {
    return new com.xl.message.Event.Builder();
  }

  /**
   * Creates a new Event RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Event RecordBuilder
   */
  public static com.xl.message.Event.Builder newBuilder(com.xl.message.Event.Builder other) {
    return new com.xl.message.Event.Builder(other);
  }

  /**
   * Creates a new Event RecordBuilder by copying an existing Event instance.
   * @param other The existing instance to copy.
   * @return A new Event RecordBuilder
   */
  public static com.xl.message.Event.Builder newBuilder(com.xl.message.Event other) {
    return new com.xl.message.Event.Builder(other);
  }

  /**
   * RecordBuilder for Event instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Event>
    implements org.apache.avro.data.RecordBuilder<Event> {

    private long snapshot_id;
    private long timestamp;
    private java.lang.CharSequence request_headers;
    private java.lang.CharSequence uri;
    private java.lang.CharSequence response_headers;
    private java.lang.CharSequence channel_action;
    private java.lang.CharSequence channel_type;
    private java.lang.CharSequence http_method;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.xl.message.Event.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.snapshot_id)) {
        this.snapshot_id = data().deepCopy(fields()[0].schema(), other.snapshot_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[1].schema(), other.timestamp);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.request_headers)) {
        this.request_headers = data().deepCopy(fields()[2].schema(), other.request_headers);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.uri)) {
        this.uri = data().deepCopy(fields()[3].schema(), other.uri);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.response_headers)) {
        this.response_headers = data().deepCopy(fields()[4].schema(), other.response_headers);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.channel_action)) {
        this.channel_action = data().deepCopy(fields()[5].schema(), other.channel_action);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.channel_type)) {
        this.channel_type = data().deepCopy(fields()[6].schema(), other.channel_type);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.http_method)) {
        this.http_method = data().deepCopy(fields()[7].schema(), other.http_method);
        fieldSetFlags()[7] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Event instance
     * @param other The existing instance to copy.
     */
    private Builder(com.xl.message.Event other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.snapshot_id)) {
        this.snapshot_id = data().deepCopy(fields()[0].schema(), other.snapshot_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[1].schema(), other.timestamp);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.request_headers)) {
        this.request_headers = data().deepCopy(fields()[2].schema(), other.request_headers);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.uri)) {
        this.uri = data().deepCopy(fields()[3].schema(), other.uri);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.response_headers)) {
        this.response_headers = data().deepCopy(fields()[4].schema(), other.response_headers);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.channel_action)) {
        this.channel_action = data().deepCopy(fields()[5].schema(), other.channel_action);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.channel_type)) {
        this.channel_type = data().deepCopy(fields()[6].schema(), other.channel_type);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.http_method)) {
        this.http_method = data().deepCopy(fields()[7].schema(), other.http_method);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'snapshot_id' field.
      * @return The value.
      */
    public java.lang.Long getSnapshotId() {
      return snapshot_id;
    }

    /**
      * Sets the value of the 'snapshot_id' field.
      * @param value The value of 'snapshot_id'.
      * @return This builder.
      */
    public com.xl.message.Event.Builder setSnapshotId(long value) {
      validate(fields()[0], value);
      this.snapshot_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'snapshot_id' field has been set.
      * @return True if the 'snapshot_id' field has been set, false otherwise.
      */
    public boolean hasSnapshotId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'snapshot_id' field.
      * @return This builder.
      */
    public com.xl.message.Event.Builder clearSnapshotId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public java.lang.Long getTimestamp() {
      return timestamp;
    }

    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public com.xl.message.Event.Builder setTimestamp(long value) {
      validate(fields()[1], value);
      this.timestamp = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public com.xl.message.Event.Builder clearTimestamp() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'request_headers' field.
      * @return The value.
      */
    public java.lang.CharSequence getRequestHeaders() {
      return request_headers;
    }

    /**
      * Sets the value of the 'request_headers' field.
      * @param value The value of 'request_headers'.
      * @return This builder.
      */
    public com.xl.message.Event.Builder setRequestHeaders(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.request_headers = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'request_headers' field has been set.
      * @return True if the 'request_headers' field has been set, false otherwise.
      */
    public boolean hasRequestHeaders() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'request_headers' field.
      * @return This builder.
      */
    public com.xl.message.Event.Builder clearRequestHeaders() {
      request_headers = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'uri' field.
      * @return The value.
      */
    public java.lang.CharSequence getUri() {
      return uri;
    }

    /**
      * Sets the value of the 'uri' field.
      * @param value The value of 'uri'.
      * @return This builder.
      */
    public com.xl.message.Event.Builder setUri(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.uri = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'uri' field has been set.
      * @return True if the 'uri' field has been set, false otherwise.
      */
    public boolean hasUri() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'uri' field.
      * @return This builder.
      */
    public com.xl.message.Event.Builder clearUri() {
      uri = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'response_headers' field.
      * @return The value.
      */
    public java.lang.CharSequence getResponseHeaders() {
      return response_headers;
    }

    /**
      * Sets the value of the 'response_headers' field.
      * @param value The value of 'response_headers'.
      * @return This builder.
      */
    public com.xl.message.Event.Builder setResponseHeaders(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.response_headers = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'response_headers' field has been set.
      * @return True if the 'response_headers' field has been set, false otherwise.
      */
    public boolean hasResponseHeaders() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'response_headers' field.
      * @return This builder.
      */
    public com.xl.message.Event.Builder clearResponseHeaders() {
      response_headers = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'channel_action' field.
      * @return The value.
      */
    public java.lang.CharSequence getChannelAction() {
      return channel_action;
    }

    /**
      * Sets the value of the 'channel_action' field.
      * @param value The value of 'channel_action'.
      * @return This builder.
      */
    public com.xl.message.Event.Builder setChannelAction(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.channel_action = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'channel_action' field has been set.
      * @return True if the 'channel_action' field has been set, false otherwise.
      */
    public boolean hasChannelAction() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'channel_action' field.
      * @return This builder.
      */
    public com.xl.message.Event.Builder clearChannelAction() {
      channel_action = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'channel_type' field.
      * @return The value.
      */
    public java.lang.CharSequence getChannelType() {
      return channel_type;
    }

    /**
      * Sets the value of the 'channel_type' field.
      * @param value The value of 'channel_type'.
      * @return This builder.
      */
    public com.xl.message.Event.Builder setChannelType(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.channel_type = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'channel_type' field has been set.
      * @return True if the 'channel_type' field has been set, false otherwise.
      */
    public boolean hasChannelType() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'channel_type' field.
      * @return This builder.
      */
    public com.xl.message.Event.Builder clearChannelType() {
      channel_type = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'http_method' field.
      * @return The value.
      */
    public java.lang.CharSequence getHttpMethod() {
      return http_method;
    }

    /**
      * Sets the value of the 'http_method' field.
      * @param value The value of 'http_method'.
      * @return This builder.
      */
    public com.xl.message.Event.Builder setHttpMethod(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.http_method = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'http_method' field has been set.
      * @return True if the 'http_method' field has been set, false otherwise.
      */
    public boolean hasHttpMethod() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'http_method' field.
      * @return This builder.
      */
    public com.xl.message.Event.Builder clearHttpMethod() {
      http_method = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Event build() {
      try {
        Event record = new Event();
        record.snapshot_id = fieldSetFlags()[0] ? this.snapshot_id : (java.lang.Long) defaultValue(fields()[0]);
        record.timestamp = fieldSetFlags()[1] ? this.timestamp : (java.lang.Long) defaultValue(fields()[1]);
        record.request_headers = fieldSetFlags()[2] ? this.request_headers : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.uri = fieldSetFlags()[3] ? this.uri : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.response_headers = fieldSetFlags()[4] ? this.response_headers : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.channel_action = fieldSetFlags()[5] ? this.channel_action : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.channel_type = fieldSetFlags()[6] ? this.channel_type : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.http_method = fieldSetFlags()[7] ? this.http_method : (java.lang.CharSequence) defaultValue(fields()[7]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Event>
    WRITER$ = (org.apache.avro.io.DatumWriter<Event>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Event>
    READER$ = (org.apache.avro.io.DatumReader<Event>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
