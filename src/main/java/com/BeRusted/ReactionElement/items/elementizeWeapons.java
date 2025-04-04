package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.element.ElementDepot;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class elementizeWeapons extends ItemSword {

    private final ElementDepot element;// 物品绑定的元素

    public elementizeWeapons(String name, ElementDepot element) {
        super(ToolMaterial.WOOD);
        this.setRegistryName(name);
        this.setTranslationKey(ReactionElement.MODID +"."+ name);
        this.setCreativeTab(CreativeTabsRegister.MainTab);
        this.element = element;
    }

    // 确保物品的 NBT 存在
    private void ensureElementData(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setString("ElementData", element.getName());
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        ensureElementData(stack);
    }
}
