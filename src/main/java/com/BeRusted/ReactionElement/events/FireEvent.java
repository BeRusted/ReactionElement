package com.BeRusted.ReactionElement.events;

import com.BeRusted.ReactionElement.registers.ItemsRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FireEvent {
    @SubscribeEvent
    public void onEntityAttacked(LivingHurtEvent event) {
        // 检测实体攻击
        if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();

            // 检测 flame_sword 攻击
            ItemStack heldItem = attacker.getHeldItemMainhand();
            if (heldItem.getItem() == ItemsRegister.flame_sword) {
                // 获取被攻击的uuid
                Entity target = event.getEntity();

                // 获取记分板
                Scoreboard scoreboard = attacker.getEntityWorld().getScoreboard();
                String objectiveName = "flame_attacks";
                ScoreObjective objective = scoreboard.getObjective(objectiveName);

                if (objective == null) {
                    // 如果目标不存在，创建一个新的记分板目标
                    objective = scoreboard.addScoreObjective(objectiveName, net.minecraft.scoreboard.ScoreCriteria.DUMMY);
                    objective.setDisplayName("Flame Sword Hits");
                    scoreboard.setObjectiveInDisplaySlot(1, objective); // 右侧显示
                }

                // 用uuid记名
                String targetUUID = target.getUniqueID().toString();
                Score score = scoreboard.getOrCreateScore(targetUUID, objective);

                // 更新分数
                score.setScorePoints(score.getScorePoints() + 1);
            }
        }
    }
}
