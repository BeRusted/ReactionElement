package com.BeRusted.ReactionElement.creativetabs.tabs;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.register.ItemsRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class MainTab extends CreativeTabs {
    public MainTab(){
        super(ReactionElement.MODID+".maintab");
    }

    @Override
    public ItemStack getTabIconItem(){
        return ItemsRegister.test_item.getDefaultInstance();
    }
}
