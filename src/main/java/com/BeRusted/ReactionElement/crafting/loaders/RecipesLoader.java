package com.BeRusted.ReactionElement.crafting.loaders;


import com.BeRusted.ReactionElement.ReactionElement;
import com.BeRusted.ReactionElement.register.BlocksRegister;
import com.BeRusted.ReactionElement.register.ItemsRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.fml.common.registry.GameRegistry.addShapedRecipe;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapelessRecipe;

@Mod.EventBusSubscriber
public class RecipesLoader {

    @SubscribeEvent
    public static void RecipesLoader(RegistryEvent.Register<IRecipe> event){
        addShapedRecipe(
                new ResourceLocation(ReactionElement.MODID, BlocksRegister.test_block.getRegistryName().toString()), // 配方名称
                null,                                                   // 配方组
                new ItemStack(BlocksRegister.test_block),                // 输出物品
                "###",                                                  // 第一行
                "###",                                                  // 第二行
                "###",                                                  // 第三行
                '#', ItemsRegister.test_item
        );

        addShapelessRecipe(
                new ResourceLocation(ReactionElement.MODID, ItemsRegister.test_item.getRegistryName().toString()), // 配方名称
                null,                                                   // 配方组
                new ItemStack(ItemsRegister.test_item,9),                // 输出物品
                Ingredient.fromItem(Item.getItemFromBlock(BlocksRegister.test_block))
        );

    }


}
