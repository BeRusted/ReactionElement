package com.BeRusted.ReactionElement.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// 监听生物受到伤害事件，只有来自带元素 NBT 的物品的攻击才会记录 REdamage
public class onLivingDamage {
    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
            ItemStack heldItem = attacker.getHeldItemMainhand();

            if (!heldItem.isEmpty() && heldItem.hasTagCompound()) {
                NBTTagCompound itemNbt = heldItem.getTagCompound();
                if (itemNbt.hasKey("ElementData")) {
                    float damage = event.getAmount();
                    entity.getEntityData().setFloat("REdamage", damage);
                }
            }
        }
    }
}
