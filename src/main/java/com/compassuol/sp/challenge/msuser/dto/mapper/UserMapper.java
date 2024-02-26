package com.compassuol.sp.challenge.msuser.dto.mapper;

import com.compassuol.sp.challenge.msuser.dto.user.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserResponseDto;
import com.compassuol.sp.challenge.msuser.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class UserMapper {





    public User createDtoToEntity(UserCreateDto userCreateDto) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(userCreateDto.getBirthdate(),dateFormat);
        return new User(userCreateDto.getFirstName(),
                userCreateDto.getLastName(),
                userCreateDto.getEmail(),
                date.atStartOfDay(),
                userCreateDto.getCpf(),
                userCreateDto.getCep(),
                userCreateDto.isActive(),
                userCreateDto.getPassword());
    }
    public UserResponseDto entityToResponse(User user) {

        SimpleDateFormat formatoIso = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            Date dataIso = formatoIso.parse(user.getBirthDate().toString());

            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

            String dataFormatada = formatoSaida.format(dataIso);
            return new UserResponseDto(user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getCpf(),
                    dataFormatada,
                    user.getEmail(),
                    user.getCep(),
                    user.isActive()
            );
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
