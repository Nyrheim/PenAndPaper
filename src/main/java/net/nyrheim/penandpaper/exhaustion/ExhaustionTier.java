package net.nyrheim.penandpaper.exhaustion;

import net.nyrheim.penandpaper.character.PenCharacter;

import java.util.Arrays;

import static org.bukkit.ChatColor.*;

public enum ExhaustionTier {

    TIER_1(1, 0, 20, GREEN + "You feel energised.", GREEN + "%s feels energised."),
    TIER_2(2, 21, 40, DARK_AQUA + "You feel fine.", DARK_AQUA + "%s feels fine."),
    TIER_3(3, 41, 60, YELLOW + "You're starting to tire. Perhaps you should take a break.", YELLOW + "%s is starting to tire. Perhaps they should take a break."),
    TIER_4(4, 61, 80, GRAY + "You're absolutely exhausted, you can't carry on for much longer.", GRAY + "%s is absolutely exhausted, they can't carry on for much longer."),
    TIER_5(5, 81, 99, RED + "You feel like you're about to collapse.", RED + "%s feels like they're about to collapse."),
    TIER_6(6, 100, 100, DARK_RED + "You are physically incapable of moving.", DARK_RED + "%s is physically incapable of moving.");

    private final int tier;
    private final int minExhaustion;
    private final int maxExhaustion;
    private final String messageSelf;
    private final String messageTemplateOther;

    ExhaustionTier(int tier, int minExhaustion, int maxExhaustion, String messageSelf, String messageTemplateOther) {
        this.tier = tier;
        this.minExhaustion = minExhaustion;
        this.maxExhaustion = maxExhaustion;
        this.messageSelf = messageSelf;
        this.messageTemplateOther = messageTemplateOther;
    }

    public int getTier() {
        return tier;
    }

    public int getMinExhaustion() {
        return minExhaustion;
    }

    public int getMaxExhaustion() {
        return maxExhaustion;
    }

    public String getMessageSelf() {
        return messageSelf;
    }

    public String getMessageOther(PenCharacter character) {
        return String.format(messageTemplateOther, character.getName());
    }

    public static ExhaustionTier forExhaustionValue(int exhaustionValue) {
        return Arrays.stream(ExhaustionTier.values())
                .filter(tier -> exhaustionValue >= tier.getMinExhaustion()
                        && exhaustionValue <= tier.getMaxExhaustion())
                .findFirst()
                .orElse(null);
    }
}
