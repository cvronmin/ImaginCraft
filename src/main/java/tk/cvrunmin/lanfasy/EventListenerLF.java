package tk.cvrunmin.lanfasy;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.client.gui.GuiLFIngameMenu;
import tk.cvrunmin.lanfasy.client.gui.GuiLFIngameMenuT2;
import tk.cvrunmin.lanfasy.util.LFConfig;

public class EventListenerLF {
	@SideOnly(Side.CLIENT)
	protected static EntityPlayer thePlayer;
	private Minecraft mc;
	protected static int nowDIMid;
	protected HashMap<String, InventoryPlayer> playerKeepsMap;
	public EventListenerLF() {
		this.playerKeepsMap = new HashMap();
	}
	@SideOnly(Side.CLIENT)
//	@SubscribeEvent
	public void onPlayerLoggedin(PlayerEvent.PlayerLoggedInEvent event){
		EventListenerLF.thePlayer = mc.thePlayer;
		EventListenerLF.nowDIMid = thePlayer.dimension;
	}
	@SubscribeEvent
	public void onChangeDIM(PlayerEvent.PlayerChangedDimensionEvent event){
		EventListenerLF.nowDIMid = event.toDim;
	}
	@SubscribeEvent
	public void pickupItem(EntityItemPickupEvent event) {
		Item item = event.item.getEntityItem().getItem();
/*		if(item == ItemBit.bit){
			event.entityPlayer.triggerAchievement(AchievementML.dataGet);
			}*/
	}
	@SubscribeEvent
	public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
		ItemStack itemStack = event.crafting;
		EntityPlayer player = event.player;
/*		if (itemStack.getItem() == ItemRPIDT.rpidt) {
			player.triggerAchievement(AchievementML.repair);
			}
		if (itemStack.getItem() == Item.getItemFromBlock(MLBlocks.refur)) {
			player.triggerAchievement(AchievementML.repairing);
		}
		if (itemStack.getItem() == Item.getItemFromBlock(MLBlocks.dati)) {
			player.triggerAchievement(AchievementML.dataBlocking);
		}
		if (itemStack.getItem() == Item.getItemFromBlock(MLBlocks.dati_invert)) {
			player.triggerAchievement(AchievementML.invertIt);
		}*/
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpened(GuiOpenEvent event){
		if(LFConfig.newIngameMenu){
			if(LFConfig.newMenuID == 0)
			{
//				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
//					   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011))
				if(event.gui instanceof GuiIngameMenu)
				{
					event.gui = new GuiIngameMenu();
			    }
		    }
			else if(LFConfig.newMenuID == 1)
			{
//				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
//					   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011))
				if(event.gui instanceof GuiIngameMenu)
				{
					event.gui = new GuiLFIngameMenuT2();
				}
			}
			else if(LFConfig.newMenuID == 2)
			{
//				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
//					   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011))
				if(event.gui instanceof GuiIngameMenu)
				{
					event.gui = new GuiLFIngameMenu();
				}
			}
		}
/*			if(event.gui instanceof GuiInventory && (FMLClientHandler.instance().getClient().thePlayer.dimension == MLDIM.DIMID ||
					   FMLClientHandler.instance().getClient().thePlayer.dimension == MLDIM_Hell.DIMID))
			{
				event.gui = new MLGuiInventory(FMLClientHandler.instance().getClient().thePlayer);
			}
			if(event.gui instanceof GuiContainerCreative && (FMLClientHandler.instance().getClient().thePlayer.dimension == MLDIM.DIMID ||
					   FMLClientHandler.instance().getClient().thePlayer.dimension == MLDIM_Hell.DIMID))
			{
				event.gui = new MLGuiContainerCreative(FMLClientHandler.instance().getClient().thePlayer);
			}*/
	}
}
