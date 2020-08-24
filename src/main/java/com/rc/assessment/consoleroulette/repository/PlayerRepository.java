package com.rc.assessment.consoleroulette.repository;

import com.google.common.collect.ImmutableSet;
import com.rc.assessment.consoleroulette.model.Player;

public interface PlayerRepository {
    void addPlayer(Player player);

    ImmutableSet<Player> getRegisteredPlayers();
}
