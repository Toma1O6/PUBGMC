package dev.toma.pubgmc.api.game.util.message;

import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public abstract class AbstractDeathMessage implements DeathMessage {

    private final ITextComponent tool;
    private MessageRelation relation = MessageRelation.UNSET;
    private final List<ITextComponent> additions = new ArrayList<>();
    private final Set<UUID> affectedEntities = new LinkedHashSet<>();

    public AbstractDeathMessage(ITextComponent tool) {
        this.tool = tool;
    }

    @Override
    public void setRelation(MessageRelation relation) {
        this.relation = relation;
    }

    @Override
    public MessageRelation getRelation() {
        return relation;
    }

    @Override
    public void addAttribute(ITextComponent message) {
        this.additions.add(message);
    }

    @Override
    public void addAffectedEntities(UUID... uuids) {
        this.affectedEntities.addAll(Arrays.asList(uuids));
    }

    @Override
    public Set<UUID> getAffectedEntities() {
        return affectedEntities;
    }

    public ITextComponent getTool() {
        return tool;
    }

    public void serializeCommon(NBTTagCompound to) {
        to.setInteger("relations", this.relation.ordinal());
        to.setTag("additions", SerializationHelper.collectionToNbt(this.additions, comp -> new NBTTagString(ITextComponent.Serializer.componentToJson(comp))));
        to.setTag("entities", SerializationHelper.collectionToNbt(this.affectedEntities, uuid -> new NBTTagString(uuid.toString())));
    }

    public void deserializeCommon(NBTTagCompound from) {
        this.relation = MessageRelation.values()[from.getInteger("relations")];
        SerializationHelper.collectionFromNbt(this.additions, from.getTagList("additions", Constants.NBT.TAG_STRING), base -> ITextComponent.Serializer.jsonToComponent(((NBTTagString) base).getString()));
        SerializationHelper.collectionFromNbt(this.affectedEntities, from.getTagList("entities", Constants.NBT.TAG_STRING), base -> UUID.fromString(((NBTTagString) base).getString()));
    }

    protected ITextComponent composeTool() {
        ITextComponent root = new TextComponentString("");
        for (ITextComponent component : additions) {
            root = root.appendSibling(component);
        }
        return root.appendSibling(tool);
    }
}
