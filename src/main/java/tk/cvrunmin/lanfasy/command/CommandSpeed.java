package tk.cvrunmin.lanfasy.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Timer;

public class CommandSpeed extends CommandBase{
	Timer timer = new Timer(20);
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "speed";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		// TODO Auto-generated method stub
		return "command.speed.usage";
	}

/*	@Override
	public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length > 1)
        {
            float i;
            float i1 = timer.timerSpeed;
            if (par2ArrayOfStr[0].equals("set"))
            {
                if (par2ArrayOfStr[1].equals("half"))
                {
                    i = 0.5f;
                }
                else if (par2ArrayOfStr[1].equals("normal"))
                {
                    i = 1.0f;
                }
                else if (par2ArrayOfStr[1].equals("double"))
                {
                    i = 2.0f;
                }
                else if(par2ArrayOfStr[1].equals("triple"))
                {
                	i = 3.0f;
                }
                else
                {
                    i = Float.parseFloat(par2ArrayOfStr[1]);
                }

                this.setSpeed(par1ICommandSender, i);
                notifyAdmins(par1ICommandSender, "commands.speed.set", new Object[] {Float.valueOf(i)});
                return;
            }

            if (par2ArrayOfStr[0].equals("add"))
            {
                i = Float.parseFloat(par2ArrayOfStr[1]);
                this.addSpeed(par1ICommandSender, i);
                notifyAdmins(par1ICommandSender, "commands.speed.added", new Object[] {Float.valueOf(i)});
                return;
            }
            if (par2ArrayOfStr[0].equals("subtract"))
            {
                i = Float.parseFloat(par2ArrayOfStr[1]);
                this.subtractSpeed(par1ICommandSender, i);
                notifyAdmins(par1ICommandSender, "commands.speed.subtracted", new Object[] {Float.valueOf(i)});
                return;
            }
        }

        throw new WrongUsageException("commands.speed.usage", new Object[0]);
	}*/
    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
	@Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr, BlockPos pos)
    {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] {"set", "add", "subtract"}): (par2ArrayOfStr.length == 2 && par2ArrayOfStr[0].equals("set") ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] {"half", "normal", "double", "triple"}): null);
    }
    /**
     * Set the speed of the game.
     */
    protected void setSpeed(ICommandSender par1ICommandSender, float par2)
    {
         timer.timerSpeed = par2;
    }

    /**
     * Adds speed of the game.
     */
    protected void addSpeed(ICommandSender par1ICommandSender, float par2)
    {
        timer.timerSpeed += par2;
    }
    /**
     * Subtract speed of the game.
     */
    protected void subtractSpeed(ICommandSender par1ICommandSender, float par2){
    	timer.timerSpeed -= par2;
    }
	@Override
	public void processCommand(ICommandSender sender, String[] args)
			throws CommandException {
		if (args.length <= 1){
	        throw new WrongUsageException("commands.speed.usage", new Object[0]);
		}
        if (args.length > 1)
        {
            float i;
            float i1 = timer.timerSpeed;
            if (args[0].equals("set"))
            {
                if (args[1].equals("half"))
                {
                    i = 0.5f;
                }
                else if (args[1].equals("normal"))
                {
                    i = 1.0f;
                }
                else if (args[1].equals("double"))
                {
                    i = 2.0f;
                }
                else if(args[1].equals("triple"))
                {
                	i = 3.0f;
                }
                else
                {
                    i = Float.parseFloat(args[1]);
                }

                this.setSpeed(sender, i);
                notifyOperators(sender, this, "commands.speed.set", new Object[] {Float.valueOf(i)});
                return;
            }

            if (args[0].equals("add"))
            {
                i = Float.parseFloat(args[1]);
                this.addSpeed(sender, i);
                notifyOperators(sender, this, "commands.speed.added", new Object[] {Float.valueOf(i)});
                return;
            }
            if (args[0].equals("subtract"))
            {
                i = Float.parseFloat(args[1]);
                this.subtractSpeed(sender, i);
                notifyOperators(sender, this, "commands.speed.subtracted", new Object[] {Float.valueOf(i)});
                return;
            }
        }
	}
}
