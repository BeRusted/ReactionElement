package com.BeRusted.ReactionElement.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onEntityAttacked {
    @SubscribeEvent
    public void onEntityAttacked(LivingAttackEvent event) {
        // 检查攻击者是否为玩家
        if (!(event.getSource().getTrueSource() instanceof EntityPlayerMP)) {
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) event.getSource().getTrueSource();
        Entity target = event.getEntity();

        // 获取计分板对象
        Scoreboard scoreboard = player.getWorldScoreboard();
        String objectiveName = "attack_counter";

        // 检查计分板目标是否存在
        ScoreObjective objective = scoreboard.getObjective(objectiveName);
        if (objective == null) {
            // 如果计分板目标不存在，则创建
            objective = scoreboard.addScoreObjective(objectiveName, net.minecraft.scoreboard.IScoreCriteria.DUMMY);
            objective.setDisplayName("Attack Counter");
        }

        // 获取当前生物的唯一标识符（可以用 UUID 或 Entity ID）
        String entityKey = "Entity_" + target.getEntityId();

        // 获取或增加计分
        int currentScore = scoreboard.getOrCreateScore(entityKey, objective).getScorePoints();
        scoreboard.getOrCreateScore(entityKey, objective).setScorePoints(currentScore + 1);

        // 通知玩家当前分数
        player.sendMessage(new TextComponentString(target.getName() + " Attacked Counter: " + (currentScore + 1)));
    }

}
