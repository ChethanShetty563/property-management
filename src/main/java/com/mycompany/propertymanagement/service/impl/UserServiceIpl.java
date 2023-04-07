package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceIpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {


        Optional<UserEntity> optUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());

        if(optUserEntity.isPresent()) {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL ALREADY EXIST");
            errorModel.setMessage("Email already registered");
            errorModels.add(errorModel);

            throw new BusinessException(errorModels);

        }
       UserEntity userEntity =  userConverter.convertDtotoEntity(userDTO);

       userEntity =  userRepository.save(userEntity);
       userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> userEntity = userRepository.findByOwnerEmailAndPassword(email,password);

        if(userEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(userEntity.get());
        }
        else {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect Email or Password");
            errorModels.add(errorModel);

            throw new BusinessException(errorModels);
        }

        return userDTO;
    }
}
