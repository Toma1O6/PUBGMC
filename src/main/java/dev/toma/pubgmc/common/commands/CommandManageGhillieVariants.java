package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.common.capability.IWorldData;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CommandManageGhillieVariants extends CommandBase {

    private final String[] args = {"add","remove","list"};

    @Override
    public String getName() {
        return "ghillie";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/ghillie [add, remove, list] <color>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        IWorldData data = world.getCapability(IWorldData.WorldDataProvider.WORLD_DATA, null);
        if(data == null) throw new CommandException("Unable to get world capability data!");
        if(args.length == 0) throw new CommandException("Unknown operation. Use /ghillie <add,remove,list>");
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase(this.args[0])) {
                if(args.length < 2) throw new WrongUsageException("You must define color");
                int color;
                try {
                    color = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new WrongUsageException("This is not a valid number: " + args[1] + "!");
                }
                if(PUBGMCUtil.contains(color, data.getGhillieSuitsColorVariants().toArray(new Integer[0]))) {
                    throw new CommandException("This color is already registered!");
                }
                data.addColorVariant(color);
                sendFeedback(sender, "Successfully added new color variant");
            } else if(args[0].equalsIgnoreCase(this.args[1])) {
                if(args.length < 2) throw new WrongUsageException("You must define color index!");
                int index;
                try {
                    index = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new WrongUsageException("This is not a valid number: " + args[1] + "!");
                }
                index = Math.abs(index);
                if(index >= data.getGhillieSuitsColorVariants().size()) throw new CommandException("Invalid index, max: " + (data.getGhillieSuitsColorVariants().size()-1));
                int removed = data.getGhillieSuitsColorVariants().remove(index);
                sendFeedback(sender, "You have deleted the color " + removed);
            } else if(args[0].equalsIgnoreCase(this.args[2])) {
                List<Integer> list = data.getGhillieSuitsColorVariants();
                sender.sendMessage(new TextComponentString("Registered colors: "));
                for(int i = 0; i < list.size(); i++) {
                    TextComponentString textComponentString = new TextComponentString("- " + list.get(i));
                    sender.sendMessage(textComponentString);
                }
            } else throw new CommandException("Unknown command operation! Try /ghillie <add,remove,list>");
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    public void sendFeedback(ICommandSender sender, String feedback) {
        if(sender.sendCommandFeedback()) sender.sendMessage(new TextComponentString(feedback));
    }
}
