package tk.cvrunmin.lanfasy.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

@SideOnly(Side.CLIENT)
public class GuiLFIngameMenu extends GuiScreen{
	int waitForOpenGuiNo;
    private int field_146445_a;
    private int field_146444_f;
    private int panoramaTimer;
	private ResourceLocation field_110351_G;
    private DynamicTexture viewportTexture;
    private static final ResourceLocation[] altifectPaths = new ResourceLocation[] {
    	new ResourceLocation("Lanfasy", "textures/gui/background/altifect_0.png"),
    	new ResourceLocation("Lanfasy", "textures/gui/background/altifect_1.png"),
    	new ResourceLocation("Lanfasy", "textures/gui/background/altifect_2.png"),
    	new ResourceLocation("Lanfasy", "textures/gui/background/altifect_3.png"),
    	new ResourceLocation("Lanfasy", "textures/gui/background/altifect_4.png"),
    	new ResourceLocation("Lanfasy", "textures/gui/background/altifect_5.png")};
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.viewportTexture = new DynamicTexture(256, 256);
        this.field_110351_G = this.mc.getTextureManager().getDynamicTextureLocation("altifect", this.viewportTexture);
        this.field_146445_a = 0;
        this.buttonList.clear();
        byte b0 = -16;
        boolean flag = true;
        this.buttonList.add(new GuiLFButton(1, this.width / 2 - 200, this.height / 4 + 124 + b0, 100, 20, EnumChatFormatting.BLACK + I18n.format("menu.returnToMenu", new Object[0])));

        if (!this.mc.isIntegratedServerRunning())
        {
            ((GuiLFButton)this.buttonList.get(0)).displayString = EnumChatFormatting.BLACK + I18n.format("menu.disconnect", new Object[0]);
        }

        this.buttonList.add(new GuiLFButton(4, this.width / 2 - 200, this.height / 4 + 24 + b0, 100, 20, EnumChatFormatting.BLACK + I18n.format("menu.returnToGame", new Object[0])));
        this.buttonList.add(new GuiLFButton(0, this.width / 2 - 200, this.height / 4 + 104 + b0, 100, 20, EnumChatFormatting.BLACK + I18n.format("menu.options", new Object[0])));
        GuiLFButton guibutton;
        this.buttonList.add(guibutton = new GuiLFButton(7, this.width / 2 - 200, this.height / 4 + 84 + b0, 100, 20, EnumChatFormatting.BLACK + I18n.format("menu.shareToLan", new Object[0])));
        this.buttonList.add(new GuiLFButton(5, this.width / 2 - 200, this.height / 4 + 44 + b0, 100, 20, EnumChatFormatting.BLACK + I18n.format("gui.achievements", new Object[0])));
        this.buttonList.add(new GuiLFButton(6, this.width / 2 - 200, this.height / 4 + 64 + b0, 100, 20, EnumChatFormatting.BLACK + I18n.format("gui.stats", new Object[0])));
        guibutton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
    }
    protected void actionPerformed(GuiButton p_146284_1_)
    {
        switch (p_146284_1_.id)
        {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                p_146284_1_.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                this.mc.displayGuiScreen(new GuiMainMenu());
            case 2:
            case 3:
            default:
                break;
            case 4:
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;
            case 5:
                if (this.mc.thePlayer != null)
                this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
                break;
            case 6:
                if (this.mc.thePlayer != null)
                this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
                break;
            case 7:
                this.mc.displayGuiScreen(new GuiShareToLan(this));
                break;
            case 12:
//                FMLClientHandler.instance().showInGameModOptions((GuiIngameMenu)this);
                break;
        }
    }
    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        ++this.panoramaTimer;
        ++this.field_146444_f;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
    	GL11.glDisable(GL11.GL_ALPHA_TEST);
        this.renderSkybox(par1, par2, par3);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
//        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.BLACK + "Game menu", this.width / 2 - 200 + (this.fontRendererObj.getStringWidth("Game menu")), 20, 16777215);
        super.drawScreen(par1, par2, par3);
    }
    /**
     * Draws the main menu panorama
     */
    private void drawPanorama(int par1, int par2, float par3)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        byte b0 = 8;

        for (int k = 0; k < b0 * b0; ++k)
        {
            GL11.glPushMatrix();
            float f1 = ((float)(k % b0) / (float)b0 - 0.5F) / 64.0F;
            float f2 = ((float)(k / b0) / (float)b0 - 0.5F) / 64.0F;
            float f3 = 0.0F;
            GL11.glTranslatef(f1, f2, f3);
            GL11.glRotatef(MathHelper.sin(((float)this.panoramaTimer + par3) / 400.0F) * 25.0F * 2f + 20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-((float)this.panoramaTimer + par3) * 0.1F * 2f, 0.0F, 1.0F, 0.0F);

            for (int l = 0; l < 6; ++l)
            {
                GL11.glPushMatrix();

                if (l == 1)
                {
                    GL11.glRotatef(90.0F, 0.0F, 1.0F * 2f, 0.0F);
                }

                if (l == 2)
                {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F * 2f, 0.0F);
                }

                if (l == 3)
                {
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 4)
                {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (l == 5)
                {
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                this.mc.getTextureManager().bindTexture(altifectPaths[l]);
                worldrenderer.startDrawingQuads();
                worldrenderer.setColorRGBA_I(16777215, 255 / (k + 1));
                float f4 = 0.0F;
                worldrenderer.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double)(0.0F + f4), (double)(0.0F + f4));
                worldrenderer.addVertexWithUV(1.0D, -1.0D, 1.0D, (double)(1.0F - f4), (double)(0.0F + f4));
                worldrenderer.addVertexWithUV(1.0D, 1.0D, 1.0D, (double)(1.0F - f4), (double)(1.0F - f4));
                worldrenderer.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double)(0.0F + f4), (double)(1.0F - f4));
                tessellator.draw();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        worldrenderer.setTranslation(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    /**
     * Rotate and blurs the skybox view in the main menu
     */
    private void rotateAndBlurSkybox(float par1)
    {
        this.mc.getTextureManager().bindTexture(this.field_110351_G);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        byte b0 = 3;

        for (int i = 0; i < b0; ++i)
        {
        	worldrenderer.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float)(i + 1));
            int j = this.width;
            int k = this.height;
            float f1 = (float)(i - b0 / 2) / 256.0F;
            worldrenderer.addVertexWithUV((double)j, (double)k, (double)this.zLevel, (double)(0.0F + f1), 1.0D);
            worldrenderer.addVertexWithUV((double)j, 0.0D, (double)this.zLevel, (double)(1.0F + f1), 1.0D);
            worldrenderer.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(1.0F + f1), 0.0D);
            worldrenderer.addVertexWithUV(0.0D, (double)k, (double)this.zLevel, (double)(0.0F + f1), 0.0D);
        }

        tessellator.draw();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColorMask(true, true, true, true);
    }

    /**
     * Renders the skybox in the main menu
     */
    public void renderSkybox(int par1, int par2, float par3)
    {
        this.mc.getFramebuffer().unbindFramebuffer();
        GL11.glViewport(0, 0, 256, 256);
        this.drawPanorama(par1, par2, par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.rotateAndBlurSkybox(par3);
        this.mc.getFramebuffer().bindFramebuffer(true);
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        float f1 = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
        float f2 = (float)this.height * f1 / 256.0F;
        float f3 = (float)this.width * f1 / 256.0F;
        worldrenderer.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int k = this.width;
        int l = this.height;
        worldrenderer.addVertexWithUV(0.0D, (double)l, (double)this.zLevel, (double)(0.5F - f2), (double)(0.5F + f3));
        worldrenderer.addVertexWithUV((double)k, (double)l, (double)this.zLevel, (double)(0.5F - f2), (double)(0.5F - f3));
        worldrenderer.addVertexWithUV((double)k, 0.0D, (double)this.zLevel, (double)(0.5F + f2), (double)(0.5F - f3));
        worldrenderer.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(0.5F + f2), (double)(0.5F + f3));
        tessellator.draw();
    }

}
