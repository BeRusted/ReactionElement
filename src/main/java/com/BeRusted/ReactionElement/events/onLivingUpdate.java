package com.BeRusted.ReactionElement.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.BeRusted.ReactionElement.effects.tools.handleCombinedEffects.handleCombinedEffects;
import static com.BeRusted.ReactionElement.effects.tools.clearEffect.*;

public class onLivingUpdate {
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.getEntityLiving() instanceof EntityLivingBase){
            EntityLivingBase entityLivingBase = (EntityLivingBase) event.getEntityLiving();
            handleCombinedEffects(entityLivingBase);
            autoRemoveEffect(entityLivingBase);
        }
    }
}
