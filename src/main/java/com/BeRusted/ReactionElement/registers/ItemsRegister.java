package com.BeRusted.ReactionElement.registers;

import com.BeRusted.ReactionElement.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//这里用来注册物品
@Mod.EventBusSubscriber
public class ItemsRegister {
    public static final Item test_item = new test_item();
    public static final Item flame_sword = new flame_sword();
    public static final Item ice_sword = new ice_sword();
    public static final Item lightning_sword = new lightning_sword();
    public static final Item dark_sword = new dark_sword();
    public static final Item water_sword = new water_sword();
    public static final Item health_sword = new health_sword();

    public ItemsRegister(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(test_item,flame_sword,ice_sword,lightning_sword,dark_sword,water_sword,health_sword);
    }
}
