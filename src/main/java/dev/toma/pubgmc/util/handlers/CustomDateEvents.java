package dev.toma.pubgmc.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;

import java.time.LocalDate;

public class CustomDateEvents {
    private static LocalDate date;

    public static void handleDates(EntityPlayer loggedPlayer) {
        date = LocalDate.now();

        if (isChristmastTime()) {
            message(loggedPlayer, TextFormatting.AQUA + "  * * * * * * * * * * * *  ", "From Toma, PUBGMC creator");
            message(loggedPlayer, TextFormatting.AQUA + "* * * Merry Christmas * * *", "From Toma, PUBGMC creator");
            message(loggedPlayer, TextFormatting.AQUA + "  * * * * * * * * * * * *  ", "From Toma, PUBGMC creator");
        } else if (isNewYear()) {
            message(loggedPlayer, TextFormatting.YELLOW + "[" + TextFormatting.GREEN + date.getYear() + TextFormatting.YELLOW + "] !!HAPPY NEW YEAR!! [" + TextFormatting.GREEN + date.getYear() + TextFormatting.YELLOW + "]");
        } else if (is1stApril()) {
            message(loggedPlayer, TextFormatting.RED + "It's april fools time! Don't believe anything, especially on internet!");
        } else if (isPUBGMCAnniversary()) {
            int year = date.getYear() - 2018;
            String s = year == 1 ? "year" : "years";
            message(loggedPlayer, TextFormatting.GOLD + "PUBGMC is now " + TextFormatting.ITALIC + year + TextFormatting.RESET + " " + TextFormatting.GOLD + s + " old! Thank you for your support! -Toma");
        }
    }

    private static boolean isChristmastTime() {
        int[] days = new int[]{24, 25, 26};
        return isDate(days, 12);
    }

    private static boolean isNewYear() {
        return isDate(1, 1);
    }

    private static boolean is1stApril() {
        return isDate(1, 4);
    }

    private static boolean isPUBGMCAnniversary() {
        return isDate(29, 6);
    }

    private static boolean isDate(int day, int month) {
        return date.getDayOfMonth() == day && date.getMonthValue() == month;
    }

    private static boolean isDate(int[] days, int month) {
        for (int day : days) {
            if (isDate(day, month)) {
                return true;
            }
        }
        return false;
    }

    private static void message(EntityPlayer p, String message) {
        p.sendMessage(new TextComponentString(message));
    }

    private static void message(EntityPlayer p, String message, String hoverText) {
        TextComponentString s = new TextComponentString(message);
        s.setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(hoverText))));
        p.sendMessage(s);
    }
}
