package dev.toma.pubgmc.client.gui.animator;

public interface IPopupHandler {

    void sendError(String error, Object... objects);

    void sendWarning(String warning, Object... objects);

    void sendText(String text, Object... objects);
}
