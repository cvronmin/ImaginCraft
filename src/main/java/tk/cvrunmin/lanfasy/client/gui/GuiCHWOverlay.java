package tk.cvrunmin.lanfasy.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tk.cvrunmin.lanfasy.init.LFHecItems;
import tk.cvrunmin.mcme.api.client.gui.MEGui;

public class GuiCHWOverlay extends MEGui{
	Minecraft mc;
	public GuiCHWOverlay(Minecraft mc){
		super();
		this.mc = mc;
	}
	  @SubscribeEvent(priority = EventPriority.LOWEST)
	  public void onRender(RenderGameOverlayEvent event){
		    if(event.isCancelable() && event.type == ElementType.ALL)
		    {      
		      return;
		    }
		    if(this.mc.thePlayer instanceof EntityPlayer){
		    	EntityPlayer player = (EntityPlayer)this.mc.thePlayer;
		    	for(ItemStack stack : player.inventory.mainInventory){
		    		if(stack != null && stack.getItem() == LFHecItems.CHW2a02){
		    			drawRect(0, 0, event.resolution.getScaledWidth(), event.resolution.getScaledHeight(), 255, 255, 0, 25);
		    		}

		    	}
		    }
	  }
}
