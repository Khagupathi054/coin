package com.coin.service;
import com.coin.entity.Coin;
import com.coin.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoinService {

    private static int[] bills = {1, 2, 5, 10, 20, 50, 100};
    private static double[] coins = {0.01, 0.05, 0.10, 0.25};
    private static int[] billCounts = new int[bills.length];
    private static int[] coinCounts = new int[coins.length];

    @Autowired
    private CoinRepository coinRepository;

    public List<Coin> calculateChange(int amount) {
        List<Coin> change = new ArrayList<>();
        if (amount < 0) {
            return change;
        }

        int[] billChangeCounts = new int[bills.length];
        int[] coinChangeCounts = new int[coins.length];

        for (int i = bills.length - 1; i >= 0; i--) {
            int bill = bills[i];

            while (amount >= bill && billCounts[i] < 100) {
                amount -= bill;
                billCounts[i]++;
                billChangeCounts[i]++;
            }
        }

        for (int i = coins.length - 1; i >= 0; i--) {
            double coin = coins[i];

            while (amount >= coin && coinCounts[i] > 0) {
                amount -= coin;
                coinCounts[i]--;
                coinChangeCounts[i]++;
            }
        }

        if (amount > 0) {
            // Handle not enough coins
            return change;
        } else {
            // Add bills to the change list
            for (int i = 0; i < billChangeCounts.length; i++) {
                int count = billChangeCounts[i];
                if (count > 0) {
                    Coin coin = new Coin(bills[i], count);
                    change.add(coin);
                }
            }

            // Add coins to the change list
            for (int i = 0; i < coinChangeCounts.length; i++) {
                int count = coinChangeCounts[i];
                if (count > 0) {
                    Coin coin = new Coin(coins[i], count);
                    change.add(coin);
                }
            }
        }
        return change;
    }

    public List<Coin> getAllCoins() {
        return coinRepository.findAll();
    }

    public Coin getCoinByDenomination(double denomination) {
        return coinRepository.findCoinByDenomination(denomination);
    }
}
