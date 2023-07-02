package dev.toma.pubgmc.data.loot.processor;

import com.google.gson.*;
import dev.toma.pubgmc.api.game.loot.*;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.data.loot.LootGenerationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AttachmentProcessor implements LootProcessor {

    private final Map<AttachmentType<?>, LootProvider> generators;

    public AttachmentProcessor(Map<AttachmentType<?>, LootProvider> generators) {
        this.generators = generators;
    }

    public AttachmentProcessor(Consumer<Map<AttachmentType<?>, LootProvider>> generator) {
        this(new HashMap<>());
        generator.accept(this.generators);
    }

    @Override
    public void processItems(LootGenerationContext context, List<ItemStack> generated) {
        for (ItemStack stack : generated) {
            if (stack.getItem() instanceof GunBase) {
                GunBase gun = (GunBase) stack.getItem();
                addAttachments(gun, stack, context);
            }
        }
    }

    public void addAttachments(GunBase gun, ItemStack stack, LootGenerationContext context) {
        for (Map.Entry<AttachmentType<?>, LootProvider> entry : generators.entrySet()) {
            AttachmentType<?> attachmentType = entry.getKey();
            LootProvider provider = entry.getValue();
            tryGenerateAttachments(gun, stack, attachmentType, provider, context);
        }
    }

    public void tryGenerateAttachments(GunBase gun, ItemStack gunStack, AttachmentType<?> type, LootProvider provider, LootGenerationContext context) {
        List<ItemStack> possibleAttachments = provider.generateItems(context);
        GunAttachments attachments = gun.getAttachments();
        if (!attachments.supportsType(type))
            return;
        for (ItemStack attachment : possibleAttachments) {
            if (attachment.getItem() instanceof ItemAttachment) {
                ItemAttachment itemAttachment = (ItemAttachment) attachment.getItem();
                if (itemAttachment.getType() != type) {
                    continue;
                }
                if (attachments.supports(itemAttachment)) {
                    attachments.attach(gunStack, itemAttachment);
                    break;
                }
            }
        }
    }

    @Override
    public LootProcessorType<?> getType() {
        return LootProcessors.ATTACHMENT_PROCESSOR;
    }

    public static final class Serializer implements LootProcessorSerializer<AttachmentProcessor> {

        @Override
        public void serialize(JsonObject object, AttachmentProcessor processor) {
            JsonArray array = new JsonArray();
            for (Map.Entry<AttachmentType<?>, LootProvider> entry : processor.generators.entrySet()) {
                JsonObject obj = new JsonObject();
                obj.addProperty("attachmentType", entry.getKey().getName());
                obj.add("attachmentProvider", LootProviderType.serialize(entry.getValue()));
                array.add(obj);
            }
            object.add("attachments", array);
        }

        @Override
        public AttachmentProcessor parse(JsonObject object) throws JsonParseException {
            JsonArray array = JsonUtils.getJsonArray(object, "attachments", new JsonArray());
            Map<AttachmentType<?>, LootProvider> map = new HashMap<>();
            for (JsonElement element : array) {
                JsonObject obj = element.getAsJsonObject();
                String key = JsonUtils.getString(obj, "attachmentType");
                AttachmentType<?> type = getAttachmentTypeByName(key);
                LootProvider provider = LootProviderType.parse(JsonUtils.getJsonObject(obj, "attachmentProvider"));
                map.put(type, provider);
            }
            return new AttachmentProcessor(map);
        }

        public static AttachmentType<?> getAttachmentTypeByName(String name) {
            for (AttachmentType<?> type : AttachmentType.allTypes) {
                if (type.getName().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            throw new JsonSyntaxException("Unknown attachment type: " + name);
        }
    }
}
