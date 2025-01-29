package com.BeRusted.ReactionElement.element;

public enum ElementDepot {
    FIRE("fire"),
    ICE("ice"),
    LIGHTNING("lightning"),
    DARK("dark"),
    WATER("water"),
    HEALTH("health"),
    LIGHT("light");


    private final String name;

    ElementDepot(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
