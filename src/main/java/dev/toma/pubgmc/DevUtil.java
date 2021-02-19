package dev.toma.pubgmc;

public class DevUtil {

    public static boolean isDev() {
        return Pubgmc.isDevEnvironment;
    }

    public static <E> boolean containsD(E[] group, E element) {
        for(E e : group) {
            if(element == e) {
                return true;
            }
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
