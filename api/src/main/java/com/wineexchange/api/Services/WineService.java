package com.wineexchange.api.Services;

import com.wineexchange.api.Domain.Wine;
import com.wineexchange.api.Repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineService {

    private final WineRepository wineRepository;

    @Autowired
    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    public List<Wine> getAllWines() {
        return wineRepository.findAll();
    }

    public Wine getWineById(String id) {
        return wineRepository.findById(id).orElseThrow();
    }

    public void updateWine(Wine updatedWine) {
        Wine wineInDb = wineRepository.findById(String.valueOf(updatedWine. getId())).orElse(null);
        if (wineInDb != null) {
            wineInDb.setName(updatedWine.getName());
            wineInDb.setYear(updatedWine.getYear());
            wineInDb.setDate(updatedWine.getDate());
            wineInDb.setPrice(updatedWine.getPrice());
            wineInDb.setWinery(updatedWine.getWinery());
            wineInDb.setWineComposition(updatedWine.getWineComposition());
            wineInDb.setAvailable(updatedWine.isAvailable());
            wineRepository.save(wineInDb);
        }
    }

    public void addWine(Wine wine) {
        wineRepository.save(wine);
    }
    public void deleteWine(String id) {
        wineRepository.deleteById(id);
    }
}

