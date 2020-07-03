package net.nyrheim.penandpaper.dice;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.bukkit.ChatColor.*;

public final class Roll {

    private final List<RollPart> parts;

    public interface RollPart {
        RollPartResult determine();
    }

    public static final class Die implements RollPart {

        private final Random random = new Random();
        private final int rolls;
        private final int sides;
        private final int multiplier;

        public Die(int rolls, int sides, int multiplier) {
            this.rolls = rolls;
            this.sides = sides;
            this.multiplier = multiplier;
        }

        public Die(int rolls, int sides) {
            this(rolls, sides, 1);
        }

        public RollPartResult determine() {
            List<Integer> results = new ArrayList<>(rolls);
            for (int i = 0; i < rolls; i++) {
                results.add(multiplier * (random.nextInt(sides) + 1));
            }
            return new RollPartResult(
                    this,
                    results
            );
        }

        @Override
        public String toString() {
            return (Math.signum(multiplier) == -1 ? "-" : "") + rolls + "d" + sides;
        }
    }

    public static final class Modifier implements RollPart {

        private final int value;

        public Modifier(int value) {
            this.value = value;
        }

        public RollPartResult determine() {
            List<Integer> results = new ArrayList<>(1);
            results.add(value);
            return new RollPartResult(
                    this,
                    results
            );
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    public Roll(RollPart... parts) {
        this.parts = Arrays.asList(parts);
    }

    public List<RollPartResult> roll() {
        return parts.stream()
                .map(RollPart::determine)
                .collect(Collectors.toList());
    }

    public static Roll parse(String rollString) throws NumberFormatException {
        List<RollPart> parts = new ArrayList<>();
        Pattern diePattern = Pattern.compile("[+\\-]\\d*d\\d+");
        String fullRollString = rollString.startsWith("+") ? rollString : "+" + rollString;
        Matcher dieMatcher = diePattern.matcher(fullRollString);
        while (dieMatcher.find()) {
            String dieRollString = dieMatcher.group();
            int multiplier = dieRollString.startsWith("-") ? -1 : 1;
            String[] rollSections = dieRollString.split("d");
            String diceAmountString = rollSections[0].substring(1);
            int dieFaces = Integer.parseInt(rollSections[1]);
            int rollCount = diceAmountString.isEmpty() ? 1 : Integer.parseInt(diceAmountString);
            parts.add(new Die(rollCount, dieFaces, multiplier));
        }
        String rollStringWithoutDice = fullRollString.replaceAll("[+\\-]\\d*d\\d+", "");
        Pattern literalPattern = Pattern.compile("([+\\-]\\d+)(?!d)");
        Matcher literalMatcher = literalPattern.matcher(rollStringWithoutDice);
        while (literalMatcher.find()) {
            int amount = Integer.parseInt(literalMatcher.group(1));
            parts.add(new Modifier(amount));
        }
        return new Roll(parts.toArray(new RollPart[0]));
    }

    @Override
    public String toString() {
        String roll = parts.stream()
                .map(RollPart::toString)
                .reduce("", (a, b) -> a + (b.startsWith("-") ? "" : "+") + b);
        if (roll.startsWith("+"))
            return roll.substring(1);
        else
            return roll;
    }

    public String toDisplayString() {
        String roll = parts.stream()
                .map(rollPart -> {
                    if (rollPart instanceof Die) {
                        return AQUA + rollPart.toString() + WHITE;
                    } else if (rollPart instanceof Modifier) {
                        return YELLOW + rollPart.toString() + WHITE;
                    } else {
                        return rollPart.toString();
                    }
                })
                .reduce("", (a, b) -> a + (ChatColor.stripColor(b).startsWith("-") ? "" : "+") + b);
        if (ChatColor.stripColor(roll).startsWith("+")) {
            return roll.replaceFirst("\\+", "");
        } else {
            return roll;
        }
    }

}
