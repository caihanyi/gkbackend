package com.chy.gk.service.impl;

import com.chy.gk.model.city.City;
import com.chy.gk.repository.CityRepository;
import com.chy.gk.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findCitiesByProvince(long provinceId) {
        String status = "100";//状态默认为 100-有效
        return cityRepository.findAllByStatusAndProvinceId(status,provinceId);
    }

    public CityRepository getCityRepository() {
        return cityRepository;
    }

    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
}
