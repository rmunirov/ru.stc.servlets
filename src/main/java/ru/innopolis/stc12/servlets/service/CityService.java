package ru.innopolis.stc12.servlets.service;

import ru.innopolis.stc12.servlets.pojo.City;
import ru.innopolis.stc12.servlets.repository.dao.CityDao;

import java.util.List;

public class CityService {
    private CityDao cityDao;

    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public CityService() {
        cityDao = new CityDao();
    }

    public List<City> getAll() {
        return cityDao.getAll();
    }

    public City get(int id) {
        return cityDao.read(id);
    }
}
