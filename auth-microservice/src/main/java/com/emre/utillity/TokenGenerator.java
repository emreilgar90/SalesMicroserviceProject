package com.emre.utillity;

import com.emre.exception.AuthMicroserviceException;
import com.emre.exception.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {
    public String createToken(Long userid){
        String token = "Brr:"+userid;
        return token;
    }

    public Long decodeToken(String token){
        try{
            String userid = token.split(":")[1];
            return Long.parseLong(userid);
        }catch (Exception e){
            throw new AuthMicroserviceException(ErrorType.INTERNAL_ERROR);
        }
    }
}

