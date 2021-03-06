package tk.cvrunmin.lanfasy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import tk.cvrunmin.mcme.api.block.MEBlock;
import tk.cvrunmin.mcme.api.item.MEItem;
public class LFCommonProxy {
	public void init(FMLInitializationEvent event){
	}
	protected void blockRend(MEBlock block){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation("lanfasy:" + block.getRegisteredName(), "inventory"));
	}
	protected void blockRend(Block block, String registerName){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation("lanfasy:" + registerName, "inventory"));
	}
	protected void itemRend(MEItem item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation("lanfasy:" + item.getRegisteredName(), "inventory"));
	}
	protected void itemRend(Item item, String registerName){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation("lanfasy:" + registerName, "inventory"));
	}
	protected void itemRend(MEItem item, int damage, String ideniter){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, damage,
                new ModelResourceLocation("lanfasy:" + ideniter, "inventory"));
	}
}
