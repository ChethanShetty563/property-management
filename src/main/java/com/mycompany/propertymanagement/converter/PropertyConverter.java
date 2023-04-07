package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity propertyDTOtoEntity(PropertyDTO propertyDTO) {
        PropertyEntity pe = new PropertyEntity();
        pe.setTitle(propertyDTO.getTitle());
        pe.setDescription(propertyDTO.getDescription());
        pe.setDescription(propertyDTO.getDescription());
        pe.setPrice(propertyDTO.getPrice());
        pe.setAddress(propertyDTO.getAddress());

        return pe;
    }

    public PropertyDTO propertyEntitytoDTO(PropertyEntity propertyEntity) {
        PropertyDTO pdto = new PropertyDTO();
        pdto.setId(propertyEntity.getId());
        pdto.setTitle(propertyEntity.getTitle());
        pdto.setDescription(propertyEntity.getDescription());
        pdto.setDescription(propertyEntity.getDescription());
        pdto.setPrice(propertyEntity.getPrice());
        pdto.setAddress(propertyEntity.getAddress());

        return pdto;
    }
}
