package com.hut.web.rabbit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Jared on 2017/1/16.
 */
public class Consumer {

        private static final ObjectMapper MAPPER = new ObjectMapper();

        // 具体执行业务的方法
        public void listen(String foo) {
            // 解析消息
            try {
                JsonNode jsonNode = MAPPER.readTree(foo);

                // 获取商品id
                long itemId = jsonNode.get("itemId").asLong();

                // 删除缓存
                //this.itemCacheService.deleteItemCacheByItemId(itemId);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
