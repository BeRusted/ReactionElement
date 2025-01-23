package com.BeRusted.ReactionElement.effects.tools;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;


public class handleCombinedEffects {
    public static void handleCombinedEffects(EntityLivingBase entity){
        NBTTagCompound nbt = entity.getEntityData();
        NBTTagList effects = nbt.getTagList("CustomEffects",10);

        boolean hasFire = false;
        boolean hasIce = false;
        boolean hasLightning = false;
        boolean hasWater = false;
        boolean hasDark = false;
        boolean hasHealth = false;

//        int fireCount = 0;
//        int iceCount = 0;
//        int lightningCount = 0;
//        int waterCount = 0;
//        int darkCount = 0;
//        int healthCount = 0;

        for(int i = 0 ; i < effects.tagCount() ; i ++){
            NBTTagCompound effectTag = effects.getCompoundTagAt(i);
            String effect = effectTag.getString("Effect");

            if("fire".equals(effect)){
                hasFire = true;
                //fireCount ++;
            }
            if("ice".equals(effect)){
                hasIce = true;
                //iceCount ++;
            }
            if("lightning".equals(effect)){
                hasLightning = true;
                //lightningCount ++;
            }
            if("water".equals(effect)){
                hasWater = true;
                //waterCount ++;
            }
            if("dark".equals(effect)) {
                hasDark = true;
                //darkCount ++;
            }
            if("health".equals(effect)){
                hasHealth = true;
                //healthCount ++;
            }
        }

        //进行元素反应


    }
}
