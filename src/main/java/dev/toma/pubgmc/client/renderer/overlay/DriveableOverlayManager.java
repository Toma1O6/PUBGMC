package dev.toma.pubgmc.client.renderer.overlay;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import dev.toma.pubgmc.common.entity.vehicles.util.VehicleCategory;

import java.util.IdentityHashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public final class DriveableOverlayManager {

    public static final DriveableOverlayManager INSTANCE = new DriveableOverlayManager();
    private final Map<Class<? extends EntityDriveable>, DriveableOverlay<?>> overlays = new IdentityHashMap<>();
    private final Map<VehicleCategory, DriveableOverlay<?>> defaultOverlays = new IdentityHashMap<>();

    private DriveableOverlayManager() {
        // TODO add remaning categories once new vehicle types are being added
        this.defaultOverlays.put(VehicleCategory.LAND, new LandVehicleOverlay<>());
    }

    public <D extends EntityDriveable> void registerCustomOverlay(Class<D> type, DriveableOverlay<D> overlay) {
        this.overlays.put(type, overlay);
    }

    public <D extends EntityDriveable> DriveableOverlay<D> getOverlay(D vehicle) {
        Class<D> type = (Class<D>) vehicle.getClass();
        DriveableOverlay<D> overlay = this.getOverlayByType(type);
        if (overlay == null) {
            VehicleCategory category = vehicle.getVehicleCategory();
            return (DriveableOverlay<D>) this.defaultOverlays.get(category);
        }
        return overlay;
    }

    public <D extends EntityDriveable> DriveableOverlay<D> getOverlayByType(Class<D> type) {
        return (DriveableOverlay<D>) this.overlays.get(type);
    }
}
