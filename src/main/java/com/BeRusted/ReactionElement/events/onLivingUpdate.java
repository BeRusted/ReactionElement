package com.BeRusted.ReactionElement.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.BeRusted.ReactionElement.element.elementControl.clearElement;

public class onLivingUpdate {
    private static int tickCounter = 0;

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = event.getEntityLiving();
            tickCounter++;
            if (tickCounter >= 100) {
                clearElement(entityLivingBase);
                tickCounter = 0;
            }
        }
    }
}
