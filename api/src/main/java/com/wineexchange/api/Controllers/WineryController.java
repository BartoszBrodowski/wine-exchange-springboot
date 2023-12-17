package com.wineexchange.api.Controllers;

import com.wineexchange.api.Domain.Winery;
import com.wineexchange.api.Services.WineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/wineries")
public class WineryController {
    @Autowired
    WineryService wineryService;

    @GetMapping("/getAllWineries")
    public ResponseEntity<Iterable<Winery>> getAllWineries() {
        try {
            Iterable<Winery> wineries = wineryService.getAllWineries();
            return ResponseEntity.ok(wineries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getWineryById/{id}")
    public ResponseEntity<Winery> getWineryById(@PathVariable UUID id) {
        try {
            Winery winery = wineryService.getWineryById(id);
            return ResponseEntity.ok(winery);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/addWinery")
    public ResponseEntity<String> addWinery(@RequestBody Winery winery) {
        try {
            wineryService.addWinery(winery);
            return ResponseEntity.status(HttpStatus.CREATED).body("Winery added successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to add winery. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/updateWinery")
    public ResponseEntity<String> updateWinery(@RequestBody Winery winery) {
        try {
            wineryService.updateWinery(winery);
            return ResponseEntity.ok("Winery updated successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to update winery. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/deleteWinery/{id}")
    public ResponseEntity<String> deleteWinery(@PathVariable UUID id) {
        try {
            wineryService.deleteWinery(id);
            return ResponseEntity.ok("Winery deleted successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to delete winery. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
