package dev.toma.pubgmc.api.game;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public interface DiagnosticsDumpSupport {

    JsonElement createDump(Gson gson);
}
