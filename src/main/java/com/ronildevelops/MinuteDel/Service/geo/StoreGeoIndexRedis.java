package com.ronildevelops.MinuteDel.Service.geo;


import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

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

    public Map<Long,Double> storeLocationFinder(double longitude, double latitude,double radius){
        Point centre = new Point(longitude,latitude);
        Distance dist = new Distance(radius, Metrics.KILOMETERS);


        RedisGeoCommands.GeoSearchCommandArgs args = RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().sortAscending().includeDistance();


        GeoResults<RedisGeoCommands.GeoLocation<String>> results = template.opsForGeo().search(STORE_GEO_KEY, GeoReference.fromCoordinate(centre),dist,args);

        Map<Long,Double> storeDistanceMap = new LinkedHashMap<>();

        if(results == null || results.getContent().isEmpty()){
            return storeDistanceMap;
        }

        for (GeoResult<RedisGeoCommands.GeoLocation<String>> item : results.getContent()) {
            Long storeId = Long.parseLong(item.getContent().getName());
            double distance = item.getDistance().getValue();
            storeDistanceMap.put(storeId, distance);
        }

        return storeDistanceMap;

    }


}





