package dev.toma.pubgmc.api.client.util;

import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.properties.PropertyType;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public final class ScoreboardRenderer {

    private final Sorter<?> sorter;
    private final List<Table.Column<UUID>> columns;

    private final Table<UUID> table;
    private boolean renderMyScore;
    private int displayLimit = 5;

    private ScoreboardRenderer(Builder builder) {
        this.sorter = builder.sorter;
        this.columns = builder.columns;
        this.table = new Table<>();
        this.columns.forEach(table::addColumn);
    }

    public static Builder create() {
        return new Builder();
    }

    public void setMyScoreAlwaysRendered(boolean renderMyScore) {
        this.renderMyScore = renderMyScore;
    }

    public void setDisplayLimit(int displayLimit) {
        this.displayLimit = displayLimit;
    }

    public void setDrawGrid(boolean drawGrid) {
        table.setDrawGrid(drawGrid);
    }

    public void renderScoreboard(PlayerPropertyHolder propertyHolder, int x, int y) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        Minecraft minecraft = Minecraft.getMinecraft();
        Entity entity = minecraft.getRenderViewEntity();
        if (entity == null)
            return;
        UUID ownerId = entity.getUniqueID();
        List<UUID> list = sorter.sort(propertyHolder);
        List<UUID> partial = list.subList(0, Math.min(displayLimit, list.size()));
        boolean hasMyScore = partial.contains(ownerId);
        if (!hasMyScore) {
            partial.remove(partial.size() - 1);
            partial.add(ownerId);
        }
        table.setRows(partial);
        FontRenderer font = minecraft.fontRenderer;
        table.render(font, propertyHolder, x, y);
    }

    public static final class Builder {

        private Sorter<?> sorter;
        private final List<Table.Column<UUID>> columns = new ArrayList<>();

        private Builder() {}

        public <T> Builder withSorting(PropertyType<T> type, Comparator<T> comparator, T defaultValue) {
            this.sorter = new Sorter<>(type, comparator, defaultValue);
            return this;
        }

        public Builder addRenderableColumn(String name, BiFunction<PlayerPropertyHolder, UUID, String> provider, Consumer<Table.Column<UUID>> consumer) {
            Table.Column<UUID> column = new Table.Column<>(name, provider);
            consumer.accept(column);
            columns.add(column);
            return this;
        }

        public Builder addRenderableColumn(String name, BiFunction<PlayerPropertyHolder, UUID, String> provider) {
            return addRenderableColumn(name, provider, t -> {});
        }

        public ScoreboardRenderer build() {
            return new ScoreboardRenderer(this);
        }
    }

    private static final class Sorter<T> {
        private final PropertyType<T> type;
        private final Comparator<T> comparator;
        private final T defaultValue;

        public Sorter(PropertyType<T> type, Comparator<T> comparator, T defaultValue) {
            this.type = type;
            this.comparator = comparator;
            this.defaultValue = defaultValue;
        }

        public List<UUID> sort(PlayerPropertyHolder holder) {
            return holder.getSortedOwners(type, comparator, defaultValue);
        }
    }

    public static final class Table<T> {

        private static final int DEFAULT_COLUMN_SIZE = 40;
        private static final int COLUMN_HEIGHT = 12;

        private List<T> rows = new ArrayList<>();
        private final List<Column<T>> columns = new ArrayList<>();
        private final List<Integer> columnSizes = new ArrayList<>();
        private boolean drawGrid;

        public void addColumn(Column<T> column) {
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

        public void render(FontRenderer font, PlayerPropertyHolder props, int x, int y) {
            int colOffset = 0;
            for (int i = 0; i < columns.size(); i++) {
                Column<T> column = columns.get(i);
                int rowIndex = 0;
                boolean shrinkAllowed = column.allowSizeShrinking;
                for (T row : rows) {
                    String value = row != null ? column.getValue(props, row) : column.name;
                    int columnSize = columnSizes.get(i);
                    int valueSize = font.getStringWidth(value) + 1;
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
                    boolean shadow = row == null;
                    if (column.rightAlignment) {
                        font.drawString(value, colLeft + columnSize - valueSize - 0.5F, colTop + 2, column.textColor, shadow);
                    } else {
                        font.drawString(value, colLeft + 0.5F, colTop + 2, column.textColor, shadow);
                    }
                }
                colOffset += columnSizes.get(i);
            }
            if (drawGrid) {
                int tableWidth = columnSizes.stream().mapToInt(Integer::intValue).sum();
                int tableHeight = COLUMN_HEIGHT * rows.size();
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

        public static final class Column<T> {

            private final String name;
            private final BiFunction<PlayerPropertyHolder, T, String> value;

            private boolean allowSizeShrinking;
            private int textColor = 0xFFFFFF;
            private boolean rightAlignment = false;

            public void setAllowSizeShrinking(boolean allowSizeShrinking) {
                this.allowSizeShrinking = allowSizeShrinking;
            }

            public void setTextColor(int textColor) {
                this.textColor = textColor;
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
