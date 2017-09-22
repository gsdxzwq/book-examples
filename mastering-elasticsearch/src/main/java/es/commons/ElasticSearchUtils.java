package es.commons;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearchUtils {

    private static TransportClient client;

    /**
     * 返回一个client单例
     *
     * @return
     */
    public static synchronized TransportClient getClientInstance() {
        if (client == null) {
            initClient();
        }
        return client;
    }

    /**
     * 初始化Client
     */
    private static void initClient() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "zhaowq").put("client.transport.sniff", true).build();
        client = new TransportClient(settings);
        try {
            client = client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 判断索引库是否已存在
     */
    public static boolean indicesExist(String indiceName) {
        Client client = getClientInstance();
        // 第一个参数用client还是client.admin().indices()？？？
        IndicesExistsRequest indicesExistsRequest =
                new IndicesExistsRequestBuilder(client.admin().indices(), indiceName).request();
        IndicesExistsResponse response = client.admin().indices().exists(indicesExistsRequest).actionGet();
        return response.isExists();
    }

    /**
     * 创建索引库
     */
    // client.admin().indices().prepareCreate("test").execute().actionGet();
    public static void createIndices(String indiceName) {
        CreateIndexRequest createIndexRequest =
                new CreateIndexRequestBuilder(client.admin().indices(), indiceName).request();
        client.admin().indices().create(createIndexRequest).actionGet();
    }

    /**
     * 删除索引库
     */
    public static void deleteIndices(String indiceName) {
        Client client = getClientInstance();
        client.admin().indices().prepareDelete(indiceName).execute().actionGet();
    }
}
