package tk.cvrunmin.lanfasy.entity.cy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.util.LogHelper;

@SideOnly(Side.CLIENT)
public final class CYBossStatus {
    public static float healthScale;
    public static float healthScaleDivided;
    public static int healthBarDivide;
    public static int statusBarTime;
    public static String bossName;
    public static boolean hasColorModifier;

    public static void setBossStatus(IBossDisplayData displayData, boolean hasColorModifierIn)
    {
        healthScale = displayData.getHealth() / displayData.getMaxHealth();
        healthBarDivide = new BigDecimal(((((displayData.getHealth() - 1) / displayData.getMaxHealth()) * 10f) + 1f)).round(new MathContext(9, RoundingMode.DOWN)).intValue();
        LogHelper.info(healthBarDivide + "");
        LogHelper.info(((displayData.getHealth() - (displayData.getMaxHealth() * (healthBarDivide-1) / 10)) +"/"+ (displayData.getMaxHealth() / 10)));
        healthScaleDivided = ((displayData.getHealth() - (displayData.getMaxHealth() * (healthBarDivide-1) / 10)) / (displayData.getMaxHealth() / 10));
        statusBarTime = 100;
        bossName = displayData.getDisplayName().getFormattedText();
        hasColorModifier = hasColorModifierIn;
    }
}
