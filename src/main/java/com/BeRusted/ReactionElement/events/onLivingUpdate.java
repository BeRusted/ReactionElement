package com.BeRusted.ReactionElement.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//用于控制元素数值的自然流逝
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

    // 每 5 秒 (100 tick) 使元素数值减少到原来的 0.72 倍
    public static void clearElement(EntityLivingBase target) {
        NBTTagCompound nbt = target.getEntityData();
        NBTTagCompound elementData = nbt.getCompoundTag("ElementData");

        for (String key : elementData.getKeySet()) {
            int newValue = (int) (elementData.getInteger(key) * 0.72);
            elementData.setInteger(key, newValue);
        }

        nbt.setTag("ElementData", elementData);
        target.writeEntityToNBT(nbt);
    }
}
