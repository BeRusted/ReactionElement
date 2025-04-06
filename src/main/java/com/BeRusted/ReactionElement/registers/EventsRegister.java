package com.BeRusted.ReactionElement.registers;

import com.BeRusted.ReactionElement.events.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

//注册事件
@Mod.EventBusSubscriber
public class EventsRegister {
    public EventsRegister(){

        //常驻注册的事件
        MinecraftForge.EVENT_BUS.register(new onLivingUpdate());
        MinecraftForge.EVENT_BUS.register(new controlReaction());
        MinecraftForge.EVENT_BUS.register(new elementTooltipHandler());

        //临时注册的事件
        MinecraftForge.EVENT_BUS.register(new onItemHolding());
        //MinecraftForge.EVENT_BUS.register(new MouseControl());
        //MinecraftForge.EVENT_BUS.register(new AdvancedWeaponsDetection());


    }
}

