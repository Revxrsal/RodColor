package io.github.reflxction.rodcolor;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import io.github.reflxction.rodcolor.command.RodCommand;

@Mod(modid = RodColor.MODID, version = RodColor.VERSION)
public class RodColor {

    static final String MODID = "rodcolor";
    static final String VERSION = "1.0";

    private static Configuration config;

    private RodCommand command = new RodCommand();

    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        Runtime.getRuntime().addShutdownHook(new Thread(config::save));
        ClientCommandHandler.instance.registerCommand(command);
    }

    @EventHandler
    public void onFMLServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(command);
    }

    public static Configuration getConfig() {
        return config;
    }
}
