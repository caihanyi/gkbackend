package com.chy.gk.controller;

import com.chy.gk.model.city.City;
import com.chy.gk.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MapController {
    @Autowired
    private CityService cityService;

    @PostMapping("/getCitiesInfo")
    public Map<String, Object> buildVerificationCode(@RequestBody Map<String, String> Map){
        Map<String, Object> map = new HashMap<String, Object>();
        long provinceId = 0l;
        try{
            provinceId = Long.valueOf(Map.get("province"));
        }catch(Exception e){
            map.put("code","401");
            map.put("msg","省份参数出错");
            return map;
        }
        List<City> cities = cityService.findCitiesByProvince(provinceId);
        if(cities.isEmpty()){
            map.put("code", "401");
            map.put("msg", "城市列表为空");
            return map;
        }
        map.put("code","200");
        map.put("cities",cities);
        return map;
    }

}
