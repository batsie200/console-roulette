package com.rc.assessment.consoleroulette.model;

import lombok.Getter;

@Getter
public class PlayerBet {
	private final Player player;
	private final Bet bet;

	public PlayerBet(Player player, Bet bet) {
		this.player = player;
		this.bet = bet;
	}
}
