package tk.cvrunmin.lanfasy.util;

import net.minecraftforge.fml.relauncher.FMLRelaunchLog;

import org.apache.logging.log4j.Level;

public class LogHelper {
	public static void info(String message)
	{
		FMLRelaunchLog.log("Lanfasy", Level.INFO, message);
	}
	public static void debug(String message)
	{
		FMLRelaunchLog.log("Lanfasy", Level.DEBUG, message);
	}
	
	public static void warn(String message)
	{
		FMLRelaunchLog.log("Lanfasy", Level.WARN, message);
	}
	
	public static void severe(String message)
	{
		FMLRelaunchLog.log("Lanfasy", Level.ERROR, message);
	}
}
