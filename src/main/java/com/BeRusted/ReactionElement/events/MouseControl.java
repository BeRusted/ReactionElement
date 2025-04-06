package com.BeRusted.ReactionElement.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.input.Mouse;

public class MouseControl {

    private static boolean leftPressedLastTick = false;
    private static boolean rightPressedLastTick = false;

    private static long leftClickStartTime = 0;
    private static long rightClickStartTime = 0;

    @SubscribeEvent
    public void ClickEvent(ClientTickEvent event) {

        //获取当前玩家
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        //玩家空指针异常处理
        if (player == null) {
            return; // 如果 player 为 null，直接返回，避免后续操作
        }

        // 左键检测
        boolean leftPressed = Mouse.isButtonDown(0);
        if (leftPressed && !leftPressedLastTick) {
            leftClickStartTime = System.currentTimeMillis();
        } else if (!leftPressed && leftPressedLastTick) {
            long heldTime = System.currentTimeMillis() - leftClickStartTime;
            //System.out.println("左键按住了 " + heldTime + " 毫秒");
            player.sendMessage(new TextComponentString("左键按住了 " + heldTime + " 毫秒"));
        }
        leftPressedLastTick = leftPressed;

        // 右键检测
        boolean rightPressed = Mouse.isButtonDown(1);
        if (rightPressed && !rightPressedLastTick) {
            rightClickStartTime = System.currentTimeMillis();
        } else if (!rightPressed && rightPressedLastTick) {
            long heldTime = System.currentTimeMillis() - rightClickStartTime;
            //System.out.println("右键按住了 " + heldTime + " 毫秒");
            player.sendMessage(new TextComponentString("右键按住了 " + heldTime + " 毫秒"));
        }
        rightPressedLastTick = rightPressed;
    }
}
