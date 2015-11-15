package tk.cvrunmin.lanfasy.client.gui;

import tk.cvrunmin.lanfasy.inventory.ContainerDimchanger;
import tk.cvrunmin.lanfasy.tileentity.TileEntityDimChanger;
import tk.cvrunmin.lanfasy.client.gui.inventory.GuiDIMChanger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
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
		case 1002:
			return new ContainerDimchanger(player.inventory, (TileEntityDimChanger)player.worldObj.getTileEntity(new BlockPos(x, y, z)), player.worldObj);
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
		case 1002:
			return new GuiDIMChanger(player.inventory, (TileEntityDimChanger)player.worldObj.getTileEntity(new BlockPos(x, y, z)), player.worldObj);
		}
		return null;
	}

}
