package com.witshare.mars.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis通用Dao
 */
public interface RedisCommonDao {

    void put(String key, String value, long timeout, TimeUnit unit);

    void putMapAll(String key, Map<String, String> map, int timeout, TimeUnit unit);

    String getString(String redisKey);

    boolean existsKey(String reidsKey);

    String getAndDelete(String key);

    void setString(String redisKey, String redisValue);

    void incrementString(String redisKey, Long data);

    List<String> getHashKeys(String redisKey);

    Map<String, String> getHash(String redisKey);

    Map<String, String> getHashKeyStartsWith(String redisKey, String hashKeyPrefix);

    Map<String, String> getHashKeyEndsWith(String redisKey, String hashKeySuffix);

    String getHash(String redisKey, String hashKey);

    void putHash(String redisKey, String hashKey, String hashValue);

    void putHash(String redisKey, Map<String, String> map);

    boolean isMember(String redisKey, Object o);

    Set<String> getSet(String redisKey);

    void addSet(String redisKey, String... values);


    void incrementHash(String redisKey, String hashKey, long data);

    void delRedisKey(String redisKey);

    void expire(String redisKey, long second);

    void setExpireByHour(String key, Integer hour);

    void setExpireByDay(String key, Integer day);

    /**
     * 获取key的过期时间
     *
     * @param key
     * @return
     */
    Long getKeyExpire(String key);

    boolean existsHashKey(String reidsKey, String field);

    List<String> scanParams(String pattern);

    /**
     * list 左边入队列
     *
     * @param redisKey
     * @param field
     */
    public void lPushList(String redisKey, String field);

    /**
     * redis转移
     *
     * @param redisKey_
     * @param redisKey
     */
    void list2list(String redisKey_, String redisKey);

    /**
     * list 右边弹出队列
     *
     * @param redisKey
     */
    public String rPopList(String redisKey);

    /**
     * list长度
     *
     * @param redisKey
     * @return
     */
    Long getLengthList(String redisKey);

    /**
     * 添加到set
     *
     * @param redisKey
     * @param field
     */
    void addSet(String redisKey, String field);

    /**
     * 返回set所有元素
     *
     * @param redisKey
     * @return
     */
    Set<String> members(String redisKey);

    /**
     * 移除set
     *
     * @param redisKey
     * @param field
     */
    void remove(String redisKey, String field);

    /**
     * 元素是否属于set
     *
     * @param redisKey
     * @param field
     */
    Boolean isMember(String redisKey, String field);

    void delHash(String redisKey, String hashKey);


    /*
        hash field 递增
     */
    void hincrBy(String redisKey, String field, long value);


    /*
        递增并返回结果
     */

    long incrBy(String redisKey, long value);

}
