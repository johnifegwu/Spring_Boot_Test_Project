package codinpad.entities;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "devices")
public class Device  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "all_device_visible_id")
    private String visibleId;

    @Column(name = "all_device_type")
    private String deviceType;

    @Column(name = "all_device_internal_id")
    private String internalId;

    @Column(name = "zone_building_internal_id")
    private String buildingInternalId;

    @Column(name = "cabinet_visible_id")
    private String cabinetVisibleId;

    @Column(name = "zone_room")
    private String zoneRoom;

    @Column(name = "zone_room_internal_id")
    private String zoneRoomInternalId;

    @Column(name = "element_last_modify_date")
    private ZonedDateTime elementLastModifyDate;

    @Column(name = "cabinet_id")
    private String cabinetId;

    @Column(name = "all_device_function")
    private String function;

    @Column(name = "created_at")
    @CreatedDate
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private ZonedDateTime updatedAt;

    @Column(name = "all_device_power_switching_status")
    private String powerSwitchingStatus;

    @Column(name = "all_device_manufacturer")
    private String manufacturer;

    @Column(name = "zone_campus_internal_id")
    private String zoneCampusInternalId;

    @Column(name = "zone_floor_internal_id")
    private String zoneFloorInternalId;

    @Column(name = "zone_internal_id")
    private String zoneInternalId;

    @Column(name = "all_device_cwarranty_end")
    private ZonedDateTime cWarrantyEnd;

    @Column(name = "all_device_power_consumption")
    private String powerConsumption;

    @Column(name = "all_device_heat_emission")
    private String heatEmission;

    @Column(name = "all_device_status_action")
    private Integer statusAction;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @Column(name= "fqdn")
    private String fqdn;

    @Column(name = "cmc_managed_device")
    private Boolean cmcManagedDevice = Boolean.FALSE;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", fetch = FetchType.LAZY)
    private List<Certificate> certificates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id) && Objects.equals(visibleId, device.visibleId) && Objects.equals(deviceType, device.deviceType) && Objects.equals(internalId, device.internalId) && Objects.equals(buildingInternalId, device.buildingInternalId) && Objects.equals(cabinetVisibleId, device.cabinetVisibleId) && Objects.equals(zoneRoom, device.zoneRoom) && Objects.equals(zoneRoomInternalId, device.zoneRoomInternalId) && Objects.equals(elementLastModifyDate, device.elementLastModifyDate) && Objects.equals(cabinetId, device.cabinetId) && Objects.equals(function, device.function) && Objects.equals(createdAt, device.createdAt) && Objects.equals(updatedAt, device.updatedAt) && Objects.equals(powerSwitchingStatus, device.powerSwitchingStatus) && Objects.equals(manufacturer, device.manufacturer) && Objects.equals(zoneCampusInternalId, device.zoneCampusInternalId) && Objects.equals(zoneFloorInternalId, device.zoneFloorInternalId) && Objects.equals(zoneInternalId, device.zoneInternalId) && Objects.equals(cWarrantyEnd, device.cWarrantyEnd) && Objects.equals(powerConsumption, device.powerConsumption) && Objects.equals(heatEmission, device.heatEmission) && Objects.equals(statusAction, device.statusAction) && Objects.equals(isDeleted, device.isDeleted) && Objects.equals(fqdn, device.fqdn) && Objects.equals(cmcManagedDevice, device.cmcManagedDevice) && Objects.equals(certificates, device.certificates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, visibleId, deviceType, internalId, buildingInternalId, cabinetVisibleId, zoneRoom, zoneRoomInternalId, elementLastModifyDate, cabinetId, function, createdAt, updatedAt, powerSwitchingStatus, manufacturer, zoneCampusInternalId, zoneFloorInternalId, zoneInternalId, cWarrantyEnd, powerConsumption, heatEmission, statusAction, isDeleted, fqdn, cmcManagedDevice, certificates);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", visibleId='" + visibleId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", internalId='" + internalId + '\'' +
                ", buildingInternalId='" + buildingInternalId + '\'' +
                ", cabinetVisibleId='" + cabinetVisibleId + '\'' +
                ", zoneRoom='" + zoneRoom + '\'' +
                ", zoneRoomInternalId='" + zoneRoomInternalId + '\'' +
                ", elementLastModifyDate=" + elementLastModifyDate +
                ", cabinetId='" + cabinetId + '\'' +
                ", function='" + function + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", powerSwitchingStatus='" + powerSwitchingStatus + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", zoneCampusInternalId='" + zoneCampusInternalId + '\'' +
                ", zoneFloorInternalId='" + zoneFloorInternalId + '\'' +
                ", zoneInternalId='" + zoneInternalId + '\'' +
                ", cWarrantyEnd=" + cWarrantyEnd +
                ", powerConsumption='" + powerConsumption + '\'' +
                ", heatEmission='" + heatEmission + '\'' +
                ", statusAction=" + statusAction +
                ", isDeleted=" + isDeleted +
                ", fqdn='" + fqdn + '\'' +
                ", cmcManagedDevice=" + cmcManagedDevice +
                ", certificates=" + certificates +
                '}';
    }
}