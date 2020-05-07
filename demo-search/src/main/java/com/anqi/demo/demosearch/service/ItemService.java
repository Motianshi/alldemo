package com.anqi.demo.demosearch.service;

import com.anqi.demo.demosearch.entity.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ObjectMapper mapper;

    public String loadJson(){

        InputStream in = null;
        InputStreamReader inr = null;
        BufferedReader buf = null;


        try {
            in = this.getClass().getClassLoader().getResourceAsStream("items.json");
            inr = new InputStreamReader(in, "utf-8");
            buf = new BufferedReader(inr);

            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = buf.read()) != -1) {
                sb.append((char)ch);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (inr != null) {
                    inr.close();
                }
                if (buf != null) {
                    buf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String getItemsJson() {
        String res = "";
        String items = loadJson();
        List<Item> itemList = new ArrayList<>();

        try {
            JsonNode jsonNode = mapper.readTree(items);
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    Item item = new Item();
                    item.setItemId(node.get("商品ID").asText());
                    item.setUrlId(node.get("_id").asText());

                    JsonNode sellAddress = node.get("卖家地址");
                    if (sellAddress != null) {
                        item.setSellAddress(sellAddress.asText());
                    }

                    JsonNode courierFee = node.get("快递费");
                    if (courierFee != null) {
                        item.setCourierFee(courierFee.asText());
                    }

                    JsonNode promotions = node.get("优惠活动");
                    if (promotions != null) {
                        item.setPromotions(promotions.asText());
                    }
                    item.setOriginalPrice(node.get("原价").asText());
                    JsonNode start = node.get("活动开始时间");
                    if (start != null) {
                        item.setStartTime(start.asText());
                    }
                    JsonNode end = node.get("活动结束时间");
                    if (end != null) {
                        item.setEndTime(end.asText());
                    }
                    item.setTitle(node.get("标题").asText());

                    JsonNode guarantee = node.get("服务保障");
                    if (guarantee != null) {
                        item.setServiceGuarantee(guarantee.asText());
                    }

                    item.setVenue(node.get("会场").asText());
                    JsonNode currentPrice = node.get("现价");
                    if (currentPrice != null) {
                        item.setCurrentPrice(currentPrice.asText());
                    }
                    itemList.add(item);
                }
            }
            res = mapper.writeValueAsString(itemList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

}
