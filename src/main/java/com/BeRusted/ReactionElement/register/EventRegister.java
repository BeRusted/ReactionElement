package com.BeRusted.ReactionElement.register;


import com.BeRusted.ReactionElement.events.test_event;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber
public class EventRegister {

    public EventRegister(){
        MinecraftForge.EVENT_BUS.register(new test_event());
    }
}
