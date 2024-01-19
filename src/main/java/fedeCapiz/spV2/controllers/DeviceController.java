package fedeCapiz.spV2.controllers;

import fedeCapiz.spV2.entities.Device;
import fedeCapiz.spV2.payloads.NewDeviceDTO;
import fedeCapiz.spV2.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody NewDeviceDTO body){
       return deviceService.save(body);
   }


}
