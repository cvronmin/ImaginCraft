package tk.cvrunmin.lanfasy;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.client.gui.GuiBSC;
import tk.cvrunmin.lanfasy.client.gui.GuiIngameMenuVan;
import tk.cvrunmin.lanfasy.client.gui.GuiLFIngameMenu;
import tk.cvrunmin.lanfasy.client.gui.GuiLFIngameMenuT2;
import tk.cvrunmin.lanfasy.client.gui.GuiLFIngameMenuT3;
import tk.cvrunmin.lanfasy.client.gui.inventory.LFGuiContainerCreative;
import tk.cvrunmin.lanfasy.client.gui.inventory.LFGuiInventory;
import tk.cvrunmin.lanfasy.util.LFConfig;

public class EventListenerLF {
	@SideOnly(Side.CLIENT)
	protected static EntityPlayer thePlayer;
	protected static int nowDIMid;
	protected Minecraft mc = FMLClientHandler.instance().getClient();
	protected HashMap<String, InventoryPlayer> playerKeepsMap;
	public EventListenerLF() {
		this.playerKeepsMap = new HashMap();
	}
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event){
		if(event.entity instanceof EntityPlayerMP){
			if(event.entity.dimension == 512){
				this.mc.displayGuiScreen(new GuiBSC());
			}
		}
	}
	@SubscribeEvent
	public void onPlayerPickXP(PlayerPickupXpEvent event){
		if(event.entityPlayer instanceof EntityPlayer){
			EntityPlayer entityPlayer = event.entityPlayer;
			entityPlayer.attackEntityFrom(DamageSource.generic, 1 * event.orb.xpValue);
		}
	}
	@SubscribeEvent
	public void onChangeDIM(PlayerEvent.PlayerChangedDimensionEvent event){
		EventListenerLF.nowDIMid = event.toDim;
		if(event.toDim == 1010){
        WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
        WorldInfo worldinfo = worldserver.getWorldInfo();
        worldinfo.setThundering(true);
		}
		if(event.fromDim == 1010 && event.toDim == 0){
	        WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
	        WorldInfo worldinfo = worldserver.getWorldInfo();
	        worldinfo.setRainTime(1);
	        worldinfo.setRaining(true);
	        worldinfo.setThundering(false);
			}
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
			switch(LFConfig.newMenuID){
			case 0:
				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
						FMLClientHandler.instance().getClient().thePlayer.dimension == -1011 || FMLClientHandler.instance().getClient().thePlayer.dimension == 512))
				{
				event.gui = new GuiIngameMenuVan();
				}
				break;
			case 1:
				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
				   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011 || FMLClientHandler.instance().getClient().thePlayer.dimension == 512))
			    {
				event.gui = new GuiLFIngameMenu();
			    }
				break;
			case 2:
				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
				   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011 || FMLClientHandler.instance().getClient().thePlayer.dimension == 512))
			    {
				event.gui = new GuiLFIngameMenuT2();
			    }
				break;
			case 3:
				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
				   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011 || FMLClientHandler.instance().getClient().thePlayer.dimension == 512))
			    {
				event.gui = new GuiLFIngameMenuT3();
			    }
				break;
			}
/*			if(LFConfig.newMenuID == 0)
			{
				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
					   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011))
				{
					event.gui = new GuiIngameMenuVan();
			    }
		    }
			else if(LFConfig.newMenuID == 1)
			{
				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
					   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011))
				{
					event.gui = new GuiLFIngameMenuT2();
				}
			}
			else if(LFConfig.newMenuID == 2)
			{
				if(event.gui instanceof GuiIngameMenu && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
					   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011))
				{
					event.gui = new GuiLFIngameMenu();
				}
			}*/
			if(event.gui instanceof GuiIngameMenu && FMLClientHandler.instance().getClient().thePlayer.dimension != 1010 &&
					   FMLClientHandler.instance().getClient().thePlayer.dimension != -1011){
				event.gui = new GuiIngameMenuVan();
			}
		}
			if(event.gui instanceof GuiInventory && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
					   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011 || FMLClientHandler.instance().getClient().thePlayer.dimension == 512))
			{
				event.gui = new LFGuiInventory(FMLClientHandler.instance().getClient().thePlayer);
			}
			if(event.gui instanceof GuiContainerCreative && (FMLClientHandler.instance().getClient().thePlayer.dimension == 1010 ||
					   FMLClientHandler.instance().getClient().thePlayer.dimension == -1011 || FMLClientHandler.instance().getClient().thePlayer.dimension == 512))
			{
				event.gui = new LFGuiContainerCreative(FMLClientHandler.instance().getClient().thePlayer);
			}
/*			if(event.gui instanceof GuiGameOver && FMLClientHandler.instance().getClient().thePlayer.dimension == 512){
				event.gui = new GuiBSC();
			}*/
	}
}
