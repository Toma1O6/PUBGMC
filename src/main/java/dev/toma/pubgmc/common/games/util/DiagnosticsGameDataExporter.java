package dev.toma.pubgmc.common.games.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.DiagnosticsDumpSupport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DiagnosticsGameDataExporter {

    private static final File DUMPS_DIR = new File("./pubgmc/diagnostics");
    private static final Gson GSON;

    public static File export(String name, DiagnosticsDumpSupport dumpImpl) {
        if (!DUMPS_DIR.exists()) {
            DUMPS_DIR.mkdirs();
        }
        File exportFile = new File(DUMPS_DIR, name + ".json");
        try {
            JsonElement jsonDump = dumpImpl.createDump(GSON);
            try (FileWriter writer = new FileWriter(exportFile)) {
                writer.write(GSON.toJson(jsonDump));
            }
        } catch (Exception e) {
            Pubgmc.logger.error("Failed to create diagnostics dump", e);
            throw new RuntimeException("Failed to create dump file", e);
        }
        return exportFile;
    }

    private static final TypeAdapter<ZonedDateTime> ZONED_DATE_TIME_ADAPTER = new TypeAdapter<ZonedDateTime>() {
        @Override
        public void write(JsonWriter out, ZonedDateTime value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.format(DateTimeFormatter.ISO_DATE_TIME));
            }
        }

        @Override
        public ZonedDateTime read(JsonReader in) throws IOException {
            String value = in.nextString();
            return ZonedDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
        }
    };

    static {
        GSON = new GsonBuilder()
                .setPrettyPrinting().disableHtmlEscaping()
                .registerTypeAdapter(ZonedDateTime.class, ZONED_DATE_TIME_ADAPTER)
                .create();
    }
}
