package com.rc.assessment.consoleroulette.repository;

import com.rc.assessment.consoleroulette.model.Player;

import java.util.Set;

public interface PlayerRepository {
    void addPlayer(Player player);

    Set<Player> getRegisteredPlayers();
}
