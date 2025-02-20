package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.effects.CustomEffect;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.BeRusted.ReactionElement.effects.tools.addEffect.addTimedEffect;

public class dark_sword extends Item{
    private static String item_name = "dark_sword";

    public dark_sword(){
        super();
        this.setRegistryName(item_name);
        this.setTranslationKey(ReactionElement.MODID +"."+ item_name);
        this.setCreativeTab(CreativeTabsRegister.SubTab);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        //附加永久效果
        //addCustomEffectToEntity(target, CustomEffect.FIRE); // 举例：附加火属性

        //附加具有时间限制的效果
        addTimedEffect(target,CustomEffect.DARK);

        return super.hitEntity(stack, target, attacker);
    }
}
