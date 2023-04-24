package dev.toma.pubgmc.common.commands.core.arg;

import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public class NBTArgument extends ArgumentType<NBTTagCompound> {

    private static final NBTArgument INSTANCE = new NBTArgument();

    private NBTArgument() {
        super(NBTArgument::parse);
    }

    public static NBTArgument nbt() {
        return INSTANCE;
    }

    private static NBTTagCompound parse(ArgumentContext<NBTTagCompound> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        String rawNbt = reader.read();
        try {
            return JsonToNBT.getTagFromJson(rawNbt);
        } catch (NBTException e) {
            throw new SyntaxErrorException(e.getMessage());
        }
    }
}
