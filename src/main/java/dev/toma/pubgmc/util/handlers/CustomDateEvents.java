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
            message(loggedPlayer, TextFormatting.AQUA + "[PUBGMC] Merry Christmas", "From PUBGMC team");
        } else if (isNewYear()) {
            message(loggedPlayer, TextFormatting.YELLOW + "[" + TextFormatting.GREEN + "PUBGMC" + TextFormatting.YELLOW + "] [" + TextFormatting.GREEN + date.getYear() + TextFormatting.YELLOW + "] !! HAPPY NEW YEAR !! [" + TextFormatting.GREEN + date.getYear() + TextFormatting.YELLOW + "]");
        } else if (is1stApril()) {
            message(loggedPlayer, TextFormatting.RED + "[PUBGMC] It's april fools time! You know what that means! Beware");
        } else if (isPUBGMCAnniversary()) {
            int year = date.getYear() - 2018;
            String s = year == 1 ? "year" : "years";
            message(loggedPlayer, TextFormatting.GOLD + "PUBGMC is now " + TextFormatting.ITALIC + year + TextFormatting.RESET + " " + TextFormatting.GOLD + s + " old! Thank you for your support! -Toma");
        }
    }

    private static boolean isChristmastTime() {
        return isDate(24, 26, 12);
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
        return isDate(day, day, month);
    }

    private static boolean isDate(int intervalStart, int intervalEnd, int month) {
        return date.getMonthValue() == month && date.getDayOfMonth() >= intervalStart && date.getDayOfMonth() <= intervalEnd;
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
