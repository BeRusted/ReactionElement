package com.BeRusted.ReactionElement.register;

import com.BeRusted.ReactionElement.events.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventsRegister {
    public EventsRegister(){

        MinecraftForge.EVENT_BUS.register(new PlayerDropItemEvent());
        MinecraftForge.EVENT_BUS.register(new PlayerClickEvent());
        MinecraftForge.EVENT_BUS.register(new FireEvent());
    }
}

