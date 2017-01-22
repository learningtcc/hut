package com.hut.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by Jared on 2017/1/20.
 */

@Component
public class RedisService {

    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    /**
     * 保存数据
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();

            return shardedJedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
        return null;
    }

    /**
     * 根据key获取数据
     *
     * @param key
     * @return
     */
    public String get(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();

            return shardedJedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
        return null;
    }

    /**
     * 根据key删除数据
     *
     * @param key
     * @return
     */
    public Long delete(String key) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();

            return shardedJedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
        return null;
    }

    /**
     * 根据key设置数据存活时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();

            return shardedJedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
        return null;
    }

    /**
     * 保存数据,同时设置存活时间
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public Long set(String key, String value, int seconds) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();

            shardedJedis.set(key, value);
            return shardedJedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
        return null;
    }
}
