package dev.toma.pubgmc;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.function.BiPredicate;

public class DevUtil {

    private static final DecimalFormat _FFF = new DecimalFormat("#.###");
    private static final DecimalFormat _FF = new DecimalFormat("#.##");

    public static String formatToThreeDecimals(Number number) {
        return _FFF.format(number.doubleValue());
    }

    public static String formatToTwoDecimals(Number number) {
        return _FF.format(number.doubleValue());
    }

    public static boolean isDev() {
        return Pubgmc.isDevEnvironment;
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
}
