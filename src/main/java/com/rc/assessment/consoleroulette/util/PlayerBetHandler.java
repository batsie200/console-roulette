package com.rc.assessment.consoleroulette.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

import static com.rc.assessment.consoleroulette.model.Type.*;

@Component
public class PlayerBetHandler {

    @Value("${}")
    private double thirtySixFactor;
    @Value("${}")
    private double twoFactor;

    private BigDecimal betAmount;

    public Optional<BigDecimal> getWinnings(int number) {
        switch (type) {
            case NUMBER:
                return multiplyWinnings(betNumber.filter(n -> n == number).map(n -> betAmount), thirtySixFactor);
            case ODD:
                return multiplyWinnings(Optional.of(betAmount).filter(amount -> number % 2 != 0), BigDecimal.valueOf(twoFactor));
            case EVEN:
                return multiplyWinnings(Optional.of(betAmount).filter(amount -> number % 2 == 0), BigDecimal.valueOf(twoFactor));
            default:
                return Optional.empty();
        }
    }

    private Optional<BigDecimal> multiplyWinnings(Optional<BigDecimal> winnings, BigDecimal multiplier) {
        return winnings.map(amount -> amount.multiply(multiplier));
    }
}
