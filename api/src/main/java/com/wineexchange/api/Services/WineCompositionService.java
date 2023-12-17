package com.wineexchange.api.Services;

import com.wineexchange.api.Domain.WineComposition;
import com.wineexchange.api.Repository.WineCompositionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WineCompositionService {

    private final WineCompositionRepository wineCompositionRepository;

    @Autowired
    public WineCompositionService(WineCompositionRepository wineCompositionRepository) {
        this.wineCompositionRepository = wineCompositionRepository;
    }

    public List<WineComposition> getAllWineCompositions() {
        return wineCompositionRepository.findAll();
    }

    public WineComposition getWineCompositionById(UUID id) {
        return wineCompositionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WineComposition not found with id: " + id));
    }

    public void addWineComposition(WineComposition wineComposition) {
        wineCompositionRepository.save(wineComposition);
    }

    public void updateWineComposition(WineComposition wineComposition) {
        if (wineCompositionRepository.existsById(wineComposition.getId())) {
            wineCompositionRepository.save(wineComposition);
        } else {
            throw new EntityNotFoundException("WineComposition not found with id: " + wineComposition.getId());
        }
    }

    public void deleteWineComposition(UUID id) {
        if (wineCompositionRepository.existsById(id)) {
            wineCompositionRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("WineComposition not found with id: " + id);
        }
    }
}
