package com.BeRusted.ReactionElement;

import com.BeRusted.ReactionElement.registers.CreativeTabsRegister;
import com.BeRusted.ReactionElement.registers.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
//import org.apache.logging.log4j.Logger;

@Mod(modid = ReactionElement.MODID, name = ReactionElement.NAME, version = ReactionElement.VERSION)
public class ReactionElement
{
    public static final String MODID = "reaction_element";
    public static final String NAME = "Reaction Element";
    public static final String VERSION = "0.0.1";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        new ItemsRegister();
        new ModelsRegister();
        new EventsRegister();
        new CreativeTabsRegister();

    }



}
