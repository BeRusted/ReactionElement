package com.BeRusted.ReactionElement.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class test {
    @SubscribeEvent
    public void test(MouseEvent mouseEvent) {
        EntityPlayer player = Minecraft.getMinecraft().player;

        if (mouseEvent.isButtonstate()) {
            if (mouseEvent.getButton() == 0) { // 左键
                player.sendMessage(new TextComponentString("Left Click "+player.getName()));
            } else if (mouseEvent.getButton() == 1) { // 右键
                player.sendMessage(new TextComponentString("Right Click "+player.getName()));
            } else { // 其他鼠标按钮
                player.sendMessage(new TextComponentString("Other Button "+player.getName()));
            }
        }
    }
}
