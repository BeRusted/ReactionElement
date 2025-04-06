package com.BeRusted.ReactionElement.events;

import com.BeRusted.ReactionElement.registers.ItemsRegister;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class onItemHolding {

    private static final Item aether_dark_blade = ItemsRegister.aether_dark_blade;
    private boolean isEventRegistered = false;
    private final MouseControl mouseControl = new MouseControl();

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){

        //玩家空指针异常处理
        if(event.player == null || event.player.world.isRemote){
            return;
        }


        if(event.player.getHeldItemMainhand().getItem() == aether_dark_blade){
            MinecraftForge.EVENT_BUS.register(mouseControl);
        }
        else {
            MinecraftForge.EVENT_BUS.unregister(mouseControl);
        }
    }

}
