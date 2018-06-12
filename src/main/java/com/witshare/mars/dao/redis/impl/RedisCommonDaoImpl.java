package com.witshare.mars.dao.redis.impl;

import com.google.gson.Gson;
import com.witshare.mars.constant.CacheConsts;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.util.TryBase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis通用Dao
 */
@Repository
public class RedisCommonDaoImpl implements RedisCommonDao {

    private static Logger logger = LoggerFactory.getLogger(RedisCommonDaoImpl.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void put(String key, String value, long timeout, TimeUnit unit) {
        logger.info("redisDao put enter :  key={}, value={}, timeout={}, timeUnit={}", key, value, timeout, unit);
        stringRedisTemplate.boundValueOps(key).set(value, timeout, unit);
    }

    @Override
    public void putMapAll(String key, Map<String, String> map, int timeout, TimeUnit unit) {
        logger.info("redisDao putMapAll enter :  key={}, map={}, timeout={}, timeUnit={}", key, map, timeout, unit);
        TryBase.ofr(3, () -> {
            stringRedisTemplate.opsForHash().putAll(key, map);
            stringRedisTemplate.expire(key, timeout, unit);
        });
        try {
            Map<Object, Object> newMap = stringRedisTemplate.opsForHash().entries(key);
            logger.info("redisDao putMapAll complete :  key={}, map={}, timeout={}, timeUnit={}, newMap={}", key, map, timeout, unit, newMap);
        } catch (Exception e) {
            logger.error("redisDao putMapAll complete :  key={}, map={}, timeout={}, timeUnit={}, getNewMap error, e=", key, map, e);
        }
    }

    @Override
    public String getString(String redisKey) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForValue().get(redisKey)
        ).get();
    }

    @Override
    public void setString(String redisKey, String redisValue) {
        logger.info("redisDao setString :  key={}, value={}", redisKey, redisValue);
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForValue().set(redisKey, redisValue)
        );
    }

    @Override
    public void incrementString(String redisKey, Long data) {
        logger.info("redisDao incrementString :  key={}, data={}", redisKey, data);
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForValue().increment(redisKey, data)
        );
    }


    @Override
    public boolean existsKey(String redisKey) {
        logger.info("redisDao existsKey :  redisKey={}", redisKey);
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.hasKey(redisKey)
        ).get();
    }

    @Override
    public String getAndDelete(String key) {
        logger.info("redisDao getAndDelete enter :  key={}", key);
        SessionCallback<String> sessionCallback = new SessionCallback<String>() {
            @Override
            public String execute(RedisOperations operations) throws DataAccessException {
                operations.watch(key);
                String flag = (String) operations.boundValueOps(key).get();
                operations.multi();
                if (flag != null && flag.trim().length() > 0) {
                    operations.delete(key);
                }
                operations.exec();
                return flag;
            }
        };
        String result = stringRedisTemplate.execute(sessionCallback);
        logger.info("redisDao getAndDelete complete :  key={}, result={}", key, result);
        return result;
    }

    @Override
    public List<String> getHashKeys(String redisKey) {
        List<String> hashKeys = new ArrayList<>();
        Set<Object> keys = TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForHash().keys(redisKey)
        ).get();
        if (CollectionUtils.isEmpty(keys))
            return hashKeys;
        for (Object key : keys) {
            hashKeys.add(String.valueOf(key));
        }
        logger.info("redisDao getHashKeys :  key={}, data={}", redisKey, new Gson().toJson(hashKeys));
        return hashKeys;
    }

    @Override
    public Map<String, String> getHash(String redisKey) {
        Map<String, String> hashKeyStartsWith = getHashKeyStartsWith(redisKey, null);
        logger.info("redisDao getHash :  key={}, data={}", redisKey, new Gson().toJson(hashKeyStartsWith));
        return hashKeyStartsWith;
    }

    @Override
    public Map<String, String> getHashKeyStartsWith(String redisKey, String hashKeyPrefix) {
        Map<String, String> hash = new HashMap<>();
        List<String> hashKeys = getHashKeys(redisKey);
        if (CollectionUtils.isEmpty(hashKeys))
            return hash;
        for (String hashKey : hashKeys) {
            if (!StringUtils.isEmpty(hashKeyPrefix) && !hashKey.startsWith(hashKeyPrefix)) {
                continue;
            }
            String value = getHash(redisKey, hashKey);
            if (!StringUtils.isEmpty(value))
                hash.put(hashKey, value);
        }
        logger.info("redisDao getHashKeyStartsWith :  key={}, hashKeyPrefix:{}, data={}", redisKey, hashKeyPrefix, new Gson().toJson(hash));
        return hash;
    }

    @Override
    public Map<String, String> getHashKeyEndsWith(String redisKey, String hashKeySuffix) {
        Map<String, String> hash = new HashMap<>();
        List<String> hashKeys = getHashKeys(redisKey);
        if (CollectionUtils.isEmpty(hashKeys))
            return hash;
        for (String hashKey : hashKeys) {
            if (!StringUtils.isEmpty(hashKeySuffix) && !hashKey.endsWith(hashKeySuffix)) {
                continue;
            }
            String value = getHash(redisKey, hashKey);
            if (!StringUtils.isEmpty(value))
                hash.put(hashKey, value);
        }
        logger.info("redisDao getHashKeyEndsWith :  key={},hashKeySuffix:{}, data={}", redisKey, hashKeySuffix, new Gson().toJson(hash));
        return hash;
    }

    @Override
    public String getHash(String redisKey, String hashKey) {
        return TryBase.ofc(CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForHash().hasKey(redisKey, hashKey)).get() ?
                String.valueOf(TryBase.ofc(CacheConsts.RETRY_TIMES,
                        () -> stringRedisTemplate.opsForHash().get(redisKey, hashKey)).get()) :
                null;
    }

    @Override
    public void putHash(String redisKey, String hashKey, String hashValue) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForHash().put(redisKey, hashKey, hashValue)
        );
    }

    @Override
    public void putHash(String redisKey, Map<String, String> map) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForHash().putAll(redisKey, map)
        );
    }

    @Override
    public boolean isMember(String redisKey, Object o) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForSet().isMember(redisKey, o)
        ).get();
    }

    @Override
    public Set<String> getSet(String redisKey) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForSet().members(redisKey)
        ).get();
    }

    @Override
    public void addSet(String redisKey, String... values) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForSet().add(redisKey, values)
        );
    }

    @Override
    public void incrementHash(String redisKey, String hashKey, long data) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForHash().increment(redisKey, hashKey, data)
        );
    }

    @Override
    public void delRedisKey(String redisKey) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.delete(redisKey)
        );
    }

    @Override
    public void expire(String redisKey, long second) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.expire(redisKey, second, TimeUnit.SECONDS)
        );
    }

    /**
     * 设置过期时间-以小时为单位
     *
     * @param key
     * @param hour
     */
    @Override
    public void setExpireByHour(String key, Integer hour) {
        expire(key, 60 * 60 * hour);
    }

    /**
     * 设置过期时间-以天为单位
     *
     * @param key
     * @param day
     */
    @Override
    public void setExpireByDay(String key, Integer day) {
        expire(key, 60 * 60 * 24 * day);
    }

    /**
     * 获取该key的过期时间
     *
     * @param key
     * @return
     */
    @Override
    public Long getKeyExpire(String key) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.getExpire(key)
        ).get();
    }

    @Override
    public boolean existsHashKey(String redisKey, String field) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForHash().hasKey(redisKey, field)
        ).get();
    }

    public List<String> scanParams(String pattern) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate
                        .execute(new RedisCallback<List<String>>() {
                            @Override
                            public List<String> doInRedis(RedisConnection connection)
                                    throws DataAccessException {
                                List<String> list = new ArrayList<>();
                                StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
                                ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(connection.dbSize()).build();
                                Cursor<byte[]> c = stringRedisConnection.scan(scanOptions);
                                while (c.hasNext()) {
                                    list.add(new String(c.next()));

                                }
                                return list;
                            }
                        })).get();
    }

    @Override
    public void lPushList(String redisKey, String field) {
        logger.info("redisDao lPushList enter :  key={}, value={}", redisKey, field);
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForList().leftPush(redisKey, field)
        );
    }


    @Override
    public void list2list(String redisKey_, String redisKey) {
        List<String> tokens = TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForList().range(redisKey_, 0, -1)
        ).get();
        TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForList().leftPushAll(redisKey, tokens)
        );
    }


    @Override
    public String rPopList(String redisKey) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForList().rightPop(redisKey)
        ).get();
    }

    @Override
    public Long getLengthList(String redisKey) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForList().size(redisKey)
        ).get();
    }


    @Override
    public void addSet(String redisKey, String field) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForSet().add(redisKey, field)
        );
    }


    @Override
    public Set<String> members(String redisKey) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForSet().members(redisKey)
        ).get();
    }


    @Override
    public void remove(String redisKey, String field) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForSet().remove(redisKey, field)
        );
    }

    @Override
    public Boolean isMember(String redisKey, String field) {
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForSet().isMember(redisKey, field)
        ).get();
    }

    @Override
    public void delHash(String redisKey, String hashKey) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForHash().delete(redisKey, hashKey)
        );
    }


    @Override
    public void hincrBy(String redisKey, String field, long value) {
        TryBase.ofr(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForHash().increment(redisKey, field, value)
        );
    }

    @Override
    public long incrBy(String redisKey, long data) {
        logger.info("redisDao incrementString :  key={}, data={}", redisKey, data);
        return TryBase.ofc(
                CacheConsts.RETRY_TIMES,
                () -> stringRedisTemplate.opsForValue().increment(redisKey, data)
        ).get();
    }
}
