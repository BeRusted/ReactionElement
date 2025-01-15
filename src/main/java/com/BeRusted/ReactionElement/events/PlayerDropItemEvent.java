package com.BeRusted.ReactionElement.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerDropItemEvent {

    @SubscribeEvent
    public void test_event(ItemTossEvent event){
        EntityPlayer player = event.getPlayer();
        String itemName = event.getEntityItem().getItem().getDisplayName();
        int itemCounter = event.getEntityItem().getItem().getCount();

        //使用本地化输出语句
        player.sendMessage(new TextComponentString("You Dropped"+" "+itemCounter+" "+itemName));
    }
}
