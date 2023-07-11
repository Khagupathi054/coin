package com.coin.repository;

import com.coin.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Long> {
    Coin findCoinByDenomination(Double denomination);
}