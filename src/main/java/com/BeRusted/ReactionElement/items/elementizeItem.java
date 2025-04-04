package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.element.ElementDepot;
import com.BeRusted.ReactionElement.Utils.TooltipHelper;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

// 如果要创造带有元素的物品,以此类为基类
// 如果要修改已有物品并使之获得元素,复制这里的代码过去
public class elementizeItem extends Item {

    private final ElementDepot element;// 物品绑定的元素

    public elementizeItem() {
        super();
        this.setRegistryName("symbol_item");
        this.setTranslationKey(ReactionElement.MODID +"."+ "symbol_item");
        this.setCreativeTab(CreativeTabsRegister.MainTab);
        this.element = ElementDepot.DEFAULT;
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
        //tooltip.add(I18n.format("tooltip.symbol_item.introduction"));
        TooltipHelper.addTooltip(tooltip,"tooltip.symbol_item.introduction",16);
    }

}
