package com.javarush.island.lazarev.entities;

public enum TileIcon {
    GRASS("🌿"),
    WOODS("🌳"),
    BEAR("🐻"),
    EAGLE("🦅"),
    FOX("🦊"),
    TIGER("🐅"),
    WOLF("🐺"),
    RABBIT("🐇"),
    MOUSE("🐁"),
    DEER("🦌"),
    BOAR("🐗"),
    HARE("🐇"),
    DUCK("🦆"),
    CATERPILLAR("🐛"),
    BEE("🐝"),
    SHEEP("🐑"),
    BEAVER("🦫");

    private final String icon;

    TileIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}