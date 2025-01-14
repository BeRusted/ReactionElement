package com.BeRusted.ReactionElement.creativetabs.loaders;

import com.BeRusted.ReactionElement.creativetabs.tabs.MainTab;
import com.BeRusted.ReactionElement.creativetabs.tabs.SubTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber
public class CreativeTabLoader {
    public static final CreativeTabs MainTab = new MainTab();
    public static final CreativeTabs SubTab = new SubTab();

    public CreativeTabLoader(){
    }
}
