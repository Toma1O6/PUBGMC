package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.DevUtil;

public final class AnimationElement {

    private static int id = 0;
    private static AnimationElement[] elements = new AnimationElement[8];
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
