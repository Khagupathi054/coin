package com.coin.controller;

import com.coin.entity.Coin;
import com.coin.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoinController {

    @Autowired
    private CoinService coinService;

    @GetMapping("/calculate-change/{amount}")
    public List<Coin> calculateChange(@PathVariable int amount) {
        return coinService.calculateChange(amount);
    }

    // more endpoints
}
