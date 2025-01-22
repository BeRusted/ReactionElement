package com.BeRusted.ReactionElement.effects.tools;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class clearEffect {
    //立马清除所有效果
    public static void clearEffect(EntityLivingBase entity){
        NBTTagCompound nbt = entity.getEntityData();

        // 设置一个新的空列表来替换原有的效果列表
        NBTTagList emptyList = new NBTTagList();
        nbt.setTag("CustomEffects", emptyList);

        /* 将更改写回生物的 NBT 数据中 */
        entity.writeEntityToNBT(nbt);
    }

    //自动清除效果
    public static void autoRemoveEffect(EntityLivingBase entity) {
        NBTTagCompound nbt = entity.getEntityData();
        NBTTagList effects = nbt.getTagList("CustomEffects", 10);
        NBTTagList updatedEffects = new NBTTagList();

        long currentTime = entity.world.getTotalWorldTime();

        for (int i = 0; i < effects.tagCount(); i++) {
            NBTTagCompound effectTag = effects.getCompoundTagAt(i);
            String effectName = effectTag.getString("Effect");
            long startTime = effectTag.getLong("StartTime");

            // 检查是否超过 10 秒
            if (currentTime - startTime <= 200) {
                updatedEffects.appendTag(effectTag); // 未过期的效果保留
            }
        }

        // 更新效果列表
        nbt.setTag("CustomEffects", updatedEffects);
        entity.writeEntityToNBT(nbt);
    }


}
