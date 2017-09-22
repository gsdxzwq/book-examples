package es.index;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import es.commons.ElasticSearchUtils;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @ClassName: IndexServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhaowq
 * @date 2016年4月6日下午8:52:20
 *
 */
public class IndexServiceImpl {
    private static TransportClient client;
    private static String indiceName = "test";

    public static void main(String[] args) throws Exception {

        client = ElasticSearchUtils.getClientInstance();

        if (!ElasticSearchUtils.indicesExist(indiceName)) {
            ElasticSearchUtils.createIndices(indiceName);
        }

        initMapping(indiceName, null, false);

        addIndexRecord(indiceName, "", null);

        deleteIndexById();

        // 删除索引名为productIndex，title中包含query的所有文档
        deleteIndexByQuery();

        // get API

        // update API

        // Multi GET API

        bulk();

        // Use BulkProcessor

        // Morelikethis
        moreLikeThis();
    }

    /**
     * TODO
     */
    //两种方式？
    @SuppressWarnings("deprecation")
    private static void moreLikeThis() {
        // MoreLikeThisRequestBuilder mlt = new MoreLikeThisRequestBuilder(client, "indexName",
        // "indexType", "id");
        // mlt.setField("title");//匹配的字段
        // SearchResponse response = client.moreLikeThis(mlt.request()).actionGet();

        MoreLikeThisQueryBuilder query = QueryBuilders.moreLikeThisQuery();
        query.boost(1.0f).likeText("xxx").minTermFreq(10);
    }

    /**
     * 批量
     * 
     * @throws IOException
     */
    public static void bulk() throws IOException {
        BulkRequestBuilder bulkRequest = client.prepareBulk();

        // either use client#prepare, or use Requests# to directly build index/delete requests
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1").setSource(
                XContentFactory.jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date())
                        .field("message", "trying out Elastic Search").endObject()));

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2").setSource(
                XContentFactory.jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date()).field("message", "another post")
                        .endObject()));

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // 处理错误
        }
    }

    /**
     * 根据查询删除索引记录
     */
    //未调通
    public static void deleteIndexByQuery() {
       // Client client = getClientInstance();
       // QueryBuilder query = QueryBuilders.termQuery("title", "query");
       // client.prepareDeleteByQuery("productIndex").setQuery(query).execute().actionGet();
    }

    /**
     * 根据ID删除索引记录
     */
    public static void deleteIndexById() {
        DeleteResponse deleteResponse = client.prepareDelete("test", "doc", "1").execute().actionGet();
        if (!deleteResponse.isFound()) {

        }
    }

    /**
     * 创建索引
     * 
     * @throws IOException
     */
    public static void addIndexRecord(String indiceName, String id, Map<String, Object> fields) throws IOException {
        Client client = ElasticSearchUtils.getClientInstance();
        XContentBuilder doc =
                XContentFactory.jsonBuilder().startObject().field("title", "this is a title!").field("description", "descript what?")
                        .field("price", 100).field("onSale", true).field("type", 1).field("createDate", new Date()).endObject();
        client.prepareIndex(indiceName, "doc").setId(id).setSource(doc).execute().actionGet();
    }

    /**
     * 初始化Mapping
     * 
     * @throws IOException
     */
    public static void initMapping(String indiceName, List<?> fields, boolean isDelete) throws IOException {
        // 是否删除索引库

        // 如何根据fields构造XContentBuilder
        XContentBuilder mapping =
                XContentFactory.jsonBuilder().startObject().startObject(indiceName).startObject("doc").startObject("title").field("type", "string")
                        .field("store", "yes").endObject().startObject("description").field("type", "string").field("index", "not_analyzed")
                        .endObject().startObject("price").field("type", "double").endObject().startObject("onSale").field("type", "boolean")
                        .endObject().startObject("type").field("type", "integer").endObject().startObject("createDate").field("type", "date")
                        .endObject().endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest(indiceName).type("doc").source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }
}
