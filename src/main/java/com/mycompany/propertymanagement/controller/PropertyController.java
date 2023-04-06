package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {


    @Autowired
    private PropertyService propertyService;


    @PostMapping("/properties")
    public ResponseEntity saveProperty(@RequestBody PropertyDTO propertyDTO) {

        propertyDTO = propertyService.saveProperty(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);

        return responseEntity;

    }

    @GetMapping("/properties")
    public  ResponseEntity<List<PropertyDTO>> getAllProperties() {
      List<PropertyDTO> propertyList =  propertyService.getAllProperties();
      ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(propertyList, HttpStatus.OK);
      return responseEntity;
    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
      PropertyDTO updatedDto =   propertyService.updateProperty(propertyDTO, propertyId);
      return new ResponseEntity<>(updatedDto, HttpStatus.CREATED);

    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public  ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId) {
        PropertyDTO updatedDto =   propertyService.updatePropertyDescription(propertyDTO, propertyId);
        return new ResponseEntity<PropertyDTO>(updatedDto, HttpStatus.CREATED);
    }

    @PatchMapping("/properties/update-price/{propertyId}")
    public  ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId) {
        PropertyDTO updatedDto =   propertyService.updatePropertyPrice(propertyDTO, propertyId);
        return new ResponseEntity<PropertyDTO>(updatedDto, HttpStatus.CREATED);
    }


    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId) {
      propertyService.deleteProperty(propertyId);
      ResponseEntity<Void> responseEntity =  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return responseEntity;
    }




}
