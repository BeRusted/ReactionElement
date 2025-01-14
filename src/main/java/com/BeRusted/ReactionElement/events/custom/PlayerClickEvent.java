package com.BeRusted.ReactionElement.events.custom;

import net.minecraftforge.client.event.MouseEvent;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerClickEvent extends MouseEvent {
    private final EntityPlayer player;
    private final MouseEvent mouseEvent;

    // 构造函数接受 EntityPlayer 和 MouseEvent
    public PlayerClickEvent(EntityPlayer player, MouseEvent mouseEvent) {
        //super();
        this.mouseEvent = mouseEvent;
        this.player = player;
    }

    // 获取玩家对象
    public EntityPlayer getPlayer() {
        return player;
    }

    // 获取 MouseEvent 对象
    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }
}
