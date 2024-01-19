package fedeCapiz.spV2.services;

import fedeCapiz.spV2.entities.Device;
import fedeCapiz.spV2.payloads.NewDeviceDTO;
import fedeCapiz.spV2.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
public Device save(NewDeviceDTO body) {
    Device newDevice = new Device();
    newDevice.setType(body.type());
    newDevice.setStatus(body.status());
      return deviceRepository.save(newDevice);
}
}
