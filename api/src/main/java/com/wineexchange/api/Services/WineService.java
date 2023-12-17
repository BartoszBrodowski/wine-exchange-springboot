package com.wineexchange.api.Services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.wineexchange.api.Domain.Wine;
import com.wineexchange.api.Domain.WineComposition;
import com.wineexchange.api.Domain.Winery;
import com.wineexchange.api.Repository.WineCompositionRepository;
import com.wineexchange.api.Repository.WineRepository;
import com.wineexchange.api.Repository.WineryRepository;
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

    @Transactional
    public void loadWinesFromCsv(String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                Wine wine = new Wine();
                wine.setName(row[0]);
                wine.setYear(Integer.parseInt(row[1]));
                wine.setDate(LocalDate.parse(row[2])); // Assuming date is in ISO format (yyyy-MM-dd)
                wine.setPrice(Float.parseFloat(row[3]));
                wine.setAvailable(Boolean.parseBoolean(row[4]));

                Winery winery = new Winery();
                winery.setName(row[5]);
                winery.setLocation(row[6]);

                WineComposition wineComposition = new WineComposition();
                wineComposition.setGrape(row[7]);
                wineComposition.setAlcohol(row[8]);

                wine.setWinery(winery);
                wine.setWineComposition(wineComposition);
                winery.getWines().add(wine);

                wineCompositionRepository.save(wineComposition);
                wineryRepository.save(winery);
                wineRepository.save(wine);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
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
        wineInDb.setYear(updatedWine.getYear());
        wineInDb.setDate(updatedWine.getDate());
        wineInDb.setPrice(updatedWine.getPrice());
        wineInDb.setWinery(updatedWine.getWinery());
        wineInDb.setWineComposition(updatedWine.getWineComposition());
        wineInDb.setAvailable(updatedWine.isAvailable());
        wineRepository.save(wineInDb);
    }

    public void addWine(Wine wine) {
        wineRepository.save(wine);
    }

    public void deleteWine(UUID id) {
        wineRepository.deleteById(id);
    }
}
