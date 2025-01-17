package com.BeRusted.ReactionElement.events;

import com.BeRusted.ReactionElement.registers.ItemsRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FireEvent {
    @SubscribeEvent
    public void FireEvent(AttackEntityEvent event){
        EntityPlayer player = event.getEntityPlayer();
        Entity target = event.getTarget();
        if(!event.getEntityPlayer().world.isRemote){
            if (player.getHeldItemMainhand().getItem().getRegistryName() == ItemsRegister.flame_sword.getRegistryName()){
                player.sendMessage(new TextComponentString(player.getName()+" attacked "+target.getName()));
            }
        }

    }
}
