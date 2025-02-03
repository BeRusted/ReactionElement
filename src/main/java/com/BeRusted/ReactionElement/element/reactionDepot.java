package com.BeRusted.ReactionElement.element;

import net.minecraft.entity.EntityLivingBase;
import java.lang.reflect.Method;

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

    // 火火     炎爆     被攻击者额外受到最后一次伤害的50%
    public static void fireAndfire(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火火同元素反应");
    }

    // 水水     逆流     被攻击者额外被击飞一格
    public static void waterAndwater(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水水同元素反应");
    }

    // 冰冰     极寒     被攻击者受到0.25s的缓慢5
    public static void iceAndice(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰冰同元素反应");
    }

    // 生命生命     吸血     攻击者回复被攻击最后一次收到的伤害的50%
    public static void healthAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命生命同元素反应");
    }

    // 电电     闪电     被攻击者受到一次雷击(该次雷击只让该生物受到影响)   //受到5点伤害并同时在脚底下生成一个火并同时播放雷的声音
    public static void lightningAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生电电同元素反应");
    }

    // 光光     修复     每次触发回复攻击者所有装备(指身穿戴的四件装备)和主副手的武器的耐久(5点)
    public static void lightAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生光光同元素反应");
    }

    // 暗暗     障目     被攻击者如果是玩家,则受到0.75s失明,如果是非玩家生物,则无法索敌0.25s
    public static void darkAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生暗暗同元素反应");
    }

    // 不同元素反应 //

    // 火水      蒸发     播放火被水熄灭的声音
    public static void fireAndwater(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火水不同元素反应");
    }

    // 火冰      融化     给予被攻击者10点水元素的元素计数
    public static void fireAndice(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火冰不同元素反应");
    }

    // 火生命     引燃     给予被攻击者5-10点真实伤害(即一定扣除若干点生命)
    public static void fireAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火生命不同元素反应");
    }

    // 火电     等离子态     攻击者的下一次攻击最远能打到7格外的物体
    public static void fireAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火电不同元素反应");
    }

    // 火光     净化     移除攻击者身上所有负面效果
    public static void fireAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火光不同元素反应");
    }

    // 火暗     冥火     移除被攻击者身上所有正面效果
    public static void fireAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火暗不同元素反应");
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

    // 冰电     超导     减少被攻击者两点盔甲韧性,可叠加,持续30s,每次叠加刷新持续时间
    public static void iceAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰电不同元素反应");
    }

    // 冰光     折射     给予被攻击者周围5*5*5范围内所有生物10点光元素的元素计数
    public static void iceAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰光不同元素反应");
    }

    // 冰暗     侵蚀     被攻击者下次受到的攻击的伤害会提升25%,可叠加,持续30s,每次叠加刷新持续时间
    public static void iceAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰暗不同元素反应");
    }

    // 生命电     振奋     使攻击者下次受伤减少50%
    public static void healthAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命电不同元素反应");
    }

    // 生命光     奇迹     增加攻击者的最大生命值,一次两点,持续30s
    public static void healthAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命光不同元素反应");
    }

    // 生命暗     贯伤     减少被攻击者的最大生命,一次两点,,最低降至只有1点血,若被攻击者是非玩家生物则永久生效,是玩家则持续30s
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

    // 光暗     混沌     给被攻击者加随机负面效果或给攻击者加随机正面效果
    public static void lightAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生光暗不同元素反应");
    }

}
