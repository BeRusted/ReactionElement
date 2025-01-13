package com.BeRusted.ReactionElement.items;


import com.BeRusted.ReactionElement.ReactionElement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class test_item extends Item {
    private static String item_name = "test_item";
    public test_item(){
        this.setRegistryName(item_name);
        this.setUnlocalizedName(ReactionElement.MODID+"."+item_name);
        this.setCreativeTab(CreativeTabs.MISC);
    }
}
