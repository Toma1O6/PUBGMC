package dev.toma.pubgmc.util.recipes;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class WorkbenchRecipe {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/recipebase.png");
    public final ItemStack result;
    public final List<CraftingIngredient> ingredients;
    public final CraftingCategory category;
    public final ItemStack returnStack;

    protected WorkbenchRecipe(ItemStack result, List<CraftingIngredient> ingredients, CraftingCategory category) {
        this(result, ingredients, category, ItemStack.EMPTY);
    }

    protected WorkbenchRecipe(ItemStack result, List<CraftingIngredient> ingredients, CraftingCategory category, ItemStack returnStack) {
        this.result = result;
        this.ingredients = ingredients;
        this.category = category;
        this.returnStack = returnStack;
    }

    public void onCraft(World world, BlockPos pos) {
        if (!returnStack.isEmpty() && !world.isRemote) {
            EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(returnStack.getItem()));
            item.motionX = Pubgmc.rng().nextDouble() * 0.15;
            item.motionY = Pubgmc.rng().nextDouble() * 0.15;
            item.motionZ = Pubgmc.rng().nextDouble() * 0.15;
            item.setPickupDelay(30);
            world.spawnEntity(item);
        }
    }

    @SideOnly(Side.CLIENT)
    public void drawRecipe(int mouseX, int mouseY) {
        Minecraft client = Minecraft.getMinecraft();
        ImageUtil.drawCustomSizedImage(client, TEXTURE, mouseX, mouseY, 50, 86, false);
        for (int slotIndex = 0; slotIndex < this.ingredients.size(); slotIndex++) {
            CraftingIngredient ingredient = this.ingredients.get(slotIndex);
            int x = slotIndex % 2 == 0 ? mouseX + 8 : mouseX + 26;
            int y = slotIndex / 2 * 18 + mouseY + 8;
            RenderHelper.enableGUIStandardItemLighting();
            ingredient.renderIngredient(client, x, y);
            RenderHelper.disableStandardItemLighting();
            int count = ingredient.requiredResourceSize();
            boolean twoDigits = count >= 10; // refactor this...
            client.fontRenderer.drawStringWithShadow(String.valueOf(count), twoDigits ? x + 5 : x + 11, y + 9, 0xFFFFFF);
        }
    }

    public enum CraftingCategory {

        GUNS("Guns"),
        AMMO("Ammo"),
        ATTACHMENTS("Attachments"),
        HEALS("Healing"),
        THROWABLES("Grenades"),
        WEARABLES("Armor&Utility"),
        VEHICLES("Vehicles");

        private final String name;

        CraftingCategory(String name) {
            this.name = name;
        }

        public static CraftingCategory getNextCategory(CraftingCategory current) {
            int i = current.ordinal();
            return (i + 1) == values().length ? values()[0] : values()[i + 1];
        }

        public static CraftingCategory getPrevCategory(CraftingCategory current) {
            int i = current.ordinal();
            return i > 0 ? values()[i - 1] : values()[values().length - 1];
        }

        public String getCategoryName() {
            return name;
        }
    }
}
