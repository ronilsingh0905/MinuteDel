package com.ronildevelops.MinuteDel.Controller;


import com.ronildevelops.MinuteDel.DTO.StoreFoundDTO;
import com.ronildevelops.MinuteDel.Service.geo.StoreLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreLocationService storeLocationService;

    public StoreController(StoreLocationService storeLocationService) {
        this.storeLocationService = storeLocationService;
    }


    @GetMapping("/nearest")
    public ResponseEntity<StoreFoundDTO> getNearestStore(@RequestParam double latitude,@RequestParam double longitude){
        StoreFoundDTO response = storeLocationService.storeFinder(latitude, longitude);

        return ResponseEntity.ok(response);
    }
}
