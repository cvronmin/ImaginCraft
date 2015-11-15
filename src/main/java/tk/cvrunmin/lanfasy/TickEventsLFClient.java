package tk.cvrunmin.lanfasy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import tk.cvrunmin.lanfasy.client.gui.LFGuiIngame;
import tk.cvrunmin.lanfasy.client.renderer.SkyRenderAltifect;
import tk.cvrunmin.lanfasy.client.renderer.SkyRenderFTW;
import tk.cvrunmin.lanfasy.world.WorldProviderAltifect;
import tk.cvrunmin.lanfasy.world.WorldProviderLF;

public class TickEventsLFClient {
	final Minecraft mc = FMLClientHandler.instance().getClient();
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		final WorldClient world = mc.theWorld;
		if (event.phase == Phase.START)
		{

			if (world != null)
			{
				if (world.provider instanceof WorldProviderLF)
				{

					if (world.provider.getSkyRenderer() == null)
					{
						world.provider.setSkyRenderer(new SkyRenderFTW());
					}
				}
				else if(world.provider instanceof WorldProviderAltifect){
					if (world.provider.getSkyRenderer() == null) {
						world.provider.setSkyRenderer(new SkyRenderAltifect());
					}
				}
			}
		}
		}
}
