package dev.toma.pubgmc.api.client.util;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.properties.PropertyType;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.ToIntBiFunction;

public final class ScoreboardRenderer<G extends Game<?>> {

    private final Sorter<?, G> sorter;

    private final Table<UUID, G> table;
    private boolean renderMyScore;
    private int displayLimit = 5;

    private ScoreboardRenderer(Builder<G> builder) {
        this.sorter = builder.sorter;
        List<Table.Column<UUID, G>> columns = builder.columns;
        this.table = new Table<>();
        columns.forEach(table::addColumn);
    }

    public static <G extends Game<?>> Builder<G> create() {
        return new Builder<>();
    }

    public void setMyScoreAlwaysRendered(boolean renderMyScore) {
        this.renderMyScore = renderMyScore;
    }

    public void setTextMargin(int textMargin) {
        table.setMargin(textMargin);
    }

    public void setDisplayLimit(int displayLimit) {
        this.displayLimit = displayLimit;
    }

    public void setDrawGrid(boolean drawGrid) {
        table.setDrawGrid(drawGrid);
    }

    public void renderScoreboard(PlayerPropertyHolder propertyHolder, G game, ScaledResolution resolution) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        Minecraft minecraft = Minecraft.getMinecraft();
        Entity entity = minecraft.getRenderViewEntity();
        if (entity == null)
            return;
        UUID ownerId = entity.getUniqueID();
        List<UUID> list = sorter.get(propertyHolder, game);
        List<UUID> partial = list.subList(0, Math.min(displayLimit, list.size()));
        boolean hasMyScore = partial.contains(ownerId);
        if (!hasMyScore && renderMyScore) {
            partial.remove(partial.size() - 1);
            partial.add(ownerId);
        }
        table.setRows(partial);
        FontRenderer font = minecraft.fontRenderer;
        int tWidth = table.getTableWidth();
        int tHeight = table.getTableHeight();
        int x = (resolution.getScaledWidth() - tWidth) / 2;
        int y = (resolution.getScaledHeight() - tHeight) / 2;
        table.render(font, propertyHolder, game, x, y);
    }

    public static final class Builder<G extends Game<?>> {

        private Sorter<?, G> sorter;
        private final List<Table.Column<UUID, G>> columns = new ArrayList<>();

        private Builder() {}

        public <T> Builder<G> withSorting(PropertyType<T> type, Comparator<T> comparator, Sorter.CollectionProvider<G> provider, T defaultValue) {
            this.sorter = new Sorter<>(type, comparator, provider, defaultValue);
            return this;
        }

        @SuppressWarnings("unchecked")
        public <T> Builder<G> withSorting(PropertyType<T> type, Comparator<T> comparator, T defaultValue) {
            return withSorting(type, comparator, (sorter, holder, game) -> {
                Sorter<T, G> sort = (Sorter<T, G>)  sorter;
                return holder.getSortedOwners(sort.getType(), sort.getComparator(), defaultValue);
            }, defaultValue);
        }

        public Builder<G> addRenderableColumn(String name, BiFunction<PlayerPropertyHolder, UUID, String> provider, Consumer<Table.Column<UUID, G>> consumer) {
            Table.Column<UUID, G> column = new Table.Column<>(name, provider);
            consumer.accept(column);
            columns.add(column);
            return this;
        }

        public Builder<G> addRenderableColumn(String name, BiFunction<PlayerPropertyHolder, UUID, String> provider) {
            return addRenderableColumn(name, provider, t -> {});
        }

        public ScoreboardRenderer<G> build() {
            return new ScoreboardRenderer<>(this);
        }
    }

    public static final class Sorter<T, G extends Game<?>> {
        private final PropertyType<T> type;
        private final Comparator<T> comparator;
        private final CollectionProvider<G> provider;
        private final T defaultValue;

        private Sorter(PropertyType<T> type, Comparator<T> comparator, CollectionProvider<G> provider, T defaultValue) {
            this.type = type;
            this.comparator = comparator;
            this.provider = provider;
            this.defaultValue = defaultValue;
        }

        public List<UUID> get(PlayerPropertyHolder holder, G game) {
            return provider.getList(this, holder, game);
        }

        public PropertyType<T> getType() {
            return type;
        }

        public Comparator<T> getComparator() {
            return comparator;
        }

        public T getDefaultValue() {
            return defaultValue;
        }

        public interface CollectionProvider<G extends Game<?>> {
            List<UUID> getList(Sorter<?, G> sorter, PlayerPropertyHolder properties, G game);
        }
    }

    public static final class Table<T, G extends Game<?>> {

        private static final int DEFAULT_COLUMN_SIZE = 75;
        private static final int COLUMN_HEIGHT = 12;

        private List<T> rows = new ArrayList<>();
        private final List<Column<T, G>> columns = new ArrayList<>();
        private final List<Integer> columnSizes = new ArrayList<>();
        private boolean drawGrid;
        private int margin = 2;

        public void addColumn(Column<T, G> column) {
            columns.add(column);
            columnSizes.add(DEFAULT_COLUMN_SIZE);
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
            this.rows.add(0, null);
        }

        public void setDrawGrid(boolean drawGrid) {
            this.drawGrid = drawGrid;
        }

        public void setMargin(int margin) {
            this.margin = margin;
        }

        public void render(FontRenderer font, PlayerPropertyHolder props, G game, int x, int y) {
            int colOffset = 0;
            for (int i = 0; i < columns.size(); i++) {
                Column<T, G> column = columns.get(i);
                int rowIndex = 0;
                boolean shrinkAllowed = column.allowSizeShrinking;
                for (T row : rows) {
                    boolean isName = row == null;
                    String value = !isName ? column.getValue(props, row) : column.name;
                    int columnSize = columnSizes.get(i);
                    int valueSize = font.getStringWidth(value) + margin * 2;
                    if (valueSize > columnSize) {
                        columnSizes.set(i, valueSize);
                        columnSize = valueSize;
                    }
                    if (valueSize < columnSize && shrinkAllowed) {
                        shrinkAllowed = false;
                        columnSizes.set(i, valueSize);
                        columnSize = valueSize;
                    }
                    int colLeft = x + colOffset;
                    int colTop = y + rowIndex++ * COLUMN_HEIGHT;
                    ImageUtil.drawShape(colLeft, colTop, colLeft + columnSize, colTop + COLUMN_HEIGHT, 0x66 << 24);
                    if (isName) {
                        font.drawStringWithShadow(value, colLeft + (columnSize - valueSize) / 2.0F, colTop + 2, column.textColor.applyAsInt(game, null));
                    } else {
                        if (column.rightAlignment) {
                            font.drawString(value, colLeft + columnSize - valueSize - margin, colTop + 2, column.textColor.applyAsInt(game, row), false);
                        } else {
                            font.drawString(value, colLeft + margin, colTop + 2, column.textColor.applyAsInt(game, row), false);
                        }
                    }
                }
                colOffset += columnSizes.get(i);
            }
            if (drawGrid) {
                int tableWidth = getTableWidth();
                int tableHeight = getTableHeight();
                for (int r = 1; r < rows.size(); r++) {
                    float posY = y + COLUMN_HEIGHT * r - 0.5F;
                    ImageUtil.drawShape(x, posY, x + tableWidth, posY + 1.0F, 0xFFFFFFFF);
                }
                float posX = x;
                for (int i = 0; i < columns.size() - 1; i++) {
                    posX = posX + columnSizes.get(i);
                    ImageUtil.drawShape(posX - 0.5F, y, posX + 0.5F, y + tableHeight, 0xFFFFFFFF);
                }
            }
        }

        int getTableWidth() {
            return columnSizes.stream().mapToInt(Integer::intValue).sum();
        }

        int getTableHeight() {
            return COLUMN_HEIGHT * rows.size();
        }

        public static final class Column<T, G extends Game<?>> {

            private final String name;
            private final BiFunction<PlayerPropertyHolder, T, String> value;

            private boolean allowSizeShrinking;
            private ToIntBiFunction<G, T> textColor = (game, type) -> 0xFFFFFF;
            private boolean rightAlignment = false;

            public void setAllowSizeShrinking(boolean allowSizeShrinking) {
                this.allowSizeShrinking = allowSizeShrinking;
            }

            public void setTextColor(ToIntBiFunction<G, T> textColor) {
                this.textColor = textColor;
            }

            public void setTextColor(int textColor) {
                this.textColor = (game, type) -> textColor;
            }

            public void setRightAlignment(boolean rightAlignment) {
                this.rightAlignment = rightAlignment;
            }

            public Column(String name, BiFunction<PlayerPropertyHolder, T, String> value) {
                this.name = name;
                this.value = value;
            }

            public String getValue(PlayerPropertyHolder propertyHolder, T t) {
                return value.apply(propertyHolder, t);
            }
        }
    }
}
