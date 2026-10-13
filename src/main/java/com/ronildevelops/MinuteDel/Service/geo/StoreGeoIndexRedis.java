package com.ronildevelops.MinuteDel.Service.geo;


import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StoreGeoIndexRedis {
    private static final String STORE_GEO_KEY ="geo:stores";
    private final StringRedisTemplate template;
    public StoreGeoIndexRedis(StringRedisTemplate redisTemplate){
        this.template = redisTemplate;
    }

    public void storeUpdate(Long storeId , double longitude, double latitude){
            template.opsForGeo().add(STORE_GEO_KEY, new Point(longitude, latitude), toMember(storeId));
    }
    private String toMember(Long storeId){
        return String.valueOf(storeId);
    }




}
