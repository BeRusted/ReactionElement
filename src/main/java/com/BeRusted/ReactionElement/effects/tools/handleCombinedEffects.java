package com.BeRusted.ReactionElement.effects.tools;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import static com.BeRusted.ReactionElement.effects.tools.clearEffect.clearEffect;

public class handleCombinedEffects {
    public static void handleCombinedEffects(EntityLivingBase entity){
        NBTTagCompound nbt = entity.getEntityData();
        NBTTagList effects = nbt.getTagList("CustomEffects",10);

        boolean hasFire = false;
        boolean hasIce = false;

        for(int i = 0 ; i < effects.tagCount() ; i ++){
            NBTTagCompound effectTag = effects.getCompoundTagAt(i);
            String effect = effectTag.getString("Effect");

            if(effect.equals("fire")) hasFire = true;
            if(effect.equals("ice")) hasIce = true;
        }

        if ( hasFire && hasIce){
            entity.world.createExplosion(entity,entity.posX,entity.posY,entity.posZ,2.0f,false);

            clearEffect(entity);
        }
    }
}
