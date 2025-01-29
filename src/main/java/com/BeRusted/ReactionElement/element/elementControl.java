package com.BeRusted.ReactionElement.element;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

//一个元素来了,先看生物的元素中有没有这个元素(有就让//元素添加//不要进行了)或可反应元素,有的话数值是多少(可以同类反应就让//元素累加//不要进行了),决定受否发生反应并减少元素数值
//随着生物nbt更新,发生//元素衰减和消失//

public class elementControl {

    // 元素反应
    public static void reaction(EntityLivingBase target, ElementDepot newElement) {
        NBTTagCompound nbt = target.getEntityData();
        if (!nbt.hasKey("ElementData")) {
            nbt.setTag("ElementData", new NBTTagCompound()); // 初始化新来的元素的数据
        }
        NBTTagCompound elementData = nbt.getCompoundTag("ElementData");

        String newElementName = newElement.getName();
        if (elementData.hasKey(newElementName)) { // 检查是否已有相同元素
            int currentValue = elementData.getInteger(newElementName);
            if (currentValue >= 100) {
                System.out.println("发生同元素反应"); // 有就触发同元素反应
                elementData.setInteger(newElementName, 0); // 清空该元素的计数
            } else {
                accumulateElementNumber(target, newElement, 50); // 有但数值不够则累加
            }
        } else { // 处理不同元素的情况
            String reactingElement = null;
            for (String key : elementData.getKeySet()) {
                if (!key.equals(newElementName) && elementData.getInteger(key) >= 50) {// 循环找一遍且只找比50大的
                    reactingElement = key;
                    break;
                }
            }
            if (reactingElement != null) {
                System.out.println("发生不同元素反应"); // 触发不同元素反应
                elementData.setInteger(reactingElement, elementData.getInteger(reactingElement) - 50); // 扣除反应元素计数 50
            } else {
                addElement(target, newElement); // 否则添加新元素
            }
        }

        nbt.setTag("ElementData", elementData);
        target.writeEntityToNBT(nbt);
    }

    // 添加新元素
    public static void addElement(EntityLivingBase target, ElementDepot element) {
        NBTTagCompound nbt = target.getEntityData();
        NBTTagCompound elementData = nbt.getCompoundTag("ElementData");
        elementData.setInteger(element.getName(), 50);
        nbt.setTag("ElementData", elementData);
        target.writeEntityToNBT(nbt);
    }

    // 累加元素数值
    public static void accumulateElementNumber(EntityLivingBase target, ElementDepot element, int amount) {
        NBTTagCompound nbt = target.getEntityData();
        NBTTagCompound elementData = nbt.getCompoundTag("ElementData");
        int newValue = elementData.getInteger(element.getName()) + amount;
        elementData.setInteger(element.getName(), newValue);
        nbt.setTag("ElementData", elementData);
        target.writeEntityToNBT(nbt);
    }

    // 每 5 秒 (100 tick) 使元素数值减少到原来的 0.72 倍
    public static void clearElement(EntityLivingBase target) {
        NBTTagCompound nbt = target.getEntityData();
        NBTTagCompound elementData = nbt.getCompoundTag("ElementData");

        for (String key : elementData.getKeySet()) {
            int newValue = (int) (elementData.getInteger(key) * 0.72);
            elementData.setInteger(key, newValue);
        }

        nbt.setTag("ElementData", elementData);
        target.writeEntityToNBT(nbt);
    }
}
