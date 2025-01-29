package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.item.Item;

public class symbol_item extends Item {

    private static String item_name = "symbol_item";

    public symbol_item(){
        super();
        this.setRegistryName(item_name);
        this.setTranslationKey(ReactionElement.MODID +"."+ item_name);
        this.setCreativeTab(CreativeTabsRegister.MainTab);
    }
}
