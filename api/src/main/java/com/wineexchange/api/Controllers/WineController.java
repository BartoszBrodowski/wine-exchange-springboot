package com.wineexchange.api.Controllers;

import com.wineexchange.api.Domain.Wine;
import com.wineexchange.api.Repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WineController {

    @Autowired
    WineRepository wineRepository;
    @PostMapping("/addWine")
    public void addWine(@RequestBody Wine wine){
        wineRepository.save(wine);
    }
}
