package com.airbnb.service;

import com.airbnb.dto.AppUserDto;
import com.airbnb.dto.LoginDto;
import com.airbnb.dto.UserDto;

public interface AppUserService {
    AppUserDto createUserDetails(AppUserDto appUserDto);

    String verifyLogIn(LoginDto loginDto);

    AppUserDto createPropertyOwnerDetails(AppUserDto appUserDto);

    AppUserDto createPropertyManagerDetails(AppUserDto appUserDto);

    String deleteUserDetails(long userId);

    AppUserDto updateUserDetails(AppUserDto appUserDto, long userId);

    UserDto<AppUserDto> getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);

    AppUserDto getUserById(long id);

    AppUserDto updateUser(AppUserDto appUserDto, long id);
}
