package com.anqi.demo.demosearch;

import com.anqi.demo.demosearch.service.ItemService;
import com.anqi.demo.demosearch.service.RestHighLevelClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest(classes = DemoSearchApplication.class)
@RunWith(SpringRunner.class)
public class DemoSearchApplicationTests {

    @Autowired
    private ItemService itemService;
    @Autowired
    private RestHighLevelClientService clientService;

    @Test
    public void contextLoads() {
        String itemsJson = itemService.getItemsJson();
        System.out.println(itemsJson);
    }


    @Test
    public void createIdx() throws IOException {
        String settings = "" +
                "  {\n" +
                "      \"number_of_shards\" : \"2\",\n" +
                "      \"number_of_replicas\" : \"0\"\n" +
                "   }";
        String mappings = "" +
                "{\n" +
                "    \"properties\": {\n" +
                "      \"itemId\" : {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"ignore_above\": 64\n" +
                "      },\n" +
                "      \"urlId\" : {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"ignore_above\": 64\n" +
                "      },\n" +
                "      \"sellAddress\" : {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\", \n" +
                "        \"search_analyzer\": \"ik_smart\",\n" +
                "        \"fields\": {\n" +
                "          \"keyword\" : {\"ignore_above\" : 256, \"type\" : \"keyword\"}\n" +
                "        }\n" +
                "      },\n" +
                "      \"courierFee\" : {\n" +
                "        \"type\": \"text\n" +
                "      },\n" +
                "      \"promotions\" : {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\", \n" +
                "        \"search_analyzer\": \"ik_smart\",\n" +
                "        \"fields\": {\n" +
                "          \"keyword\" : {\"ignore_above\" : 256, \"type\" : \"keyword\"}\n" +
                "        }\n" +
                "      },\n" +
                "      \"originalPrice\" : {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"ignore_above\": 64\n" +
                "      },\n" +
                "      \"startTime\" : {\n" +
                "        \"type\": \"date\",\n" +
                "        \"format\": \"yyyy-MM-dd HH:mm:ss\"\n" +
                "      },\n" +
                "      \"endTime\" : {\n" +
                "        \"type\": \"date\",\n" +
                "        \"format\": \"yyyy-MM-dd HH:mm:ss\"\n" +
                "      },\n" +
                "      \"title\" : {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\", \n" +
                "        \"search_analyzer\": \"ik_smart\",\n" +
                "        \"fields\": {\n" +
                "          \"keyword\" : {\"ignore_above\" : 256, \"type\" : \"keyword\"}\n" +
                "        }\n" +
                "      },\n" +
                "      \"serviceGuarantee\" : {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\", \n" +
                "        \"search_analyzer\": \"ik_smart\",\n" +
                "        \"fields\": {\n" +
                "          \"keyword\" : {\"ignore_above\" : 256, \"type\" : \"keyword\"}\n" +
                "        }\n" +
                "      },\n" +
                "      \"venue\" : {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\", \n" +
                "        \"search_analyzer\": \"ik_smart\",\n" +
                "        \"fields\": {\n" +
                "          \"keyword\" : {\"ignore_above\" : 256, \"type\" : \"keyword\"}\n" +
                "        }\n" +
                "      },\n" +
                "      \"currentPrice\" : {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"ignore_above\": 64\n" +
                "      }\n" +
                "   }\n" +
                "}";
        clientService.createIndex("idx_item", settings, mappings);
    }


    @Test
    public void importAll() throws IOException {
        clientService.importAll("idx_item", true, itemService.getItemsJson());
    }

}
