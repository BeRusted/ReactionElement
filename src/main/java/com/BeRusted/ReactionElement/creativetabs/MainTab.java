package com.BeRusted.ReactionElement.creativetabs;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.registers.ItemsRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class MainTab extends CreativeTabs {
    public MainTab(){

        super(ReactionElement.MODID+".maintab");
    }
    /*
    @Override
    public ItemStack getTabIconItem(){
        return ItemsRegister.test_item.getDefaultInstance();
    }
    */
    @Override
    public ItemStack createIcon() {
        return ItemsRegister.test_item.getDefaultInstance();
    }
}
