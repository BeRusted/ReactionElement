package com.BeRusted.ReactionElement.element;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


import java.lang.reflect.Method;
import java.util.Random;

public class reactionDepot {
    // 统一反应接口
    public static void invokeReaction(String element1, String element2, EntityLivingBase target, EntityLivingBase attacker) {
        String methodName = element1 + "And" + element2;
        try {
            Method method = reactionDepot.class.getMethod(methodName, EntityLivingBase.class, EntityLivingBase.class);
            method.invoke(null, target, attacker);
        } catch (NoSuchMethodException e1) {
            // 因为目前没有按元素顺序发生不同反应的想法,所以比如冰火和火冰是一个反应
            methodName = element2 + "And" + element1;
            try {
                Method method = reactionDepot.class.getMethod(methodName, EntityLivingBase.class, EntityLivingBase.class);
                method.invoke(null, target, attacker);
            } catch (NoSuchMethodException e2) {//防止加了元素但漏加反应
                System.out.println("未知的元素反应: " + element1 + " 和 " + element2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 同元素反应 //

    // 火火 - 炎爆: 被攻击者额外受到最后一次伤害的50%
    public static void fireAndfire(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火火同元素反应");
        float lastDamage = target.getEntityData().getFloat("REdamage") * 0.5f;
        target.attackEntityFrom(DamageSource.ON_FIRE, lastDamage);
    }

    // 水水 - 逆流: 被攻击者受到额外击飞
    public static void waterAndwater(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水水同元素反应");
        target.addVelocity(0, 0.5D, 0);
        target.velocityChanged = true;
    }

    // 冰冰 - 极寒: 被攻击者受到0.25s的缓慢5
    public static void iceAndice(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰冰同元素反应");
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5, 4));
    }

    // 生命生命 - 吸血: 攻击者回复被攻击者最后一次受到伤害的50%
    public static void healthAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命生命同元素反应");
        float healAmount = target.getEntityData().getFloat("REdamage") * 0.5f;
        attacker.heal(healAmount);
    }

    // 电电 - 闪电: 被攻击者受到5点伤害, 播放雷声, 在被攻击者脚底生成火
    public static void lightningAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生电电同元素反应");
        World world = target.world;
        target.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 5.0F);
        BlockPos firePos = target.getPosition();
        if (world.isAirBlock(firePos)) {
            world.setBlockState(firePos, Blocks.FIRE.getDefaultState());
        }
        world.playSound(null, firePos, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.WEATHER, 1.0F, 1.0F);
    }

    // 光光 - 修复: 每次触发回复攻击者所有装备和主副手武器的耐久(5点)
    public static void lightAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生光光同元素反应");
        for (ItemStack item : attacker.getArmorInventoryList()) {
            if (item.isItemDamaged()) {
                item.setItemDamage(Math.max(0, item.getItemDamage() - 5));
            }
        }
        ItemStack mainHand = attacker.getHeldItemMainhand();
        ItemStack offHand = attacker.getHeldItemOffhand();
        if (mainHand.isItemDamaged()) {
            mainHand.setItemDamage(Math.max(0, mainHand.getItemDamage() - 5));
        }
        if (offHand.isItemDamaged()) {
            offHand.setItemDamage(Math.max(0, offHand.getItemDamage() - 5));
        }
    }

    // 暗暗 - 障目: 玩家0.75s失明，非玩家生物无法索敌0.25s
    public static void darkAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生暗暗同元素反应");
        if (target instanceof net.minecraft.entity.player.EntityPlayer) {
            target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 15));
        } else {
            target.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 5));//还未整好ai逻辑,整好再做
        }
    }

    // 不同元素反应 //

    // 火水 - 蒸发: 播放火被水熄灭的声音
    public static void fireAndwater(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火水不同元素反应");
        World world = target.world;
        world.playSound(null, target.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    // 火冰 - 融化: 给予被攻击者10点水元素的元素计数
    public static void fireAndice(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火冰不同元素反应");
        target.getEntityData().setInteger("water", target.getEntityData().getInteger("water") + 10);
    }

    // 火生命 - 引燃: 给予被攻击者5-10点真实伤害
    public static void fireAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火生命不同元素反应");
        int damage = new Random().nextInt(6) + 5;
        target.attackEntityFrom(DamageSource.GENERIC, damage);
    }

    // 火电 - 等离子态: 攻击者的下一次攻击最远能打到7格外的物体
    public static void fireAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火电不同元素反应");
        attacker.getEntityData().setBoolean("ExtendedRange", true);//后续做检测或更好的方法
    }

    // 火光 - 净化: 移除攻击者身上所有负面效果
    public static void fireAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火光不同元素反应");
        for (PotionEffect effect : attacker.getActivePotionEffects()) {
            if (!effect.getPotion().isBeneficial()) {
                attacker.removePotionEffect(effect.getPotion());
            }
        }
    }


    // 火暗 - 冥火: 移除被攻击者身上所有正面效果
    public static void fireAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火暗不同元素反应");
        for (PotionEffect effect : target.getActivePotionEffects()) {
            if (effect.getPotion().isBeneficial()) {
                target.removePotionEffect(effect.getPotion());
            }
        }
    }

    // 水冰     窒息      每次触发都能给被攻击者延长5s的溺水效果(气泡条减少至0开始掉血,相当于原版溺水)
    public static void waterAndice(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水冰不同元素反应");
    }

    // 水生命     救济     被攻击者周围5*5*5范围内所有生物都回复3点生命
    public static void waterAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水生命不同元素反应");
    }

    // 水电     扩散     追踪闪电,让被攻击者周围5*5*5范围内的生物(不包括)都受到最后一次伤害的10%
    public static void waterAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水电不同元素反应");
    }

    // 水光     反射     被攻击者周围5*5*5范围内所有生物都受到0.5s失明效果
    public static void waterAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水光不同元素反应");
    }

    // 水暗     侵蚀     减少被攻击者的护甲值2点,可叠加,持续30s,每次叠加刷新持续时间
    public static void waterAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水暗不同元素反应");
    }

    // 冰生命     冷疗     每次触发攻击者都能有0.25s的无敌时间
    public static void iceAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰生命不同元素反应");
    }

    // 冰电     超导     被攻击者下次受到的攻击的伤害会提升25%,可叠加,持续30s,每次叠加刷新持续时间
    public static void iceAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰电不同元素反应");
    }

    // 冰光     折射     给予被攻击者周围5*5*5范围内所有生物10点光元素的元素计数
    public static void iceAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰光不同元素反应");
    }

    // 冰暗     侵蚀     被攻击者下次攻击的伤害会降低50%
    public static void iceAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰暗不同元素反应");
    }

    // 生命电     振奋     使攻击者下次受伤减少50%
    public static void healthAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命电不同元素反应");
    }

    // 生命光     奇迹     增加攻击者的最大生命值,一次两点,持续30s,可叠加,每次叠加刷新持续时间
    public static void healthAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命光不同元素反应");
    }

    // 生命暗     贯伤     减少被攻击者的最大生命,一次两点,,最低降至只有1点血,若被攻击者是非玩家生物则永久生效,是玩家则持续30s,可叠加,每次叠加刷新持续时间
    public static void healthAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命暗不同元素反应");
    }

    // 电光     刹那     刷新攻击者的攻击
    public static void lightningAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生电光不同元素反应");
    }

    // 电暗     虚无     随机传送攻击者和被攻击者
    public static void lightningAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生电暗不同元素反应");
    }

    // 光暗     混沌     给被攻击者加随机负面效果或给攻击者加随机正面效果,每个效果都持续30s,若随到同样的效果,则提升一级(可以的情况下)并刷新持续时间
    public static void lightAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生光暗不同元素反应");
    }

}
