package com.BeRusted.ReactionElement.register;

import com.BeRusted.ReactionElement.items.test_item;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//这里用来注册物品
@Mod.EventBusSubscriber
public class ItemsRegister {
    public static final Item test_item = new test_item();
    public ItemsRegister(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(test_item);
    }
}
