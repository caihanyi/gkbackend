package com.chy.gk.service;

import com.chy.gk.model.city.City;

import java.util.List;

public interface CityService {
    public List<City> findCitiesByProvince(long provinceId);
}
