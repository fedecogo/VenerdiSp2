package fedeCapiz.spV2.controllers;

import fedeCapiz.spV2.entities.Device;
import fedeCapiz.spV2.payloads.NewDeviceDTO;
import fedeCapiz.spV2.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;



    //post
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody NewDeviceDTO body){
       return deviceService.save(body);
   }
    //get
    @GetMapping
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "4") int size,
                                   @RequestParam(defaultValue = "type") String sortBy) {
        return deviceService.getDevices(page, size, sortBy);
    }

    //finf by id
    @GetMapping("/{deviceId}")
    public Device getDeviceById(@PathVariable int deviceId) {
        return deviceService.getDeviceById(deviceId);
    }

    //put
    @PutMapping("/{deviceId}")
    public Device findAndupdate2(@PathVariable int deviceId, @RequestBody Device device) {
        return deviceService.findAndupdate2(deviceId, device);
    }

    //delate
    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete2(@PathVariable int deviceId) {
        deviceService.findAndDelete2(deviceId);
    }


}
