package com.BeRusted.ReactionElement.items.AdvancedElementalWeapons;

import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.element.ElementDepot;
import com.BeRusted.ReactionElement.Utils.AreaEffectHelper;
import com.BeRusted.ReactionElement.Utils.NameHelper;
import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AetherDarkBlade extends ItemSword {

    private final ElementDepot element;
    // 每个玩家的发光冷却记录
    private static final Map<UUID, Long> glowCooldownMap = new HashMap<>();
    private static final long GLOW_INTERVAL_MS = 5000;

    // 材质
    public static final Item.ToolMaterial WeaponMaterial = EnumHelper.addToolMaterial(
            "DARK",      // 名字（必须大写，否则有时渲染出错）
            3,             // harvestLevel（采掘等级，3 = 钻石）
            10000,          // maxUses（耐久度）
            30.0F,         // efficiency（挖掘速度）
            46.0F,          // attackDamage（基础攻击力）
            50             // enchantability（附魔性）
    );

    public AetherDarkBlade() {
        super(WeaponMaterial);
        this.setRegistryName("aether_dark_blade");
        this.setTranslationKey(ReactionElement.MODID +"."+ "aether_dark_blade");
        this.setCreativeTab(CreativeTabsRegister.MainTab);
        this.element = ElementDepot.DARK;

    }
    // 元素 tag
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setString("ElementData", element.getName());
    }
    // 创生时修改武器名字
    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setString("creator", playerIn.getName());
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        // 制作时更新武器名字
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());

        if (entity instanceof EntityPlayer) {
            NBTTagCompound tag = stack.getTagCompound();
            if (!tag.hasKey("creator")) {
                tag.setString("creator", (entity).getName());
            }
        }
        //距离攻击
        if (!world.isRemote && entity instanceof EntityPlayer && isSelected) {
            EntityPlayer player = (EntityPlayer) entity;

            long now = System.currentTimeMillis();
            UUID playerId = player.getUniqueID();

            boolean shouldGlow;

            if (!glowCooldownMap.containsKey(playerId) || now - glowCooldownMap.get(playerId) >= GLOW_INTERVAL_MS) {
                shouldGlow = true;
                glowCooldownMap.put(playerId, now);
            } else {
                shouldGlow = false;
            }
            // 50格
            AreaEffectHelper.getEntitiesAround(player, world, 50, (target) -> {
                if (shouldGlow) {
                    target.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 120));
                }
            });
            // 10格
            AreaEffectHelper.getEntitiesAround(player, world, 10, (target) -> {
                target.attackEntityFrom(DamageSource.MAGIC, 1.0F);
            });
        }
    }
    // 名称修改
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return NameHelper.getLocalizedDisplayName(stack, this.getUnlocalizedNameInefficiently(stack));
    }
    // 设定伤害和攻速
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> modifiers = HashMultimap.create();  // 不调用 super，自己建一个新的

        if (slot == EntityEquipmentSlot.MAINHAND) {
            // 设置攻击力
            modifiers.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 49.0, 0));

            // 设置攻击速度
            //冷却时间（秒） = 1 ÷ (攻速 + 4)
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                    new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.0F, 0)); // 冷却时间 = 0.5 秒
        }

        return modifiers;
    }
    // 特定条件时的攻击伤害
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        // 只对特定目标检查是否发光
        if (!target.isPotionActive(MobEffects.GLOWING)) {
            target.attackEntityFrom(DamageSource.causeMobDamage(attacker), 100);
        }
        return super.hitEntity(stack, target, attacker);
    }

}
