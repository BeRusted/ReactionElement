package com.BeRusted.ReactionElement.Init;

import com.BeRusted.ReactionElement.ReactionElement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    // 存储所有物品实例
    public static final List<Item> ITEMS = new ArrayList<>();

    // 定义物品
    public static final Item test_item = createItem("test_item", CreativeTabs.MISC);

    // 创建物品的方法
    private static Item createItem(String name, CreativeTabs tab) {
        Item item = new Item()
                .setRegistryName(ReactionElement.MODID, name)
                .setUnlocalizedName(ReactionElement.MODID + "." + name)
                .setCreativeTab(tab);
        ITEMS.add(item); // 加入物品列表
        return item;
    }

    // 注册物品
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS) {
            event.getRegistry().register(item);
        }
    }
}
