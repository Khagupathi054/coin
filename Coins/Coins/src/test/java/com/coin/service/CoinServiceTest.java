package com.coin.service;

import com.coin.entity.Coin;
import com.coin.repository.CoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CoinService.class)
@RunWith(MockitoJUnitRunner.class)

public class CoinServiceTest {

    @Autowired
    private CoinService coinService;

    @MockBean
    private CoinRepository coinRepository;

    @BeforeEach
    public void setup() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(20, 2));
        coins.add(new Coin(2, 5));
        when(coinRepository.findAll()).thenReturn(coins);
    }

    @Test
    public void testCalculateChange() {
        int amount = 42;

        List<Coin> change = coinService.calculateChange(amount);

        // Assert the expected change breakdown
        assertEquals(2, change.size());

        Coin coin1 = change.get(0);
        assertEquals(20, coin1.getDenomination());
        assertEquals(2, coin1.getCount());

        Coin coin2 = change.get(1);
        assertEquals(2, coin2.getDenomination());
        assertEquals(1, coin2.getCount());
    }

    @Test
    public void testCalculateChange_ExactAmount() {
        int amount = 20;

        List<Coin> change = coinService.calculateChange(amount);

        assertEquals(1, change.size());
        Coin coin = change.get(0);
        assertEquals(20, coin.getDenomination());
        assertEquals(1, coin.getCount());
    }

    @Test
    public void testCalculateChange_NotEnoughCoins() {
        int amount = 500;
        List<Coin> change = coinService.calculateChange(amount);
        // Assert that the change list is empty
        assertEquals(0, change.size());
    }


    @Test
    public void testCalculateChange_NegativeAmount() {
        int amount = -50;
        List<Coin> change = coinService.calculateChange(amount);
        assertEquals(0, change.size());
    }

    @Test
    public void testGetAllCoins() {
        // Mock data in the repository
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(1, 10));
        coins.add(new Coin(5, 5));
        when(coinRepository.findAll()).thenReturn(coins);

        List<Coin> result = coinService.getAllCoins();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetCoinByDenomination() {
        double denomination = 0.10;

        // Mock data in the repository
        Coin coin = new Coin(denomination, 20);
        when(coinRepository.findCoinByDenomination(denomination)).thenReturn(coin);

        Coin result = coinService.getCoinByDenomination(denomination);

        assertEquals(denomination, result.getDenomination());
        assertEquals(20, result.getCount());
    }
}

