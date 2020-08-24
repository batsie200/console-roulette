package com.rc.assessment.consoleroulette.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
public class Bet {

    
    private final Optional<Integer> betNumber;
    private final BigDecimal betAmount;
    private final Type type;
    private final AtomicReference<Optional<BigDecimal>> winnings = new AtomicReference<>(Optional.empty());
    private final AtomicReference<Optional<Outcome>> outcome = new AtomicReference<>(Optional.empty());

    public Bet(Optional<Integer> betNumber, BigDecimal betAmount, Type type) {
        this.betNumber = betNumber;
        this.betAmount = betAmount;
        this.type = type;
    }
}
