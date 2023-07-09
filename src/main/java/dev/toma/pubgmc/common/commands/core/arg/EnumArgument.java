package dev.toma.pubgmc.common.commands.core.arg;

import dev.toma.pubgmc.common.commands.core.SuggestionProvider;
import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumArgument<E extends Enum<E>> extends ArgumentType<E> {

    private final Class<E> enumType;

    private EnumArgument(Class<E> enumType) {
        super(EnumArgument::parse);
        this.enumType = enumType;
    }

    public static <E extends Enum<E>> EnumArgument<E> enumeration(Class<E> type) {
        return new EnumArgument<>(Objects.requireNonNull(type));
    }

    @Override
    public List<String> suggest(SuggestionProvider.Context context) {
        return transform(enumType).collect(Collectors.toList());
    }

    private static Stream<String> transform(Class<? extends Enum<?>> type) {
        return Arrays.stream(type.getEnumConstants()).map(Enum::name);
    }

    private static <E extends Enum<E>> E parse(ArgumentContext<E> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        String value = reader.read();
        EnumArgument<E> argument = context.castArgument();
        Class<E> type = argument.enumType;
        try {
            return Enum.valueOf(type, value);
        } catch (IllegalArgumentException e) {
            String[] transformed = transform(type).toArray(String[]::new);
            throw new SyntaxErrorException("Unknown value: " + value + ". Must be one of [" + String.join(",", transformed) + "]");
        }
    }
}
