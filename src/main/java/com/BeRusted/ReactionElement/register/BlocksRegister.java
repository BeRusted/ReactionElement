package com.BeRusted.ReactionElement.register;

import com.BeRusted.ReactionElement.blocks.test_block;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//在这里注册方块
@Mod.EventBusSubscriber
public class BlocksRegister {
    public static final Block test_block = new test_block();

    public BlocksRegister(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    //注册的方块目录
    private static Block[] blocks = {
            test_block
    };

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : blocks) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for (Block block : blocks) {
            Item itemBlock = new ItemBlock(block).setRegistryName(block.getRegistryName());
            ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
            event.getRegistry().register(itemBlock);
        }
    }
}
