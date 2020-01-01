package com.toma.pubgmc.content;

import com.toma.pubgmc.Pubgmc;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MapDownloader {

    private static final short BUFFER_SIZE = 8192;
    private final MapData data;
    private final GuiMainMenu.MenuButtonMap buttonMap;
    private Thread thread;

    public MapDownloader(final MapData data, final GuiMainMenu.MenuButtonMap buttonMap) {
        this.data = data;
        this.buttonMap = buttonMap;
        this.download();
        this.thread.start();
    }

    private void download() {
        this.thread = new Thread("Map download") {

            private File downloadedFile;

            @Override
            public void run() {
                MapData data = MapDownloader.this.data;
                MapDownloader.this.buttonMap.updateState(MapButtonState.DOWNLOADING);
                this.downloadAsZIP(data);
                MapDownloader.this.buttonMap.updateState(MapButtonState.EXTRACTING);
                this.extractZIP(data);
                this.deleteZIP(data);
                MapDownloader.this.buttonMap.updateState(MapButtonState.NORMAL);
                buttonMap.getData().isDownloaded = true;
            }

            private void downloadAsZIP(MapData data) {
                try {
                    InputStream stream = new URL("https://www.dropbox.com/s/ilcyxwzhxwjrqq4/Test%20map%203.zip?dl=1").openStream();
                    ReadableByteChannel readableByteChannel = Channels.newChannel(stream);
                    downloadedFile = new File(GuiLoadCommunityContent.contentFolder.getAbsolutePath() + "/" + data.displayName + ".zip");
                    FileOutputStream fileOutputStream = new FileOutputStream(downloadedFile);
                    fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                    stream.close();
                    readableByteChannel.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    MapDownloader.this.buttonMap.updateState(MapButtonState.FAILED);
                    e.printStackTrace();
                }
            }

            @SuppressWarnings("ResultOfMethodCallIgnored")
            private void extractZIP(MapData data) {
                try {
                    File dest = new File(GuiLoadCommunityContent.contentFolder.getAbsolutePath());
                    if(!dest.exists()) {
                        dest.mkdir();
                    }
                    ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(dest.getAbsolutePath() + File.separator + data.displayName + ".zip"));
                    ZipEntry entry = zipInputStream.getNextEntry();
                    while (entry != null) {
                        String filePath = dest.getAbsolutePath() + File.separator + entry.getName();
                        if(!entry.isDirectory()) {
                            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                            byte[] buffer = new byte[4096];
                            int i;
                            while ((i = zipInputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, i);
                            }
                            outputStream.close();
                        } else {
                            new File(filePath).mkdir();
                        }
                        zipInputStream.closeEntry();
                        entry = zipInputStream.getNextEntry();
                    }
                    zipInputStream.close();
                } catch (IOException e) {
                    MapDownloader.this.buttonMap.updateState(MapButtonState.FAILED);
                    e.printStackTrace();
                }
            }

            private void deleteZIP(MapData data) {
                File file = new File(GuiLoadCommunityContent.contentFolder.getAbsolutePath() + File.separator + data.displayName + ".zip");
                if(!file.exists()) {
                    Pubgmc.logger.warn("Cannot delete old zip file, file not found!");
                    return;
                }
                file.delete();
            }
        };
    }
}
