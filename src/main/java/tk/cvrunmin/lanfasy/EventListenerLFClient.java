package tk.cvrunmin.lanfasy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import tk.cvrunmin.lanfasy.client.gui.LFGuiIngame;

public class EventListenerLFClient {
	protected Minecraft mc = FMLClientHandler.instance().getClient();
	private GuiIngame gui;
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
		gui = mc.ingameGUI;
		EventListenerLF.nowDIMid = event.player.dimension;
		if(event.player.dimension == 1010 || event.player.dimension == -1011 || event.player.dimension == 512){
			mc.ingameGUI = new LFGuiIngame(mc);
		}
		if(event.player.dimension >= -1 && event.player.dimension <= 1){
			mc.ingameGUI = gui;
		}
	}
	@SubscribeEvent
	public void onChangeDIM(PlayerEvent.PlayerChangedDimensionEvent event){
		gui = mc.ingameGUI;
		EventListenerLF.nowDIMid = event.toDim;
		if(event.toDim == 1010 || event.toDim == -1011 || event.toDim == 512){
			mc.ingameGUI = new LFGuiIngame(mc);
		}
		if(event.toDim >= -1 && event.toDim <= 1){
			mc.ingameGUI = gui;
		}
	}
}
