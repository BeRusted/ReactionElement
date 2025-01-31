package com.BeRusted.ReactionElement.items;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.element.ElementDepot;
import com.BeRusted.ReactionElement.element.elementControl;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
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

    public elementizeItem(String name, ElementDepot element) {
        this.setRegistryName(name);
        this.setTranslationKey(ReactionElement.MODID +"."+ name);
        this.setCreativeTab(CreativeTabsRegister.MainTab);
        this.element = element;
    }
    /*
    // 确保物品合成出来时有 NBT
    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer player) {
        ensureElementData(stack);
    }
    */

    // 确保物品的 NBT 存在
    private void ensureElementData(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setString("ElementData", element.getName());
    }

    // 在物品信息中显示元素 ( 同时也是保证物品的 NBT 存在 )
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        ensureElementData(stack);
        ElementDepot element = ElementDepot.valueOf(getElementData(stack));
        if (!element.equals(ElementDepot.DEFAULT)) {
            String localizedElementName = I18n.translateToLocal("element." + element.getName().toLowerCase());//不同语言要改名称
            tooltip.add(element.getColor() + localizedElementName);
        }
    }

    // 物品攻击生物时，触发元素反应
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        ElementDepot itemElement = ElementDepot.valueOf(getElementData(stack).toUpperCase());
        elementControl.reaction(target, itemElement, attacker);
        return super.hitEntity(stack, target, attacker);
    }

    // 获取物品的 ElementData
    public static String getElementData(ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("ElementData")) {
            return stack.getTagCompound().getString("ElementData").toUpperCase();
        }
        return "DEFAULT";
    }
}
