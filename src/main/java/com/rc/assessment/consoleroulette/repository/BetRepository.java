package com.rc.assessment.consoleroulette.repository;

import com.rc.assessment.consoleroulette.model.PlayerBet;

import java.util.Set;

public interface BetRepository {
	void addBet(PlayerBet playerBet);

	Set<PlayerBet> getCurrentGameBets();
}
