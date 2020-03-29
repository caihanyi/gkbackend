package com.chy.gk.repository;

import com.chy.gk.model.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor {
    public List<City> findAllByStatusAndProvinceId(String status,long provinceId);
}
