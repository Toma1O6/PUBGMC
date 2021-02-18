package dev.toma.pubgmc.util.recipes;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PMCRecipe {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/recipebase.png");
    public final Item result;
    public final int resultCount;
    public final PMCIngredient[] ingredients;
    public final CraftingCategory category;
    public final ItemStack returnStack;

    protected PMCRecipe(Item result, int amount, PMCIngredient[] ingredients, CraftingCategory category) {
        this(result, amount, ingredients, category, ItemStack.EMPTY);
    }

    protected PMCRecipe(Item result, PMCIngredient[] ingredients, CraftingCategory category, ItemStack returnStack) {
        this(result, 1, ingredients, category, returnStack);
    }

    protected PMCRecipe(Item result, int amount, PMCIngredient[] ingredients, CraftingCategory category, ItemStack returnStack) {
        this.result = result;
        this.resultCount = amount;
        this.ingredients = ingredients;
        this.category = category;
        this.returnStack = returnStack;
    }

    public static boolean isRecipeReady(PMCRecipe recipe, ICraftingInventory inv) {
        if (recipe == null || recipe.ingredients.length == 0) {
            return false;
        }
        for (PMCIngredient ingredient : recipe.ingredients) {
            ItemStack stack = inv.getStackInSlot(ingredient.slotIndex);
            if (stack.isEmpty() || stack.getItem() != ingredient.getIngredient().getItem() || stack.getCount() < ingredient.getIngredient().getCount()) {
                return false;
            }
        }
        return true;
    }

    public static boolean areSameRecipes(PMCRecipe r1, PMCRecipe r2) {
        if (r1.ingredients.length != r2.ingredients.length) {
            return false;
        } else {
            for (int i = 0; i < r1.ingredients.length; i++) {
                PMCIngredient i1 = r1.ingredients[i];
                PMCIngredient i2 = r2.ingredients[i];
                if (i1.getIngredient() == i2.getIngredient()) {
                    if (i1.slotIndex == i2.slotIndex) {
                        if (i1.getIngredient().getCount() == i2.getIngredient().getCount()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
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
        ImageUtil.drawCustomSizedImage(Minecraft.getMinecraft(), TEXTURE, mouseX, mouseY, 50, 86, false);
        for (PMCIngredient ing : ingredients) {
            int x = ing.slotIndex % 2 == 0 ? mouseX + 8 : mouseX + 26;
            int y = ing.slotIndex / 2 * 18 + mouseY + 8;
            RenderHelper.enableGUIStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(ing.getIngredient(), x, y);
            RenderHelper.disableStandardItemLighting();
            boolean flag = ing.getIngredient().getCount() >= 10;
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(ing.getIngredient().getCount() + "", flag ? x + 5 : x + 11, y + 9, 0xFFFFFF);
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

        private CraftingCategory(String name) {
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
