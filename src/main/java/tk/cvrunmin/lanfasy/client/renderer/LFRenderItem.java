package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.init.LFItems;

@SideOnly(Side.CLIENT)
public class LFRenderItem extends RenderItem implements IResourceManagerReloadListener{

	public LFRenderItem(TextureManager textureManager, ModelManager modelManager) {
		super(textureManager, modelManager);
		// TODO Auto-generated constructor stub
	}
	@Override
    public void renderItemModelForEntity(ItemStack stack, EntityLivingBase entityToRenderFor, ItemCameraTransforms.TransformType cameraTransformType)
    {
        IBakedModel ibakedmodel = this.getItemModelMesher().getItemModel(stack);

        if (entityToRenderFor instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityToRenderFor;
            Item item = stack.getItem();
            ModelResourceLocation modelresourcelocation = null;

/*            if (item == Items.fishing_rod && entityplayer.fishEntity != null)
            {
                modelresourcelocation = new ModelResourceLocation("fishing_rod_cast", "inventory");
            }
            else */
            	if (item == LFItems.fire_bow && entityplayer.getItemInUse() != null)
            {
                int i = stack.getMaxItemUseDuration() - entityplayer.getItemInUseCount();

                if (i >= 18)
                {
                    modelresourcelocation = new ModelResourceLocation("fire_bow_pulling_2", "inventory");
                }
                else if (i > 13)
                {
                    modelresourcelocation = new ModelResourceLocation("fire_bow_pulling_1", "inventory");
                }
                else if (i > 0)
                {
                    modelresourcelocation = new ModelResourceLocation("fire_bow_pulling_0", "inventory");
                }
            }
            else
            {
                modelresourcelocation = item.getModel(stack, entityplayer, entityplayer.getItemInUseCount());
            }

            if (modelresourcelocation != null)
            {
                ibakedmodel = this.getItemModelMesher().getModelManager().getModel(modelresourcelocation);
            }
        }

        this.renderItemModelTransform(stack, ibakedmodel, cameraTransformType);
    }

}
