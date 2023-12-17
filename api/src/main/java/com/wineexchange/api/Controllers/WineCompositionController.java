package com.wineexchange.api.Controllers;

import com.wineexchange.api.Domain.WineComposition;
import com.wineexchange.api.Services.WineCompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wineComposition")
public class WineCompositionController {
    private final WineCompositionService wineCompositionService;

    @Autowired
    public WineCompositionController(WineCompositionService wineCompositionService) {
        this.wineCompositionService = wineCompositionService;
    }

    @GetMapping("/getWineCompositionById/{id}")
    public ResponseEntity<String> getWineCompositionById(@PathVariable UUID id) {
        try {
            WineComposition wineComposition = wineCompositionService.getWineCompositionById(id);
            return ResponseEntity.ok("Wine Composition added successfully: " + wineComposition.toString());
        } catch (Exception e) {
            String errorMessage = "Failed to add wine. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping("/addWineComposition")
    public ResponseEntity<String> addWineComposition(@RequestBody WineComposition wineComposition) {
        try {
            wineCompositionService.addWineComposition(wineComposition);
            return ResponseEntity.status(HttpStatus.CREATED).body("Wine Composition added successfully: " + wineComposition.toString());
        } catch (Exception e) {
            String errorMessage = "Failed to add wine composition. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/updateWineComposition")
    public ResponseEntity<String> updateWineComposition(@RequestBody WineComposition wineComposition) {
        try {
            wineCompositionService.updateWineComposition(wineComposition);
            return ResponseEntity.ok("Wine Composition updated successfully: " + wineComposition.toString());
        } catch (Exception e) {
            String errorMessage = "Failed to update wine composition. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/deleteWineComposition/{id}")
    public ResponseEntity<String> deleteWineComposition(@PathVariable UUID id) {
        try {
            wineCompositionService.deleteWineComposition(id);
            return ResponseEntity.ok("Wine Composition deleted successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to delete wine composition. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
