package com.rc.assessment.consoleroulette.repository;

import com.rc.assessment.consoleroulette.model.PlayerBet;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BetRepositoryImpl implements BetRepository {
    private final AtomicReference<Set<PlayerBet>> playerBetsHolder = new AtomicReference<>(emptySet());

    @Override
    public void addBet(PlayerBet playerBet) {
        playerBetsHolder.updateAndGet(bets -> {
            bets.add(playerBet);
            return bets;
        });
    }

    @Override
    public Set<PlayerBet> getCurrentGameBets() {
        return playerBetsHolder.getAndSet(emptySet());
    }

    private ConcurrentHashMap.KeySetView<PlayerBet, Boolean> emptySet() {
        return ConcurrentHashMap.newKeySet();
    }
}
