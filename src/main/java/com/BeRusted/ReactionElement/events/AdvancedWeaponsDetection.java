package com.BeRusted.ReactionElement.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AdvancedWeaponsDetection {
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        EntityLivingBase target = event.getEntityLiving();
        DamageSource source = event.getSource();

        if (!(source.getTrueSource() instanceof EntityLivingBase)) return;

        EntityLivingBase attacker = (EntityLivingBase) source.getTrueSource();

        // 判断是否副手攻击
        boolean isOffhand = isOffHandAttack(source);
        ItemStack weaponUsed = isOffhand ? attacker.getHeldItemOffhand() : attacker.getHeldItemMainhand();
        System.out.println("Weapon used: " + weaponUsed.getItem().getRegistryName());



        // 如果是你的终极武器
        if (!weaponUsed.isEmpty() && weaponUsed.getItem().getRegistryName().toString().equals("reaction_element:aether_dark_blade")) {
            System.out.println("[DEBUG] is glowing: " + target.isPotionActive(MobEffects.GLOWING));


            // 如果目标没有发光
            if (!target.isPotionActive(MobEffects.GLOWING)) {
                event.setAmount(event.getAmount() * 2.0F); // 修改这一次攻击的伤害
            }
        }
    }

    private boolean isOffHandAttack(DamageSource source) {
        String type = source.getDamageType();
        return type.equals("player.offhand") || type.contains("offhand");
    }
}