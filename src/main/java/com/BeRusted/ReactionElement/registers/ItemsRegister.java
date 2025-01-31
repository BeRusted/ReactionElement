package com.BeRusted.ReactionElement.registers;

import com.BeRusted.ReactionElement.element.ElementDepot;
import com.BeRusted.ReactionElement.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//注册物品
@Mod.EventBusSubscriber
public class ItemsRegister {
    // 测试用物品
    public static final Item fire_sword = new elementizeItem("fire_sword", ElementDepot.FIRE);
    public static final Item ice_sword = new elementizeItem("ice_sword", ElementDepot.ICE);
    public static final Item lightning_sword = new elementizeItem("lightning_sword", ElementDepot.LIGHTNING);
    public static final Item dark_sword = new elementizeItem("dark_sword", ElementDepot.DARK);
    public static final Item water_sword = new elementizeItem("water_sword", ElementDepot.WATER);
    public static final Item health_sword = new elementizeItem("health_sword", ElementDepot.HEALTH);
    public static final Item light_sword = new elementizeItem("light_sword", ElementDepot.LIGHT);

    // 模组象征
    public static final Item symbol_item = new elementizeItem();

    public ItemsRegister(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(symbol_item,fire_sword,ice_sword,lightning_sword,dark_sword,water_sword,health_sword,light_sword);
    }
}
