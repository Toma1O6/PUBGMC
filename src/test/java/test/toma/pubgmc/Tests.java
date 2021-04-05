package test.toma.pubgmc;

import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Tests {

    @Test
    public void testAttachmentTypeSlots() {
        AttachmentType<?>[] types = AttachmentType.allTypes;
        for (AttachmentType<?> type : types) {
            for (AttachmentType<?> type1 : types) {
                if(type == type1)
                    continue;
                int x1 = type.getX();
                int y1 = type.getY();
                int x2 = type1.getX();
                int y2 = type1.getY();
                int diffX = Math.abs(x1 - x2);
                int diffY = Math.abs(y1 - y2);
                assertTrue(diffX > 16 || diffY > 16);
            }
        }
    }
}
