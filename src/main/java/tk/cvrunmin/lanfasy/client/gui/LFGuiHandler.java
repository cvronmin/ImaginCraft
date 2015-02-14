package tk.cvrunmin.lanfasy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class LFGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID)
		{
		case 128:
//			return new ContainerRepairFurnace(player.inventory, (TileEntityRepairFurnace)player.worldObj.getTileEntity(x, y, z));
			
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID)
		{
		case 128:
//			return new GuiRepairFurnace(player.inventory, (TileEntityRepairFurnace)player.worldObj.getTileEntity(x, y, z));
			
		}
		return null;
	}

}
