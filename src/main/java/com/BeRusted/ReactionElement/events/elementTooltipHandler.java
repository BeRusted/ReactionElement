package com.BeRusted.ReactionElement.events;

import com.BeRusted.ReactionElement.element.ElementDepot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

// 测试用代码
// 1.12.2
// /give @p minecraft:diamond_sword 1 0 {ElementData:"fire"}

// 让所有有 ElementData NBT 的武器都能显现出他的元素
public class elementTooltipHandler {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<String> tooltip = event.getToolTip();

        if (hasElementData(stack)) {
            ElementDepot element = ElementDepot.valueOf(getElementData(stack));
            if (!element.equals(ElementDepot.DEFAULT)) {
                String localizedElementName = I18n.translateToLocal("element." + element.getName().toLowerCase());
                tooltip.add(1,element.getColor() + localizedElementName);
            }
        }
    }

    // 检查物品是否有 ElementData NBT 标签
    private boolean hasElementData(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("ElementData");
    }

    // 获取物品的 ElementData
    private String getElementData(ItemStack stack) {
        return stack.getTagCompound().getString("ElementData").toUpperCase();
    }
}
