package dev.toma.pubgmc;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class DevUtil {

    public static final DecimalFormat _FFF = new DecimalFormat("#.###");
    public static final DecimalFormat _FF = new DecimalFormat("#.##");

    public static String formatToThreeDecimals(Number number) {
        return _FFF.format(number.doubleValue());
    }

    public static String formatToTwoDecimals(Number number) {
        return _FF.format(number.doubleValue());
    }

    public static boolean isDev() {
        return Pubgmc.isDevEnvironment;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] expandArray(T[] array, int addedLength) {
        if(addedLength <= 0)
            throw new IllegalArgumentException("Number must be bigger than 0");
        T[] expanded = (T[]) new Object[array.length + addedLength];
        System.arraycopy(array, 0, expanded, 0, array.length);
        return expanded;
    }

    public static <T> T getPrevious(List<T> list, int index, T fallback) {
        int prev = index - 1;
        if(prev < 0)
            return fallback;
        return list.get(prev);
    }

    public static <T> T make(T t, Consumer<T> consumer) {
        if(t != null)
            consumer.accept(t);
        return t;
    }

    public static <E> boolean contains(E[] group, E element) {
        for(E e : group) {
            if(element == e) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(T t, Collection<T> collection) {
        return contains(t, collection, (t1, t2) -> t1 == t2);
    }

    public static <T, U> boolean contains(T t, Collection<U> collection, BiPredicate<T, U> test) {
        for (U u : collection) {
            if(test.test(t, u))
                return true;
        }
        return false;
    }

    public static int wrap(int i, int min, int max) {
        return Math.min(max, Math.max(min, i));
    }

    public static float wrap(float f, float min, float max) {
        return Math.min(max, Math.max(min, f));
    }

    public static double wrap(double d, double min, double max) {
        return Math.min(max, Math.max(min, d));
    }

    public static float lerp(float actual, float previous, float partial) {
        return previous + (actual - previous) * partial;
    }

    public static int getItemCount(Item item, IInventory inventory) {
        int total = 0;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if(stack.getItem() == item) {
                total += stack.getCount();
            }
        }
        return total;
    }
}
