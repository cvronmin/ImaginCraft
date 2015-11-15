package tk.cvrunmin.lanfasy.util;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.FMLRelaunchLog;

import org.apache.logging.log4j.Level;

public class PlayerUtil {
	public static EntityPlayerMP getPlayerMPfromName (String username){
		MinecraftServer server = MinecraftServer.getServer();
		if(server != null){
			Iterator iterator = server.getConfigurationManager().playerEntityList.iterator();
			EntityPlayerMP entityplayermp;
			do{
				if (!iterator.hasNext())
				{
					return null;
					
				}

			entityplayermp = (EntityPlayerMP) iterator.next();
			}
			while (!entityplayermp.getCommandSenderName().equalsIgnoreCase(username));
			return entityplayermp;
		}
		FMLRelaunchLog.log("Lanfasy", Level.ERROR, "Couldn't find server");
		return null;
	}
	public static EntityPlayerMP getPlayerMPfromPlayer(EntityPlayer player){
		if (player == null)
		{
			return null;
		}

		if (player instanceof EntityPlayerMP)
		{
			return (EntityPlayerMP) player;
		}

		return PlayerUtil.getPlayerMPfromName(player.getCommandSenderName());
	}
}
