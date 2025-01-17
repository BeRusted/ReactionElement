package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.item.Item;

public class ice_sword  extends Item{
    private static String item_name = "ice_sword";

    public ice_sword(){
        super();
        this.setRegistryName(item_name);
        this.setUnlocalizedName(ReactionElement.MODID+"."+item_name);
        this.setCreativeTab(CreativeTabsRegister.SubTab);
    }
}
