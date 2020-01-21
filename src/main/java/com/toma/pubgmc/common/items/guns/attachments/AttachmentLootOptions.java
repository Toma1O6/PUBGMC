package com.toma.pubgmc.common.items.guns.attachments;

public class AttachmentLootOptions {

    public static AttachmentLootOptions from(int rarity) {
        return from(rarity, false, false);
    }

    public static AttachmentLootOptions from(int rarity, boolean spawnsInAirdrop) {
        return from(rarity, spawnsInAirdrop, false);
    }

    public static AttachmentLootOptions from(int rarity, boolean spawnsInAirdrop, boolean airdropOnly) {
        return new AttachmentLootOptions(rarity, spawnsInAirdrop, airdropOnly);
    }

    private int rarity;
    private boolean spawnsInAirdrop;
    private boolean airdropOnly;

    private AttachmentLootOptions(int rarity, boolean spawnsInAirdrop, boolean airdropOnly) {
        this.rarity = rarity;
        this.spawnsInAirdrop = spawnsInAirdrop;
        this.airdropOnly = airdropOnly;
    }

    public int getRarity() {
        return rarity;
    }

    public boolean doesSpawnsInAirdrop() {
        return spawnsInAirdrop;
    }

    public boolean isAirdropOnly() {
        return airdropOnly;
    }
}
