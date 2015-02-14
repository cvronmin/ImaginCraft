package tk.cvrunmin.lanfasy.client.gui;

import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLFOptionButton extends GuiLFButton {
    private final GameSettings.Options enumOptions;

    public GuiLFOptionButton(int p_i45011_1_, int p_i45011_2_, int p_i45011_3_, String p_i45011_4_)
    {
        this(p_i45011_1_, p_i45011_2_, p_i45011_3_, (GameSettings.Options)null, p_i45011_4_);
    }

    public GuiLFOptionButton(int p_i45012_1_, int p_i45012_2_, int p_i45012_3_, int p_i45012_4_, int p_i45012_5_, String p_i45012_6_)
    {
        super(p_i45012_1_, p_i45012_2_, p_i45012_3_, p_i45012_4_, p_i45012_5_, p_i45012_6_);
        this.enumOptions = null;
    }

    public GuiLFOptionButton(int p_i45013_1_, int p_i45013_2_, int p_i45013_3_, GameSettings.Options p_i45013_4_, String p_i45013_5_)
    {
        super(p_i45013_1_, p_i45013_2_, p_i45013_3_, 150, 20, p_i45013_5_);
        this.enumOptions = p_i45013_4_;
    }
    public GuiLFOptionButton(int par1, int par2, int par3, int par4, int par5, GameSettings.Options par6, String par7)
    {
        super(par1, par2, par3, par4, par5, par7);
        this.enumOptions = par6;
    }

    public GameSettings.Options returnEnumOptions()
    {
        return this.enumOptions;
    }
}
