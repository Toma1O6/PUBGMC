package dev.toma.pubgmc.client.gui.animator;

public interface IPopupHandler {

    void sendError(String error);

    void sendWarning(String warning);

    void sendText(String text);
}
