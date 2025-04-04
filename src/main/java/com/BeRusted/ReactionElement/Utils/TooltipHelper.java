package com.BeRusted.ReactionElement.Utils;

import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.List;

public class TooltipHelper {
    //添加能按要求换行的物品描述,参数第一个参数填tooltip,第二个填描述源,第三个填每行字数
    public static void addTooltip(List<String> tooltip, String key, int maxUnitsPerLine) {
        String raw = I18n.format(key);
        if (raw.startsWith("@")) { // @ 是中文
            tooltip.addAll(wrapByChar(raw.substring(1), maxUnitsPerLine));
        } else if (raw.startsWith("#")) { // # 是英文
            tooltip.addAll(wrapByWord(raw.substring(1), maxUnitsPerLine));
        } else { //默认英文
            tooltip.addAll(wrapByWord(raw, maxUnitsPerLine));
        }
    }

    // 英文换行
    public static List<String> wrapByWord(String text, int maxUnits) {
        List<String> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        String currentColor = "";
        int currentUnits = 0;

        String[] parts = text.split(" ");
        for (String part : parts) {
            StringBuilder word = new StringBuilder();
            int wordUnits = part.length();

            for (int i = 0; i < part.length(); i++) {
                char c = part.charAt(i);
                if (c == '§' && i + 1 < part.length()) {
                    word.append(c).append(part.charAt(i + 1));
                    currentColor = "§" + part.charAt(i + 1);
                    i++;
                    continue;
                }
                word.append(c);
            }

            if (currentUnits + wordUnits + 1 > maxUnits) {
                result.add(currentLine.toString());
                currentLine = new StringBuilder(currentColor);
                currentUnits = 0;
            }

            if (currentLine.length() > 0) {
                currentLine.append(" ");
                currentUnits++;
            }

            currentLine.append(word);
            currentUnits += wordUnits;
        }

        if (currentLine.length() > 0) {
            result.add(currentLine.toString());
        }

        return result;
    }

    // 中文换行
    public static List<String> wrapByChar(String text, int maxCharsPerLine) {
        List<String> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        String currentColor = "";
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '§' && i + 1 < text.length()) {
                currentLine.append(c).append(text.charAt(i + 1));
                currentColor = "§" + text.charAt(i + 1);
                i++;
                continue;
            }

            currentLine.append(c);
            count++;

            if (count >= maxCharsPerLine) {
                result.add(currentLine.toString());
                currentLine = new StringBuilder(currentColor);
                count = 0;
            }
        }

        if (currentLine.length() > 0) {
            result.add(currentLine.toString());
        }

        return result;
    }

}
