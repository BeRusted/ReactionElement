package com.BeRusted.ReactionElement.Utils;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class NameHelper {
    public static String getLocalizedDisplayName(ItemStack stack, String unlocalizedNameKey) {
        String baseKey = unlocalizedNameKey + ".name";
        String namedKey = unlocalizedNameKey + ".named";

        String creator = null;
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("creator")) {
            creator = stack.getTagCompound().getString("creator");

            // 尝试多语言命名（%s 占位）
            if (I18n.canTranslate(namedKey)) {
                return "§6" + I18n.translateToLocalFormatted(namedKey, creator);
            } else {
                // 若语言未定义，则返回默认英文拼接
                String fallbackName = I18n.translateToLocal(baseKey);
                return "§6" + creator + "'s " + fallbackName;
            }
        }

        // 没有锻造者，返回普通名称
        return "§6" + I18n.translateToLocal(baseKey);
    }
}
