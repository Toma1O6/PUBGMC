package dev.toma.pubgmc.api.data;

import java.util.Optional;

public final class DataVersion {

    private final int major;
    private final int minor;

    private DataVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public static DataVersion of(int major, int minor) {
        return new DataVersion(major, minor);
    }

    public static DataVersion parse(String versionString) {
        String[] versions = versionString.split("\\.");
        if (versions.length != 2) {
            return DataVersion.of(0, 0);
        }
        int major = parseInt(versions[0]).orElse(0);
        int minor = parseInt(versions[1]).orElse(0);
        return new DataVersion(major, minor);
    }

    private static Optional<Integer> parseInt(String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public CompareResult compare(DataVersion version) {
        if (major != version.major) {
            return CompareResult.MAJOR_MISMATCH;
        }
        if (minor != version.minor) {
            return CompareResult.MINOR_MISMATCH;
        }
        return CompareResult.SAME;
    }

    @Override
    public String toString() {
        return major + "." + minor;
    }

    public enum CompareResult {
        MAJOR_MISMATCH,
        MINOR_MISMATCH,
        SAME
    }
}
