package dev.toma.pubgmc.api.data;

public interface Recreatable {

    void recreateData();

    DataVersion getVersion();
}
