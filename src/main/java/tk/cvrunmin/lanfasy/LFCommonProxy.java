package tk.cvrunmin.lanfasy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import tk.cvrunmin.lanfasy.block.LFBlock;
import tk.cvrunmin.lanfasy.item.LFItem;
public class LFCommonProxy {
	public void registerRenderers(Lanfasy main){
		
	}
	public void registerBlockRenderers(){}
	public void registerItemRenderers(){}
	protected void blockRend(LFBlock block){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation("lanfasy:" + block.getRegisteredName(), "inventory"));
	}
	protected void itemRend(LFItem item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation("lanfasy:" + item.getRegisteredName(), "inventory"));
	}
}
