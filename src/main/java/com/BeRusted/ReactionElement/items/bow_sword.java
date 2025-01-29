package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.item.Item;


public class bow_sword extends Item{
    private static String item_name = "bow_sword.json";

    public bow_sword(){
        super();
        this.setRegistryName(item_name);
        this.setTranslationKey(ReactionElement.MODID +"."+ item_name);
        this.setCreativeTab(CreativeTabsRegister.MainTab);
    }

}
