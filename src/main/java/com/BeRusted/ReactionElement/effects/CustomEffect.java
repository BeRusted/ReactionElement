package com.BeRusted.ReactionElement.effects;

public enum CustomEffect {
    FIRE("fire",1),
    ICE("ice",2),
    LIGHTNING("lightning",3),
    DARK("dark",4),
    WATER("water",5),
    HEALTH("health",6);

    private final String name;
    private final int id;

    CustomEffect(String name , int id ){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }
}
