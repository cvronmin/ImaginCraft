package tk.cvrunmin.lanfasy.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LFConfig {
	public static boolean newIngameMenu = true;
	public static int newMenuID = 1;
	public static boolean safeMode = false;
	public static int startIon = 10;
	public static int endIon = 30;
	public static Configuration config;

	public static void startConfig(FMLPreInitializationEvent event) {
		setConfigLocation(event.getSuggestedConfigurationFile());
		init();
	}

	public static void setConfigLocation(File configFile) {
		if (config == null)
			config = new Configuration(configFile);
	}

	public static void init() {
		config.load();
		try {
			newIngameMenu = config.getBoolean("enable new Ingame Menu", Reference.CATEGORY_NEWELEMENT, true, "enable new style of ingame menu in new world");
			newMenuID = config.getInt("setting of new Ingame Menu", Reference.CATEGORY_NEWELEMENT, 2, 0, 3, "0 means default menu,1 means new menu no.1 and 2 means new menu no.2");
			safeMode = config.getBoolean("safety setting", Reference.CATEGORY_GLOBEL, false, "enable to protect the game!");
			startIon = config.getInt("start ionplozation", Reference.CATEGORY_GLOBEL, 10, 0, 10000, "control the time(unit:ticks) for Irrow to start ionplozation(only affect in normal mode)");
			endIon = config.getInt("end ionplozation", Reference.CATEGORY_GLOBEL, 30, 20, 10020, "control the time(unit:ticks) for Irrow to end ionplozation and die(only affect in normal mode)");
		}
		catch (Exception e) {} 
		finally {
			if (config.hasChanged()) {
				config.save();
				System.out.println("Config saved");
			}
		}
	}

	@SubscribeEvent
	public void onConfigChanged(
			ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.modID.equalsIgnoreCase("lanfasy")) {
			config.save();
			init();
		}
	}
	protected class Reference {
	    public static final String CATEGORY_NEWELEMENT = "newelementsettings";
	    public static final String CATEGORY_GLOBEL = "globelsettings";
	}

}
