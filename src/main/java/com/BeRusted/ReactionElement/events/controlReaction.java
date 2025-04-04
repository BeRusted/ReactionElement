package com.BeRusted.ReactionElement.events;

import com.BeRusted.ReactionElement.element.ElementDepot;
import com.BeRusted.ReactionElement.element.reactionDepot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.DamageSource;

// 监听生物受到伤害事件，只有来自带元素 NBT 的物品的攻击才会记录 REdamage
public class controlReaction {
    //触发原因是生物受到伤害( onLivingDamageEvent )
    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource source = event.getSource();

        if (source.getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) source.getTrueSource();

            // 检查主手和副手的武器是否包含元素 NBT 标签
            ItemStack mainHandItem = attacker.getHeldItemMainhand();
            ItemStack offHandItem = attacker.getHeldItemOffhand();

            boolean isPlayerAttack = source.getDamageType().equals("player");

            if (isPlayerAttack) {
                if (isOffHandAttack(source)) {
                    if (hasElementData(offHandItem)) {
                        processReaction(offHandItem, entity, attacker, event.getAmount());
                    }
                } else {
                    if (hasElementData(mainHandItem)) {
                        processReaction(mainHandItem, entity, attacker, event.getAmount());
                    }
                }
            } else {
                // 如果是怪物或其他生物，检查主手武器
                if (attacker instanceof IMob && hasElementData(mainHandItem)) {
                    processReaction(mainHandItem, entity, attacker, event.getAmount());
                }
            }
        }
    }

    // 检查物品是否包含 "ElementData" NBT 标签
    // 完全从属于 onLivingDamageEvent
    private boolean hasElementData(ItemStack itemStack) {
        return !itemStack.isEmpty() && itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("ElementData");
    }

    // 判断是否来自副手的攻击
    // 完全从属于 onLivingDamageEvent
    private boolean isOffHandAttack(DamageSource source) {
        String damageType = source.getDamageType();
        return damageType.equals("player.offhand") || damageType.contains("offhand");
    }

    // 安排逻辑 同时获取必要的参数
    // 完全从属于 onLivingDamageEvent
    private void processReaction(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float damage) {
        ElementDepot itemElement = ElementDepot.valueOf(getElementData(stack).toUpperCase());
        recordDamage(target, damage);
        reaction(target, itemElement, attacker);
    }

    // 记录伤害到生物的 NBT 数据中
    // 完全从属于 processReaction
    private void recordDamage(EntityLivingBase entity, float damage) {
        entity.getEntityData().setFloat("REdamage", damage);
    }

    // 获取物品的 ElementData
    // 完全从属于 processReaction
    public static String getElementData(ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("ElementData")) {
            return stack.getTagCompound().getString("ElementData").toUpperCase();
        }
        return "DEFAULT";
    }

    //一个元素来了,若能发生 同元素反应 就发生,若不能且不能发生 不同元素反应 就 加若干元素数值 或 添加新元素
    //随着生物nbt更新,发生//元素衰减和消失//

    // 元素反应
    public static void reaction(EntityLivingBase target, ElementDepot newElement, EntityLivingBase attacker) {
        NBTTagCompound nbt = target.getEntityData();
        if (!nbt.hasKey("ElementData")) {
            nbt.setTag("ElementData", new NBTTagCompound()); // 初始化生物的元素nbt组,名为ElementData
        }
        NBTTagCompound elementData = nbt.getCompoundTag("ElementData");

        String newElementName = newElement.getName();
        boolean sameElementReacted = false;

        if (elementData.hasKey(newElementName)) { // 检查是否已有相同元素
            int currentValue = elementData.getInteger(newElementName);
            if (currentValue >= 20) {
                reactionDepot.invokeReaction(newElementName, newElementName, target, attacker);//发生同元素反应
                elementData.setInteger(newElementName, 0); // 清空该元素的计数
                sameElementReacted =true;
            }
        }

        if (!sameElementReacted) { // 仅在未发生同元素反应时，检查不同元素反应
            String reactingElement = null;
            for (String key : elementData.getKeySet()) {
                if (!key.equals(newElementName) && elementData.getInteger(key) >= 10) {//循环一遍找数值大于 50 的不同元素
                    reactingElement = key;
                    break;
                }
            }
            if (reactingElement != null) {
                reactionDepot.invokeReaction(newElementName, reactingElement, target, attacker); // 触发不同元素反应
                elementData.setInteger(reactingElement, elementData.getInteger(reactingElement) - 10); // 扣除反应元素计数 50
            } else if (!elementData.hasKey(newElementName)) {
                addElement(target, newElement); // 仅在无法发生元素反应且这个元素不存在时添加新元素
                accumulateElementNumber(target, newElement, damageCount(target));
            } else {
                accumulateElementNumber(target, newElement, damageCount(target)); // 仅在无法发生元素反应时且元素存在时累加数值
            }
        }
        nbt.setTag("ElementData", elementData);
        target.writeEntityToNBT(nbt);
    }

    //以下三个函数均完全从属于reaction函数
    // 添加新元素
    public static void addElement(EntityLivingBase target, ElementDepot element) {
        NBTTagCompound nbt = target.getEntityData();
        NBTTagCompound elementData = nbt.getCompoundTag("ElementData");
        elementData.setInteger(element.getName(), 0);
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

    //计算元素数值增加多少
    public static int damageCount(EntityLivingBase target) {
        float damage = 0;
        if (target.getEntityData().hasKey("REdamage")) {
            damage = target.getEntityData().getFloat("REdamage");
        }
        return 10+(int)damage;
    }
}