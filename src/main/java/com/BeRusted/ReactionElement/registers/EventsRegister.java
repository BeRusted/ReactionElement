package com.BeRusted.ReactionElement.registers;

import com.BeRusted.ReactionElement.events.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventsRegister {
    public EventsRegister(){

        //注册事件
        MinecraftForge.EVENT_BUS.register(new onLivingUpdate());

    }
}

