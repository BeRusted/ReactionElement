package com.BeRusted.ReactionElement.creativetabs;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.registers.ItemsRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SubTab extends CreativeTabs {
    public SubTab(){
        super(ReactionElement.MODID+".subtab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return ItemsRegister.flame_sword.getDefaultInstance();
    }
}
