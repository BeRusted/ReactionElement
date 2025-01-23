package com.BeRusted.ReactionElement.effects.tools;

import com.BeRusted.ReactionElement.effects.CustomEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.UUID;

public class addEffect {

    //永久存在
    public static void addPermanentEffect(EntityLivingBase entity, CustomEffect effect) {
        NBTTagCompound nbt = entity.getEntityData();
        NBTTagList effects = nbt.getTagList("CustomEffects", 10);

        boolean alreadyExists = false;
        for (int i = 0; i < effects.tagCount(); i++) {
            NBTTagCompound effectTag = effects.getCompoundTagAt(i);
            if (effectTag.getString("Effect").equals(effect.getName())) {
                alreadyExists = true;
                break;
            }
        }

        if (!alreadyExists) {
            NBTTagCompound newEffect = new NBTTagCompound();
            newEffect.setString("Effect", effect.getName());
            effects.appendTag(newEffect);
        }

        nbt.setTag("CustomEffects", effects);
        entity.writeEntityToNBT(nbt);
    }

    //具有时效性
    public static void addTimedEffect(EntityLivingBase entity, CustomEffect effect) {
        NBTTagCompound nbt = entity.getEntityData();
        NBTTagList effects = nbt.getTagList("CustomEffects", 10);

        boolean alreadyExists = false;

        for (int i = 0; i < effects.tagCount(); i++) {
            NBTTagCompound effectTag = effects.getCompoundTagAt(i);
            if (effectTag.getString("Effect").equals(effect.getName())) {
                alreadyExists = true;
                break;
            }
        }

        if (!alreadyExists) {
            NBTTagCompound newEffect = new NBTTagCompound();
            newEffect.setString("Effect", effect.getName());
            newEffect.setLong("StartTime", entity.world.getTotalWorldTime()); // 记录附加时间
            //使用uuid来区分多个相同的属性
            newEffect.setString("UUID", UUID.randomUUID().toString());

            effects.appendTag(newEffect);
        }

        nbt.setTag("CustomEffects", effects);
        entity.writeEntityToNBT(nbt);
    }

}
