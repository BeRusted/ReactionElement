package com.BeRusted.ReactionElement.element;

import net.minecraft.util.text.TextFormatting;

public enum ElementDepot {
    FIRE("fire", TextFormatting.RED),
    ICE("ice", TextFormatting.AQUA),
    DARK("dark", TextFormatting.DARK_PURPLE),
    LIGHTNING("lightning", TextFormatting.LIGHT_PURPLE),
    WATER("water", TextFormatting.BLUE),
    HEALTH("health", TextFormatting.GREEN),
    LIGHT("light", TextFormatting.YELLOW),
    DEFAULT("default", TextFormatting.GRAY); // 兜底颜色,不然没有枚举到就要爆游戏 //当为 default 时,不会有元素显示


    private final String name;
    private final TextFormatting color;

    ElementDepot(String name, TextFormatting color){
        this.name = name;
        this.color = color;
    }

    public String getName(){
        return name;
    }

    public TextFormatting getColor() {
        return color;
    }

    // 获取颜色
    public static ElementDepot fromString(String elementName) {
        for (ElementDepot e : values()) {
            if (e.getName().equalsIgnoreCase(elementName)) {
                return e;
            }
        }
        return ElementDepot.DEFAULT; // 避免异常
    }
}
