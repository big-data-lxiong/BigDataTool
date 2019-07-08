package com.xl;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ESMetrics {

	private RestClient restClient;

	/**
	 * Singleton instance of ESMetrics
	 */
	private static ESMetrics INSTANCE = null;

	public static void init() {
		if (INSTANCE != null) {
			return;
		}
		INSTANCE = new ESMetrics();

		HttpHost httpHost = null;
		String appKey = "";
		String appSecret = "";

		RestClientBuilder builder = RestClient.builder(httpHost);
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
			new UsernamePasswordCredentials(appKey, appSecret));
		builder = builder.setHttpClientConfigCallback(httpClientBuilder ->
			httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

		INSTANCE.restClient = builder.build();
	}

	protected void sendMetric(String path, Map<String, Object> metric) {
		final String type = "_doc";
		final String id = String.valueOf(System.currentTimeMillis())
			+ String.format("%04d", random.nextInt(10000));

		Gson gson = new Gson();
		try {
			if (restClient != null)
				restClient.performRequest("PUT", "/" + path + "/" + type + "/" + id, new HashMap<>(),
					new NStringEntity(gson.toJson(metric), ContentType.APPLICATION_JSON));
		} catch (IOException e) {
			logger.warn(e.toString(), e);
		}
	}

	@Override
	protected String createPathIfNecessary(Date date) {
		final String index;
		if (IS_PREFIX)
			index = INDEX_NAME + sdf0.format(date);
		else
			index = INDEX_NAME;

		try {
			if (restClient != null)
				restClient.performRequest("GET", "/" + index);
		} catch (ResponseException e) {
			// create index if not found
			try {
				createIndex(index);
			} catch (IOException e1) {
				logger.error(e1.toString());
			}
		} catch (IOException e) {
			logger.warn(e.toString());
		}

		return index;
	}

	/**
	 * Create index with dynamic template, map string to keyword for aggregation
	 *
	 * @param index
	 * @throws IOException
	 */
	private void createIndex(String index) throws IOException {
		if (restClient != null) {
			restClient.performRequest("PUT", "/" + index, new HashMap<>(),
				new NStringEntity("{\n" +
					" \"settings\": {\n" +
					"   \"number_of_replicas\": 2\n" +
					" },\n" +
					"  \"mappings\": {\n" +
					"    \"_doc\": {\n" +
					"      \"dynamic_templates\": [\n" +
					"        {\n" +
					"          \"strings_as_keywords\": {\n" +
					"            \"match_mapping_type\": \"string\",\n" +
					"            \"mapping\": {\n" +
					"              \"type\": \"keyword\"\n" +
					"            }\n" +
					"          }\n" +
					"        }\n" +
					"      ],\n" +
					"      \"properties\": {\n" +
					"        \"date\":   { \"type\": \"date\", \"format\": \"yyyy-MM-dd HH:mm:ss\" },\n" +
					"        \"key\":    { \"type\": \"keyword\" },\n" +
					"        \"value\":  { \"type\": \"long\" },\n" +
					"        \"host\":   { \"type\": \"keyword\" },\n" +
					"        \"dc\":     { \"type\": \"keyword\" }\n" +
					"      }\n" +
					"    }\n" +
					"  }\n" +
					"}", ContentType.APPLICATION_JSON));
		}
	}

}
