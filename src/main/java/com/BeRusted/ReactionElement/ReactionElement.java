package com.BeRusted.ReactionElement;

import com.BeRusted.ReactionElement.register.ItemsRegister;
import com.BeRusted.ReactionElement.register.ModelsRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ReactionElement.MODID, name = ReactionElement.NAME, version = ReactionElement.VERSION)
public class ReactionElement
{
    public static final String MODID = "reaction_element";
    public static final String NAME = "Reaction Element";
    public static final String VERSION = "0.0.1";

    private static Logger logger;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        new ItemsRegister();
        new ModelsRegister();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}
