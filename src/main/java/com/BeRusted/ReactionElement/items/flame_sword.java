package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.item.Item;

public class flame_sword extends Item{
    private static String item_name = "flame_sword";

    public flame_sword(){
        super();
        this.setRegistryName(item_name);
        this.setUnlocalizedName(ReactionElement.MODID+"."+item_name);
        this.setCreativeTab(CreativeTabsRegister.SubTab);
    }
}
