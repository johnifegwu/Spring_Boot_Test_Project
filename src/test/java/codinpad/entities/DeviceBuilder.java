package codinpad.entities;

import java.time.ZonedDateTime;
import codinpad.entities.Device;

public class DeviceBuilder {
    private final Device device;

    public DeviceBuilder() {
        device = new Device();
        device.setVisibleId("test1101022");
        device.setFqdn("test1101022.company.com");
        device.setCreatedAt(ZonedDateTime.now());
    }

    public DeviceBuilder withAddDeviceVisibleId(String allDeviceVisibleId) {
        device.setVisibleId(allDeviceVisibleId);
        return this;
    }

    public DeviceBuilder withFqdn(String fqdn) {
        device.setFqdn(fqdn);
        return this;
    }

    public Device build() {
        return device;
    }

}