package fedeCapiz.spV2.services;

import fedeCapiz.spV2.entities.Device;
import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.exceptions.NotFoundException;
import fedeCapiz.spV2.payloads.NewDeviceDTO;
import fedeCapiz.spV2.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserService userService;

public Device save(NewDeviceDTO body) {
    Device newDevice = new Device();
    User user = userService.findById(body.user_id());
    newDevice.setType(body.type());
    newDevice.setStatus(body.status());
    newDevice.setUser(user);
      return deviceRepository.save(newDevice);
}
public Device getDeviceById(int id){
    return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
}
public Page<Device> getDevices(int page, int size, String sort){
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    return deviceRepository.findAll(pageable);
}
   public Device findAndupdate2(int id,Device body){
    Device found = this.getDeviceById(id);
    found.setType(body.getType());
    found.setStatus(body.getStatus());
    return deviceRepository.save(found);
   }
   public void findAndDelete2(int id){
    Device found = this.getDeviceById(id);
    deviceRepository.delete(found);
   }
}
