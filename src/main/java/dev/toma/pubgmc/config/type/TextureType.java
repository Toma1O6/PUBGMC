package dev.toma.pubgmc.config.type;

import dev.toma.configuration.api.client.ComponentFactory;
import dev.toma.configuration.api.client.component.ArrayComponent;
import dev.toma.configuration.api.client.component.PlainTextComponent;
import dev.toma.configuration.api.type.FixedCollectionType;
import dev.toma.configuration.api.util.Nameable;
import dev.toma.pubgmc.config.client.component.TextureComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Supplier;

public class TextureType extends FixedCollectionType<TextureType.Entry> {

    final Supplier<Integer> colorSupplier;

    public TextureType(String name, Entry value, Entry[] values, Supplier<Integer> colorSupplier, String... desc) {
        super(name, value, values, desc);
        this.colorSupplier = colorSupplier;
    }

    public int getColorForRender() {
        return colorSupplier.get();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ComponentFactory getComponentFactory() {
        return (screen, t, x, y, width, height) -> {
            TextureType type = (TextureType) t;
            String key = type.getId();
            if (key.isEmpty()) {
                screen.addComponent(new TextureComponent(type, x, y, 20, height));
                screen.addComponent(new ArrayComponent<>(type, x + 20, y, width - 20, height));
            } else {
                int nameEnd = width / 2;
                int typeWidth = width - nameEnd;
                screen.addComponent(new PlainTextComponent(x, y, nameEnd, height, screen.getTextColor(), type.getId()));
                screen.addComponent(new TextureComponent(type, x + nameEnd - 19, y, 20, height));
                screen.addComponent(new ArrayComponent<>(type, x + nameEnd, y, typeWidth, height));
            }
        };
    }

    public static class Entry implements Nameable {

        final ResourceLocation location;
        final String name;

        public Entry(ResourceLocation location, String name) {
            this.location = location;
            this.name = name;
        }

        public ResourceLocation getResource() {
            return location;
        }

        @Override
        public String getUnformattedName() {
            return location.toString();
        }

        @Override
        public String getFormattedName() {
            return name;
        }
    }
}
