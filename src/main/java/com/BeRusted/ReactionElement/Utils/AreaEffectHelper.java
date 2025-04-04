package com.BeRusted.ReactionElement.Utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Consumer;

public class AreaEffectHelper {
    /**
     * 这里是高级武器的作用距离控制工具
     */
    public static void getEntitiesAround(EntityLivingBase source, World world, double range, Consumer<EntityLivingBase> action) {
        List<EntityLivingBase> nearby = world.getEntitiesWithinAABB(EntityLivingBase.class,
                new AxisAlignedBB(
                        source.posX - range, source.posY - range, source.posZ - range,
                        source.posX + range, source.posY + range, source.posZ + range
                ),
                entity -> entity != source && entity.isEntityAlive()
        );

        for (EntityLivingBase entity : nearby) {
            action.accept(entity);
        }
    }
}
