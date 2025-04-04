package com.BeRusted.ReactionElement.registers;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


//注册物品或模型的材质材质
@Mod.EventBusSubscriber
public class ModelsRegister {
    public ModelsRegister(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        registerModel(ItemsRegister.symbol_item);
        registerModel(ItemsRegister.fire_sword);
        registerModel(ItemsRegister.ice_sword);
        registerModel(ItemsRegister.lightning_sword);
        registerModel(ItemsRegister.dark_sword);
        registerModel(ItemsRegister.water_sword);
        registerModel(ItemsRegister.health_sword);
        registerModel(ItemsRegister.light_sword);
        registerModel(ItemsRegister.aether_dark_blade);
    }

    private void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
