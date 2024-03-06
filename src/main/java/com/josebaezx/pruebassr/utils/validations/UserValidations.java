package com.josebaezx.pruebassr.utils.validations;

import com.josebaezx.pruebassr.models.dto.ResponseDto;
import com.josebaezx.pruebassr.models.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserValidations {

    public ResponseDto userValid(UserEntity user){
        ResponseDto response = new ResponseDto();
        response.setNumOfErros(0);
        if(user.getFirstname() == null
                || user.getFirstname().isEmpty()
                || user.getFirstname().length() < 3){
            response.setNumOfErros(response.getNumOfErros() + 1);
            response.setMessage("Please enter your first name, which should be almost 4 characters long");
        }

        if(user.getLastname() == null
                || user.getLastname().isEmpty()
                || user.getLastname().length() < 3){
            response.setNumOfErros(response.getNumOfErros() + 1);
            response.setMessage("Please enter your last name, which should be almost 4 characters long");
        }

        if(user.getLastname() == null
                || user.getLastname().isEmpty()
                || user.getLastname().length() < 3){
            response.setNumOfErros(response.getNumOfErros() + 1);
            response.setMessage("Please enter your last name, which should be almost 4 characters long");
        }



        return response;
    }
}
