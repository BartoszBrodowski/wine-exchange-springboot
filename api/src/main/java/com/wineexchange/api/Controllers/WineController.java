package com.wineexchange.api.Controllers;

import com.wineexchange.api.Domain.Wine;
import com.wineexchange.api.Repository.WineRepository;
import com.wineexchange.api.Services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("/getAllWines")
    public Iterable<Wine> getAllWines(){
        return wineService.getAllWines();
    }

    @GetMapping("/getWineById")
    public Wine getWineById(@RequestBody String id){
        return wineService.getWineById(id);
    }
    @PostMapping("/addWine")
    public void addWine(@RequestBody Wine wine){
        wineService.addWine(wine);
    }

    @PutMapping("/updateWine")
    public void updateWine(@RequestBody Wine wine){
        wineService.updateWine(wine);
    }

    @DeleteMapping("/deleteWine")
    public void deleteWine(@RequestBody String id){
        wineService.deleteWine(id);
    }
}
