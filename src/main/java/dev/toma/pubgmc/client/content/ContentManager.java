package dev.toma.pubgmc.client.content;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.config.ConfigPMC;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ContentManager {

    private static final Logger log = LogManager.getLogger("Content");
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ContentResult.class, new ContentResult.Deserializer())
            .registerTypeAdapter(MenuDisplayContent.class, new MenuDisplayContent.Deserializer())
            .create();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setName("Content Thread");
        return thread;
    });
    private ContentResult cache;
    private URL url;
    private Future<?> task;
    boolean failed;
    boolean updatePeriodically;

    public void initialize() {
        log.info("Initializing content manager");
        try {
            url = new URL("https://raw.githubusercontent.com/Toma1O6/PUBGMC/master/content.json");
        } catch (MalformedURLException ex) {
            throw new ReportedException(CrashReport.makeCrashReport(ex, "Invalid content URL. Contact mod author"));
        }
        MenuDisplayContent.registerDeserializers();
        start();
    }

    public ContentResult getCachedResult() {
        return cache;
    }

    synchronized void start() {
        if(updatePeriodically = ConfigPMC.client.content.periodicUpdates.get()) {
            this.schedulePeriodicUpdates();
        } else {
            task = executorService.submit(this::loadContent);
        }
    }

    void loadContent() {
        try {
            JsonParser parser = new JsonParser();
            ContentResult result = gson.fromJson(parser.parse(this.getRawContent()), ContentResult.class);
            if(cache == null) {
                cacheResult(result);
                log.info("Content loaded");
                if(!updatePeriodically) {
                    executorService.shutdown();
                    log.info("Shutting down Content Thread because periodic updates are disabled");
                }
            } else {
                cache.updateModifiable(result);
            }
            if(failed) {
                failed = false;
                if(task != null)
                    task.cancel(true);
                if(updatePeriodically) {
                    schedulePeriodicUpdates();
                } else task = executorService.submit(this::loadContent);
                log.info("Data parsing successful");
            }
        } catch (Exception ex) {
            log.fatal("Couldn't parse received data: {}", ex.toString());
            if(!failed) {
                failed = true;
                if(task != null)
                    task.cancel(true);
                task = executorService.scheduleAtFixedRate(this::loadContent, 30, 30, TimeUnit.SECONDS);
                log.info("Scheduling new parse attempts in 30s until successful");
            }
        }
    }

    String getRawContent() throws Exception {
        boolean forceUrlParse = false;
        BufferedReader reader;
        if(Pubgmc.isDevEnvironment && !forceUrlParse) {
            String path = "./content.json";
            File file = new File(path);
            if(!file.exists())
                throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
            reader = new BufferedReader(new FileReader(file));
        } else {
            URLConnection connection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        StringBuilder builder = new StringBuilder();
        String input;
        while ((input = reader.readLine()) != null) {
            builder.append(input);
        }
        reader.close();
        return builder.toString();
    }

    synchronized void cacheResult(ContentResult result) {
        cache = result;
    }

    void schedulePeriodicUpdates() {
        int period = ConfigPMC.client.content.period.get();
        TimeUnit unit = ConfigPMC.client.content.timeUnit.get().get();
        task = executorService.scheduleAtFixedRate(this::loadContent, 0L, period, unit);
    }
}
