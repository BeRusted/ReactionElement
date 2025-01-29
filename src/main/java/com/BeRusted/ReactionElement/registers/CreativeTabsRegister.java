package com.BeRusted.ReactionElement.registers;

import com.BeRusted.ReactionElement.creativetabs.MainTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CreativeTabsRegister {
    public static final CreativeTabs MainTab = new MainTab();

    public CreativeTabsRegister(){
    }
}
