package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.element.ElementDepot;
import com.BeRusted.ReactionElement.element.elementControl;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class flame_sword extends Item{
    private static String item_name = "flame_sword";

    public flame_sword(){
        super();
        this.setRegistryName(item_name);
        this.setTranslationKey(ReactionElement.MODID +"."+ item_name);
        this.setCreativeTab(CreativeTabsRegister.MainTab);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){

        //附加具有时间限制的效果
        elementControl.reaction(target, ElementDepot.FIRE, attacker);

        return super.hitEntity(stack, target, attacker);
    }
}
