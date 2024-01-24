package com.wineexchange.api.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.wineexchange.api.domain.Tag;
import com.wineexchange.api.domain.Wine;
import com.wineexchange.api.domain.WineComposition;
import com.wineexchange.api.domain.Winery;
import com.wineexchange.api.repository.WineCompositionRepository;
import com.wineexchange.api.repository.WineRepository;
import com.wineexchange.api.repository.WineryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class WineService {

    private final WineRepository wineRepository;
    private final WineCompositionRepository wineCompositionRepository;
    private final WineryRepository wineryRepository;

    @Autowired
    public WineService(WineRepository wineRepository, WineCompositionRepository wineCompositionRepository, WineryRepository wineryRepository) {
        this.wineRepository = wineRepository;
        this.wineCompositionRepository = wineCompositionRepository;
        this.wineryRepository = wineryRepository;
    }

    public List<Wine> getAllWines() {
        return wineRepository.findAll();
    }

    public Wine getWineById(UUID id) {
        return wineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wine not found with id: " + id));
    }

    public void updateWine(Wine updatedWine) {
        Wine wineInDb = wineRepository.findById(updatedWine.getId())
                .orElseThrow(() -> new EntityNotFoundException("Wine not found with id: " + updatedWine.getId()));

        wineInDb.setName(updatedWine.getName());
        wineInDb.setYears(updatedWine.getYears());
        wineInDb.setPrice(updatedWine.getPrice());
        wineInDb.setWinery(updatedWine.getWinery());
        wineInDb.setWineComposition(updatedWine.getWineComposition());
        wineInDb.setAvailable(updatedWine.isAvailable());

        // Update tags
        List<Tag> updatedTags = updatedWine.getTags();

        // Clear existing tags that are not in the updated collection
        wineInDb.getTags().removeIf(existingTag -> !updatedTags.contains(existingTag));

        // Update or add new tags
        for (Tag updatedTag : updatedTags) {
            if (!wineInDb.getTags().contains(updatedTag)) {
                wineInDb.getTags().add(updatedTag);
            }
        }

        wineRepository.save(wineInDb);
    }

    public Wine addWine(Wine wine) {
        wineRepository.save(wine);
        return wine;
    }

    public void deleteWine(UUID id) {
        wineRepository.deleteById(id);
    }
}
