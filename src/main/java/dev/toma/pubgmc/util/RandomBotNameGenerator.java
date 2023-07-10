package dev.toma.pubgmc.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Random;

public class RandomBotNameGenerator {

    private static final Random RANDOM = new Random();
    private static final ITextComponent BOT_PREFIX = new TextComponentTranslation("label.pubgmc.bot.prefix");
    private static final String[] NAMES = {
            "Ted", "Slade", "Glenn", "Selene", "Ryker",
            "Xavier", "Shade", "Cecil", "Aedan", "Deri",
            "Ewen", "Kenley", "Manawydan", "Taryn", "Smith",
            "Anselm", "Merton", "Everest", "Reynald", "Hickory",
            "Rigby", "Darren", "Dave", "Jerry", "Geralt",
            "Digby", "Chris", "Royce", "Macy", "Tyron",
            "Denver", "Tucker", "Seton", "Garret", "Arnold",
            "Ramsay", "Tyler", "Xander", "Nolan", "Branson",
            "Jacob", "Steve", "Gabriel", "Diego", "Scott",
            "Kane", "Mike", "Tim", "Joe", "Shaun"
    };

    public static ITextComponent generateBotName() {
        return new TextComponentString(BOT_PREFIX.getFormattedText() + " " + NAMES[RANDOM.nextInt(NAMES.length)]);
    }

    static {
        Style style = BOT_PREFIX.getStyle();
        style.setItalic(true);
    }
}
