package com.wineexchange.api.controllers;

import com.wineexchange.api.domain.Wine;
import com.wineexchange.api.services.WineService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/wines")
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("/getAllWines")
    public ResponseEntity<Iterable<Wine>> getAllWines() {
        try {
            Iterable<Wine> wines = wineService.getAllWines();
            return ResponseEntity.ok(wines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getWineById/{id}")
    public ResponseEntity<Wine> getWineById(@PathVariable UUID id) {
        try {
            Wine wine = wineService.getWineById(id);
            return ResponseEntity.ok(wine);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/addWine")
    public ResponseEntity<Object> addWine(@RequestBody Wine wine) {
        Map<String, Object> response = new HashMap<>();
        try {
            Wine addedWine = wineService.addWine(wine);
            response.put("status", "success");
            response.put("message", "Wine added successfully");
            response.put("wine", addedWine);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to add wine");
            response.put("errorDetails", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    @PutMapping("/updateWine")
    public ResponseEntity<Object> updateWine(@RequestBody Wine wine) {
        Map<String, Object> response = new HashMap<>();
        try {
            wineService.updateWine(wine);
            response.put("status", "success");
            response.put("message", "Wine updated successfully");
            response.put("wine", wine);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to update wine");
            response.put("errorDetails", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/deleteWine/{id}")
    public ResponseEntity<Object> deleteWine(@PathVariable UUID id) {
        Map<String, String> response = new HashMap<>();
        try {
            wineService.deleteWine(id);
            response.put("status", "success");
            response.put("message", "Wine deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to delete wine");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
