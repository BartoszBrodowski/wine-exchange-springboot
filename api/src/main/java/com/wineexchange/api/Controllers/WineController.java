package com.wineexchange.api.Controllers;

import com.wineexchange.api.Domain.Wine;
import com.wineexchange.api.Services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wines")
public class WineController {

    @Autowired
    WineService wineService;

    @PostMapping("/loadWines")
    public void loadWines(@RequestParam String csvFilePath) {
        wineService.loadWinesFromCsv(csvFilePath);
    }

    @GetMapping("/getAllWines")
    public ResponseEntity<Iterable<Wine>> getAllWines() {
        try {
            Iterable<Wine> wines = wineService.getAllWines();
            return ResponseEntity.ok(wines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getWineById")
    public ResponseEntity<Wine> getWineById(@RequestParam UUID id) {
        try {
            Wine wine = wineService.getWineById(id);
            return ResponseEntity.ok(wine);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/addWine")
    public ResponseEntity<String> addWine(@RequestBody Wine wine) {
        try {
            wineService.addWine(wine);
            return ResponseEntity.status(HttpStatus.CREATED).body("Wine added successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to add wine. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/updateWine")
    public ResponseEntity<String> updateWine(@RequestBody Wine wine) {
        try {
            wineService.updateWine(wine);
            return ResponseEntity.ok("Wine updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update wine");
        }
    }

    @DeleteMapping("/deleteWine")
    public ResponseEntity<String> deleteWine(@RequestParam UUID id) {
        try {
            wineService.deleteWine(id);
            return ResponseEntity.ok("Wine deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete wine");
        }
    }
}
