package es.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import es.commons.ElasticSearchUtils;

public class SearchServiceImpl {
	private static String indiceName = "test";

	public static void main(String[] args) {
		Client client = ElasticSearchUtils.getClientInstance();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indiceName).addFields();
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

		for (SearchHit searchHit : searchResponse.getHits().getHits()) {
			System.out.println(searchHit.getId());

			if (searchHit.getFields().containsKey("title")) {
				System.out.println(searchHit.getFields().get("title").getValue().toString());
			}
		}

	}

	/**
	 * 查询
	 */
	// 需优化较多，查询条件，返回格式，分面
	public void search(QueryBuilder queryBuilder) {
		Client client = ElasticSearchUtils.getClientInstance();
		// QueryBuilder qb1 = QueryBuilders.termQuery("name", "kimchy");
		//
		// QueryBuilder qb2 =
		// QueryBuilders.boolQuery().must(QueryBuilders.termQuery("content",
		// "test1")).must(QueryBuilders.termQuery("content", "test4"))
		// .mustNot(QueryBuilders.termQuery("content",
		// "test2")).should(QueryBuilders.termQuery("content", "test3"));

		// filter还能用吗
		// QueryBuilder qb3 =
		// filteredQuery(QueryBuilders.termQuery("name.first", "shay"),
		// QueryBuilders.rangeFilter("age").from(23).to(54).includeLower(true)
		// .includeUpper(false));

		SearchResponse searchResponse = client.prepareSearch(indiceName).setQuery(queryBuilder).setFrom(0).setSize(60).setExplain(true).execute()
				.actionGet();

		// 解析SearchResponse
		SearchHits hits = searchResponse.getHits();
		for (int i = 0; i < 60; i++) {
			System.out.println(hits.getAt(i).getSource().get("field"));
		}
	}
}
