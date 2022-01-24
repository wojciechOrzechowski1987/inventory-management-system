package com.worzech.inventorymanagementsystem.mapper.user;


import com.worzech.inventorymanagementsystem.domain.User;
import com.worzech.inventorymanagementsystem.model.User.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Service
@Mapper()
public interface UserDtoMapper {

    UserDto toUserDto(User user);

    User fromUserDto(UserDto userDto);


}
