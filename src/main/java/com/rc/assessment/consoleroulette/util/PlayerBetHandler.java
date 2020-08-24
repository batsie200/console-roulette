package com.rc.assessment.consoleroulette.util;

import com.rc.assessment.consoleroulette.model.Bet;
import com.rc.assessment.consoleroulette.model.PlayerBet;
import com.rc.assessment.consoleroulette.model.Type;
import com.rc.assessment.consoleroulette.repository.PlayerRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PlayerBetHandler {

    private String oddNumberRegex = "ODD";
    private String evenNumberRegex = "EVEN";

    private static final String BET_NUMBER_REGEX = "[1-9]|1[0-9]|2[0-9]|3[0-6]";
    private static final Pattern BET_NUMBER_PATTERN = Pattern.compile(BET_NUMBER_REGEX);
    private static final Pattern PLAYER_BET_PATTERN =
            Pattern.compile(String.format("(\\w+)\\s(%s|%s|%s)\\s(\\d+|\\d+\\.\\d+)", BET_NUMBER_REGEX, oddNumberRegex, evenNumberRegex));

    private final PlayerRepository playerRepository;

    public PlayerBetHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<PlayerBet> createPlayerBet(String input) {
        return Optional.ofNullable(input).flatMap(val -> {
            Matcher playerBetMatcher = PLAYER_BET_PATTERN.matcher(val);

            if (playerBetMatcher.matches()) {
                String name = playerBetMatcher.group(1);
                return Optional.of(name).flatMap(this::findPlayer).map(player -> new PlayerBet(player, crateBet(playerBetMatcher)));
            } else {
                return Optional.empty();
            }
        });
    }

    private Bet crateBet(Matcher playerBetMatcher) {
        String betGroup = playerBetMatcher.group(2);
        String betAmountGroup = playerBetMatcher.group(3);

        Optional<Integer> betNumber = parseBetNumber(betGroup);
        Type betType = parseBetType(betGroup);
        BigDecimal betAmount = new BigDecimal(betAmountGroup);

        return new Bet(betNumber, betAmount, betType);
    }


}
