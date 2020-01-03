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
    private boolean isDownloading = false;

    public MapDownloader(final MapData data, final GuiMainMenu.MenuButtonMap buttonMap) {
        this.data = data;
        this.buttonMap = buttonMap;
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public void download() {
        if(thread == null) throw new NullPointerException("You must first call the #initThread method!");
        this.isDownloading = true;
        this.thread.start();
    }

    public void initThread() {
        this.thread = new Thread("Map download") {

            private File downloadedFile;

            @Override
            public void run() {
                try {
                    MapData data = MapDownloader.this.data;
                    MapDownloader.this.buttonMap.updateState(MapButtonState.DOWNLOADING);
                    this.downloadAsZIP(data);
                    MapDownloader.this.buttonMap.updateState(MapButtonState.EXTRACTING);
                    this.extractZIP(data);
                    this.deleteZIP(data);
                    MapDownloader.this.buttonMap.updateState(MapButtonState.NORMAL);
                    buttonMap.getData().isDownloaded = true;
                    finishDownload();
                } catch (IOException e) {
                    MapDownloader.this.buttonMap.updateState(MapButtonState.FAILED);
                    e.printStackTrace();
                }
            }

            private void downloadAsZIP(MapData data) throws IOException {
                InputStream stream = new URL(data.downloadLink).openStream();
                ReadableByteChannel readableByteChannel = Channels.newChannel(stream);
                downloadedFile = new File(GuiLoadCommunityContent.contentFolder.getAbsolutePath() + "/" + data.displayName + ".zip");
                FileOutputStream fileOutputStream = new FileOutputStream(downloadedFile);
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                stream.close();
                readableByteChannel.close();
                fileOutputStream.close();
            }

            @SuppressWarnings("ResultOfMethodCallIgnored")
            private void extractZIP(MapData data) throws IOException {
                File dest = new File(GuiLoadCommunityContent.contentFolder.getAbsolutePath());
                if(!dest.exists()) {
                    dest.mkdir();
                }
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(dest.getAbsolutePath() + File.separator + data.displayName + ".zip"));
                ZipEntry entry = zipInputStream.getNextEntry();
                String old = null;
                String new_ = data.displayName;
                while (entry != null) {
                    String fileName = entry.getName();
                    if(old == null) {
                        // make sure files are named properly
                        old = fileName.replace("/", "");
                    }
                    fileName = fileName.replace(old, new_);
                    String filePath = dest.getAbsolutePath() + File.separator + fileName;
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

    private synchronized void finishDownload() {
        this.isDownloading = false;
        this.buttonMap.onDownloadFinished();
    }
}
