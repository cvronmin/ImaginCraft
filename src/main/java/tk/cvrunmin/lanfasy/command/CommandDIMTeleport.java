package tk.cvrunmin.lanfasy.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.DimensionManager;
import tk.cvrunmin.lanfasy.world.TeleporterGenatic;

public class CommandDIMTeleport extends CommandBase {

	@Override
	public String getCommandName() {
		return "dtp";
	}
    public int getRequiredPermissionLevel()
    {
        return 2;
    }
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "command.dtp.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
			throws CommandException {
        if (args.length < 1)
        {
            throw new WrongUsageException("commands.dtp.usage", new Object[0]);
        }
        else
        {
        	Object target;
        	int id;
        	target = func_175768_b(sender, args[0]);
        	if(args.length > 2){
                throw new WrongUsageException("commands.dtp.usage", new Object[0]);
        	}
        	else if(args.length < 2){
                throw new WrongUsageException("commands.dtp.usage", new Object[0]);
        	}
        	else{
        		try{
        			id = Integer.parseInt(args[1]);
        		}catch(NumberFormatException e){
        			throw new CommandException("commands.dtp.num.format", new Object[]{args[1]});
        		}
        		if(DimensionManager.getProvider(id) == null){
        			throw new CommandException("There is no World Provider in dimension " + id + "!");
        		}
        		if(target instanceof EntityPlayerMP){
        			((EntityPlayerMP)target).mcServer.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP)target), id, new TeleporterGenatic(((EntityPlayerMP)target).mcServer.worldServerForDimension(id)));
        		}
                notifyOperators(sender, this, "commands.dtp.success", new Object[] {((Entity)target).getCommandSenderName(), id});
        	}
        }
	}
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        return args.length != 1 ? null : getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
    }
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }
}
