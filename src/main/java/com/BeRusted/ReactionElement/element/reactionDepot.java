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

    // 火火
    public static void fireAndfire(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火火同元素反应");
    }

    // 水水
    public static void waterAndwater(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水水同元素反应");
    }

    // 冰冰
    public static void iceAndice(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰冰同元素反应");
    }

    // 生命生命
    public static void healthAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命生命同元素反应");
    }

    // 电电
    public static void lightningAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生电电同元素反应");
    }

    // 光光
    public static void lightAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生光光同元素反应");
    }

    // 暗暗
    public static void darkAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生暗暗同元素反应");
    }

    // 不同元素反应 //

    // 火水
    public static void fireAndwater(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火水不同元素反应");
    }

    // 火冰
    public static void fireAndice(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火冰不同元素反应");
    }

    // 火生命
    public static void fireAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火生命不同元素反应");
    }

    // 火电
    public static void fireAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火电不同元素反应");
    }

    // 火光
    public static void fireAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火光不同元素反应");
    }

    // 火暗
    public static void fireAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生火暗不同元素反应");
    }

    // 水冰
    public static void waterAndice(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水冰不同元素反应");
    }

    // 水生命
    public static void waterAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水生命不同元素反应");
    }

    // 水电
    public static void waterAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水电不同元素反应");
    }

    // 水光
    public static void waterAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水光不同元素反应");
    }

    // 水暗
    public static void waterAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生水暗不同元素反应");
    }

    // 冰生命
    public static void iceAndhealth(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰生命不同元素反应");
    }

    // 冰电
    public static void iceAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰电不同元素反应");
    }

    // 冰光
    public static void iceAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰光不同元素反应");
    }

    // 冰暗
    public static void iceAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生冰暗不同元素反应");
    }

    // 生命电
    public static void healthAndlightning(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命电不同元素反应");
    }

    // 生命光
    public static void healthAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命光不同元素反应");
    }

    // 生命暗
    public static void healthAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生生命暗不同元素反应");
    }

    // 电光
    public static void lightningAndlight(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生电光不同元素反应");
    }

    // 电暗
    public static void lightningAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生电暗不同元素反应");
    }

    // 光暗
    public static void lightAnddark(EntityLivingBase target, EntityLivingBase attacker) {
        System.out.println("发生光暗不同元素反应");
    }

}
