package com.wineexchange.api.Services;

import com.wineexchange.api.Domain.User;
import com.wineexchange.api.Domain.Winery;
import com.wineexchange.api.Repository.WineryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WineryService {
    private final WineryRepository wineryRepository;
    @Autowired
    public WineryService(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }
    public void addWinery(Winery winery) {
        wineryRepository.save(winery);
    }

    public List<Winery> getAllWineries() {
        return wineryRepository.findAll();
    }

    public Winery getWineryById(UUID id) {
        return wineryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Winery not found with id: " + id));
    }

    public void updateWinery(Winery winery) {
        if (wineryRepository.existsById(winery.getId())) {
            wineryRepository.save(winery);
        } else {
            throw new EntityNotFoundException("Winery not found with id: " + winery.getId());
        }
    }

    public void deleteWinery(UUID id) {
        if (wineryRepository.existsById(id)) {
            wineryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Winery not found with id: " + id);
        }
    }
}
