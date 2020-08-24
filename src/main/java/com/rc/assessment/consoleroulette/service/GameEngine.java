package com.rc.assessment.consoleroulette.service;

import com.rc.assessment.consoleroulette.model.Player;
import com.rc.assessment.consoleroulette.model.PlayerBet;
import com.rc.assessment.consoleroulette.repository.PlayerRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.Set;

@Component
class GameEngine {
	private static final PrimitiveIterator.OfInt WINNING_NUMBERS = new Random().ints(0, 36).iterator();

	private final PlayerRepository playerRepository;

	private final BetsResultsProcessor betsResultsProcessor;

	private final GameResultsPublisher gameResultsPublisher;

	public GameEngine(PlayerRepository playerRepository, BetsResultsProcessor betsResultsProcessor, GameResultsPublisher gameResultsPublisher) {
		this.playerRepository = playerRepository;
		this.betsResultsProcessor = betsResultsProcessor;
		this.gameResultsPublisher = gameResultsPublisher;
	}

	@Scheduled(fixedRate = 30000, initialDelay = 30000)
	void landBall() {
		int winningNumber = nextWinningNumber();
		Set<Player> playerBets = playerRepository.getRegisteredPlayers();

		
	}

	private int nextWinningNumber() {
		return WINNING_NUMBERS.nextInt();
	}

}
