package com.toma.pubgmc.common.commands;

import com.toma.pubgmc.common.blocks.BlockLootSpawner;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IGameData.GameDataProvider;
import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.common.capability.IWorldData.WorldDataProvider;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import com.toma.pubgmc.network.PacketHandler;
import com.toma.pubgmc.network.sp.PacketDisplayLootSetupGui;
import com.toma.pubgmc.network.sp.PacketSyncTileEntity;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.game.loot.ILootSpawner;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CommandLootGenerate extends CommandBase {
    @Override
    public String getName() {
        return "loot";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/loot";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        IWorldData worldData = null;

        if (sender.getEntityWorld().hasCapability(WorldDataProvider.WORLD_DATA, null)) {
            worldData = sender.getEntityWorld().getCapability(WorldDataProvider.WORLD_DATA, null);
        }

        if (args.length == 0) {
            throw new WrongUsageException("You must specify operation. Use /loot help");
        } else if (args.length > 0) {
            executeBasic(server, sender, args, worldData);
        }
    }

    private void executeBasic(MinecraftServer server, ICommandSender sender, String[] args, IWorldData data) {
        World world = sender.getEntityWorld();
        if (args[0].equalsIgnoreCase("help")) {
            String[] messages = {
                    "- generate [Blockpos, range] >> will generate loot with current command settings",
                    "- clear >> clear all loaded loot spawners",
                    "- show >> fills all loaded loot spawners with loot to make it easier for spotting",
                    "- info >> info about current loot setting",
                    "- reset >> reset all current setting to default",
                    "- setup >> here you can setup multiple things for loot generation",
                    "- delete >> delete all loaded loot spawners",
                    "- count >> Total count of all loaded loot spawners"
            };

            for (int i = 0; i < messages.length; i++) {
                sender.sendMessage(new TextComponentString(TextFormatting.GREEN + messages[i]));
            }
        } else if (args[0].equalsIgnoreCase("info")) {
            sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Airdrop Weapons: ") + getTextColorFormatting(data.hasAirdropWeapons())));
            sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Ammo Loot: ") + getTextColorFormatting(data.isAmmoLootEnabled())));
            sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Random Ammo Count: ") + getTextColorFormatting(data.isRandomAmmoCountEnabled())));
            sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Weapons: ") + getColorBasedOnList(data.getWeaponList())));
            sender.sendMessage(new TextComponentString(getDefaultInfoFormat("Chance Multiplier: ") + getNumberFormatting(data.getLootChanceMultiplier()) + "x"));
        } else if (args[0].equalsIgnoreCase("reset")) {
            data.toggleAirdropWeapons(false);
            data.toggleAmmoLoot(true);
            data.toggleRandomAmmoCount(false);
            resetWeapons(data);
            data.setLootChanceMultiplier(1.0D);
            if (shouldSendCommandFeedback(world.getGameRules()))
                sender.sendMessage(new TextComponentString("Reseting all values..."));
        } else if (args[0].equalsIgnoreCase("generate")) {
            IGameData game = world.getCapability(GameDataProvider.GAMEDATA, null);
            int count = 0;
            if (args.length == 5 && isValidNumber(args[1]) && isValidNumber(args[2]) && isValidNumber(args[3]) && isValidNumber(args[4])) {
                BlockPos gen = new BlockPos(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
                int range = Integer.parseInt(args[4]);

                if (world.isBlockLoaded(gen)) {
                    for (TileEntity te : world.loadedTileEntityList) {
                        if (te instanceof ILootSpawner) {
                            BlockPos lootPos = te.getPos();
                            double totalRange = PUBGMCUtil.getDistanceToBlockPos3D(lootPos, gen);

                            if (totalRange <= range) {
                                count++;
                                ((ILootSpawner) te).setGameHash(game.getGameID());
                                ((ILootSpawner) te).onLoaded();
                            }
                        }
                    }
                }
            } else {
                for (TileEntity te : sender.getEntityWorld().loadedTileEntityList) {
                    if (te instanceof ILootSpawner) {
                        count++;
                        ((ILootSpawner) te).setGameHash(game.getGameID());
                        ((ILootSpawner) te).onLoaded();
                    }
                }
            }

            if (shouldSendCommandFeedback(world.getGameRules())) {
                if (!data.getWeaponList().isEmpty() && data.getLootChanceMultiplier() > 0) {
                    if (count == 0)
                        sender.sendMessage(new TextComponentString(TextFormatting.RED + "Couldn't locate any loot spawners, try again."));
                    else
                        sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Generated loot inside " + TextFormatting.YELLOW + count + TextFormatting.GREEN + " loot spawners"));
                } else {
                    sender.sendMessage(new TextComponentString(TextFormatting.RED + "Attempted to generate loot, but weapons are disabled. Execute '/loot reset' to fix that"));
                }
            }
        } else if (args[0].equalsIgnoreCase("clear")) {
            for (TileEntity te : sender.getEntityWorld().loadedTileEntityList) {
                if (te instanceof TileEntityLootGenerator) {
                    ((TileEntityLootGenerator) te).clear();
                    PacketHandler.sendToAllClients(new PacketSyncTileEntity(te.writeToNBT(new NBTTagCompound()), te.getPos()));
                }
            }

            if (shouldSendCommandFeedback(world.getGameRules()))
                sender.sendMessage(new TextComponentString("Cleared loot inside all loaded loot spawners"));
        } else if (args[0].equalsIgnoreCase("show")) {
            int counter = 0;
            ItemStack[] stack = new ItemStack[] {new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 11), new ItemStack(Items.DYE, 1, 10)};
            for (TileEntity te : sender.getEntityWorld().loadedTileEntityList) {
                if (te instanceof TileEntityLootGenerator) {
                    counter++;
                    int tier = world.getBlockState(te.getPos()).getValue(BlockLootSpawner.LOOT);
                    for (int i = 0; i < ((TileEntityLootGenerator) te).getSizeInventory(); i++) {
                        ((TileEntityLootGenerator) te).setInventorySlotContents(i, stack[tier].copy());
                        PacketHandler.sendToAllClients(new PacketSyncTileEntity(te.writeToNBT(new NBTTagCompound()), te.getPos()));
                    }
                }
            }

            if (shouldSendCommandFeedback(world.getGameRules()))
                sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Currently showing " + TextFormatting.YELLOW + counter + TextFormatting.GREEN + " loot spawners"));
        } else if (args[0].equalsIgnoreCase("setup")) {
            if(sender instanceof EntityPlayerMP) {
                PacketHandler.sendToClient(new PacketDisplayLootSetupGui(data.serializeNBT()), (EntityPlayerMP)sender);
            }
        } else if (args[0].equalsIgnoreCase("count")) {
            int total = 0;
            for (TileEntity te : world.loadedTileEntityList) {
                if (te instanceof TileEntityLootGenerator) {
                    total++;
                }
            }
            sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Loaded loot spawners: " + TextFormatting.YELLOW + total + TextFormatting.GREEN + "."));
        } else if (args[0].equalsIgnoreCase("delete")) {
            int count = 0;
            Iterator<TileEntity> iterator = world.loadedTileEntityList.iterator();
            while (iterator.hasNext()) {
                TileEntity tileEntity = iterator.next();
                if(tileEntity instanceof ILootSpawner) {
                    if(((ILootSpawner) tileEntity).generateLootOnCommand()) {
                        iterator.remove();
                        world.destroyBlock(tileEntity.getPos(), false);
                        count++;
                    }
                }
            }

            if (shouldSendCommandFeedback(world.getGameRules())) {
                if (count == 0)
                    sender.sendMessage(new TextComponentString(TextFormatting.RED + "There are no loaded loot spawners!"));
                else
                    sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Removed " + TextFormatting.YELLOW + count + TextFormatting.GREEN + " loot spawners!"));
            }
        } else {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Unknown operation, try /loot help"));
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        if (args.length == 1)
            return getListOfStringsMatchingLastWord(args, "help", "info", "clear", "generate", "setup", "reset", "count", "delete", "show");

        return Collections.EMPTY_LIST;
    }

    private boolean isStringDouble(String text) {
        char[] c = text.toCharArray();
        boolean valid = true;
        boolean alreadyUsedDot = false;
        for (char value : c) {
            if (Character.isDigit(value) || value == '.') {
                if (alreadyUsedDot && value == '.') {
                    valid = false;
                }

                if (value == '.' && !alreadyUsedDot) {
                    alreadyUsedDot = true;
                }
            } else {
                valid = false;
            }
        }

        return valid;
    }

    private boolean isValidNumber(String s) {
        char[] num = s.toCharArray();
        boolean valid = true;
        if (num[0] == '-' || Character.isDigit(num[0])) {
            for (int i = 1; i < num.length; i++) {
                if (!Character.isDigit(num[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetWeapons(IWorldData data) {
        data.resetWeaponLootGeneration();
    }

    private boolean shouldSendCommandFeedback(GameRules rules) {
        return rules.getBoolean("sendCommandFeedback");
    }

    private String getTextColorFormatting(boolean input) {
        return input ? TextFormatting.GREEN + "" + input : TextFormatting.RED + "" + input;
    }

    private String getNumberFormatting(double input) {
        if (input <= 0)
            return TextFormatting.RED + "" + input;
        else if (input > 0 && input < 1.5)
            return TextFormatting.YELLOW + "" + input;
        else if (input >= 1.5 && input < 5)
            return TextFormatting.GREEN + "" + input;
        else
            return TextFormatting.DARK_GREEN + "" + input;
    }

    private String getColorBasedOnList(List<GunType> list) {
        switch (list.size()) {
            case 0:
                return TextFormatting.DARK_RED + "" + list;
            case 1:
                return TextFormatting.RED + "" + list;
            case 2:
                return TextFormatting.GOLD + "" + list;
            case 3:
                return TextFormatting.YELLOW + "" + list;
            case 4:
                return TextFormatting.GREEN + "" + list;
            default:
                return TextFormatting.DARK_GREEN + "" + list;
        }
    }

    private String getDefaultInfoFormat(String textToShow) {
        return TextFormatting.GRAY + textToShow;
    }
}
