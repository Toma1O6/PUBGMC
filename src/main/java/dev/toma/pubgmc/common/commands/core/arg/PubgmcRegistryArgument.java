package dev.toma.pubgmc.common.commands.core.arg;

import dev.toma.pubgmc.api.PubgmcRegistry;
import dev.toma.pubgmc.api.util.RegistryObject;
import dev.toma.pubgmc.common.commands.core.SuggestionProvider;
import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PubgmcRegistryArgument<V extends RegistryObject> extends ArgumentType<V> {

    private final PubgmcRegistry<V> registry;
    private final List<String> keys;

    private PubgmcRegistryArgument(PubgmcRegistry<V> registry) {
        super(PubgmcRegistryArgument::parse);
        this.registry = registry;
        this.keys = registry.getKeys().stream()
                .map(ResourceLocation::toString)
                .collect(Collectors.toList());
    }

    public static <V extends RegistryObject> PubgmcRegistryArgument<V> registry(PubgmcRegistry<V> registry) {
        return new PubgmcRegistryArgument<>(Objects.requireNonNull(registry));
    }

    @Override
    public List<String> suggest(SuggestionProvider.Context context) {
        return keys;
    }

    private static <V extends RegistryObject> V parse(ArgumentContext<V> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        ResourceLocation location;
        try {
            location = new ResourceLocation(reader.read());
        } catch (Exception e) {
            throw new SyntaxErrorException("Registry key parsing failed", e);
        }
        PubgmcRegistryArgument<V> argument = context.castArgument();
        V value = argument.registry.getValue(location);
        if (value == null) {
            throw new WrongUsageException("Unknown value: " + location);
        }
        return value;
    }
}
