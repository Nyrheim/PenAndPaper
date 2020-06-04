package net.nyrheim.penandpaper.dice;

import net.nyrheim.penandpaper.dice.Roll.RollPart;

import java.util.List;

public final class RollPartResult {

    private final RollPart rollPart;
    private final List<Integer> individualResults;
    private final int result;

    public RollPartResult(RollPart rollPart, List<Integer> results) {
        this.rollPart = rollPart;
        this.individualResults = results;
        this.result = results.stream().reduce(0, Integer::sum);
    }
}
