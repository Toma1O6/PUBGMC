package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.DevUtil;

public final class AnimationElement {

    private static AnimationElement[] elements = new AnimationElement[9];
    private static int id = 0;
    public static final AnimationElement ITEM_AND_HANDS = AnimationElement.create("base.hands.item");
    public static final AnimationElement HANDS = AnimationElement.create("base.hands");
    public static final AnimationElement RIGHT_HAND = AnimationElement.create("base.hand.right");
    public static final AnimationElement LEFT_HAND = AnimationElement.create("base.hand.left");
    public static final AnimationElement ITEM = AnimationElement.create("base.item");
    public static final AnimationElement MAGAZINE = AnimationElement.create("dynamic.magazine");
    public static final AnimationElement CHARGING = AnimationElement.create("dynamic.charging");
    public static final AnimationElement HAMMER = AnimationElement.create("dynamic.hammer");
    public static final AnimationElement BULLET = AnimationElement.create("dynamic.bullet");
    final int index = id++;
    final String name;

    public static AnimationElement create(String name) {
        AnimationElement element = new AnimationElement(name);
        int index = element.index;
        if(index >= elements.length)
            elements = DevUtil.expandArray(elements, 1);
        elements[element.index] = element;
        return element;
    }

    public static AnimationElement getElementByID(int ID) {
        return elements[ID];
    }

    public static AnimationElement[] values() {
        return elements;
    }

    private AnimationElement(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimationElement element = (AnimationElement) o;
        return index == element.index;
    }

    @Override
    public int hashCode() {
        return index;
    }
}
