package com.BeRusted.ReactionElement.register;

import com.BeRusted.ReactionElement.ReactionElement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class CreativeTabRegister extends CreativeTabs {
    public CreativeTabRegister(){
        super(ReactionElement.MODID);
    }

    @Override
    public ItemStack getTabIconItem(){
        return ItemsRegister.test_item.getDefaultInstance();
    }
}
