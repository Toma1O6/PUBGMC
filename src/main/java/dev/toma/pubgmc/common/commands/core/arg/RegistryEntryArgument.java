package dev.toma.pubgmc.common.commands.core.arg;

import dev.toma.pubgmc.common.commands.core.SuggestionProvider;
import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;
import java.util.stream.Collectors;

public class RegistryEntryArgument<T extends IForgeRegistryEntry<T>> extends ArgumentType<T> {

    private final IForgeRegistry<T> registry;
    private final List<String> suggestions;

    private RegistryEntryArgument(IForgeRegistry<T> registry) {
        super(RegistryEntryArgument::parse);
        this.registry = registry;
        this.suggestions = registry.getKeys().stream()
                .sorted()
                .map(ResourceLocation::toString)
                .collect(Collectors.toList());
    }

    public static <T extends IForgeRegistryEntry<T>> RegistryEntryArgument<T> registry(IForgeRegistry<T> registry) {
        return new RegistryEntryArgument<>(registry);
    }

    @Override
    public List<String> suggest(SuggestionProvider.Context context) {
        return suggestions;
    }

    private static <T extends IForgeRegistryEntry<T>> T parse(ArgumentContext<T> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        ResourceLocation key = new ResourceLocation(reader.read());
        RegistryEntryArgument<T> argument = context.castArgument();
        IForgeRegistry<T> registry = argument.registry;
        if (!registry.containsKey(key)) {
            throw new SyntaxErrorException("No object with key " + key + " exists in " + registry.getRegistrySuperType().getSimpleName() + " registry");
        }
        return registry.getValue(key);
    }
}
