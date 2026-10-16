package com.ronildevelops.MinuteDel.Service.geo;


import com.ronildevelops.MinuteDel.Entity.DarkStoreEntity;
import com.ronildevelops.MinuteDel.Repository.DarkStoreEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreFinder {

    @Autowired
    DarkStoreEntityRepository darkStoreEntityRepository;



}
