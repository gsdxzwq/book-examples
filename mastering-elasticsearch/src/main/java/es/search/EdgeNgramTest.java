/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: EdgeNgramTest.java 
 * @Prject: es-test
 * @Package: com.zhaowq.es.search 
 * @Description: TODO
 * @author: zhaowq   
 * @date: 2017年3月15日 上午10:31:57 
 * @version: V1.0   
 */
package es.search;

import java.io.IOException;

import es.commons.ElasticSearchUtils;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;

/**
 * @ClassName: EdgeNgramTest
 * @Description: TODO
 * @author: zhaowq
 * @date: 2017年3月15日 上午10:31:57
 */
public class EdgeNgramTest {
	private static final String INDICE_NAME = "edgengram2";
	private static final String INDICE_TYPE = "doc";

	public void testNgram() throws IOException {
		createIndex();
		initMapping();
		indexRecord();
		searchRecord();
	}

	private void createIndex() throws IOException {
		Client client = ElasticSearchUtils.getClientInstance();
		String source = XContentFactory.jsonBuilder().startObject().startObject("settings").startObject("analysis").startObject("analyzer")
				.startObject("my_edge_ngram_analyzer").field("tokenizer", "my_edge_ngram_tokenizer").endObject().endObject().startObject("tokenizer")
				.startObject("my_edge_ngram_tokenizer").field("min_gram", "1").field("type", "edgeNGram").field("max_gram", "10")
				.startArray("token_chars").value("letter").value("digit").endArray().endObject().endObject().endObject().endObject().endObject()
				.string();
		System.out.println(source);
		client.admin().indices().prepareCreate(INDICE_NAME).setSource(source).execute().actionGet();
	}

	private void initMapping() throws IOException {
		Client client = ElasticSearchUtils.getClientInstance();
		XContentBuilder mappingBuilder = XContentFactory.jsonBuilder().startObject().startObject(INDICE_TYPE).startObject("properties")
				.startObject("etitle").field("type", "string").field("index", "analyzed").field("store", true)
				.field("analyzer", "my_edge_ngram_analyzer").field("formate", "").field("default", "").endObject().endObject().endObject()
				.endObject();
		System.out.println(mappingBuilder.string());
		PutMappingResponse response = client.admin().indices().preparePutMapping(INDICE_NAME).setType(INDICE_TYPE).setSource(mappingBuilder)
				.execute().actionGet();
		System.out.println(response.isAcknowledged());
	}

	private void indexRecord() {
		Client client = ElasticSearchUtils.getClientInstance();
		IndexRequestBuilder indexRequestBuilder = client.prepareIndex().setIndex(INDICE_NAME).setType(INDICE_TYPE).setId("1")
				.setSource("etitle", "ljmzhgmh");
		System.out.println(indexRequestBuilder.toString());
		IndexResponse indexResponse = indexRequestBuilder.execute().actionGet();
		System.out.println(indexResponse.isCreated());
		IndexRequestBuilder indexRequestBuilder2 = client.prepareIndex().setIndex(INDICE_NAME).setType(INDICE_TYPE).setId("2")
				.setSource("etitle", "bxjg");
		System.out.println(indexRequestBuilder2.toString());
		IndexResponse indexResponse2 = indexRequestBuilder2.execute().actionGet();
		System.out.println(indexResponse2.isCreated());

	}

	private void searchRecord() {
		Client client = ElasticSearchUtils.getClientInstance();
		QueryBuilder queryBuilder = new TermQueryBuilder("etitle", "ljm");
		SearchResponse response = client.prepareSearch("ngram2").setQuery(queryBuilder).execute().actionGet();
		System.out.println(response.getHits());
	}
}
