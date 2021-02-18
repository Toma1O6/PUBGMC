package dev.toma.pubgmc.common.items.guns.attachments;

public interface IAttachment {
    Type getType();

    enum Type {
        BARREL("barrel"),
        GRIP("grip"),
        MAGAZINE("magazine"),
        STOCK("stock"),
        SCOPE("scope");

        String nbtName;

        Type(String nbtTagName) {
            this.nbtName = nbtTagName;
        }

        public String getName() {
            return name().toLowerCase();
        }
    }
}
