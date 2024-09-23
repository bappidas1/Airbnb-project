package com.airbnb.service;

import com.airbnb.dto.AppUserDto;
import com.airbnb.dto.LoginDto;
import com.airbnb.dto.UserDto;
import com.airbnb.entity.AppUser;
import com.airbnb.exception.ResourceNotFound;
import com.airbnb.exception.UserExist;
import com.airbnb.repository.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public AppUserServiceImpl(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AppUserDto createUserDetails(AppUserDto appUserDto) {
        AppUser appUserEntity = mapToEntity(appUserDto);
         Optional<AppUser> byEmail = appUserRepository.findByEmail(appUserEntity.getEmail());
        if (byEmail.isPresent()) {
            throw new UserExist("Email id exist");
        }
        Optional<AppUser> byUsername = appUserRepository.findByUsername(appUserEntity.getUsername());
        if (byUsername.isPresent()) {
            throw new UserExist("Username already exists");
        }
        String encodedPassword = BCrypt.hashpw(appUserEntity.getPassword(), BCrypt.gensalt(5));
        appUserEntity.setPassword(encodedPassword);
//        appUserEntity.setRole("ROLE_USER");
        AppUser savedEntity = appUserRepository.save(appUserEntity);
        AppUserDto savedDto = mapToDto(savedEntity);
        return savedDto;
    }

    @Override
    public String verifyLogIn(LoginDto loginDto) {
//        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        Optional<AppUser> opUser = appUserRepository.findByUsernameOrEmail(loginDto.getUsername(), loginDto.getUsername());
        if(opUser.isPresent()){
            AppUser appUser = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())){
                return jwtService.generateToken(appUser);
            }


        }
        return null;

    }

    @Override
    public AppUserDto createPropertyOwnerDetails(AppUserDto appUserDto) {
        AppUser appUserEntity = mapToEntity(appUserDto);
        Optional<AppUser> byEmail = appUserRepository.findByEmail(appUserEntity.getEmail());
        if (byEmail.isPresent()) {
            throw new UserExist("Email id exist");
        }
        Optional<AppUser> byUsername = appUserRepository.findByUsername(appUserEntity.getUsername());
        if (byUsername.isPresent()) {
            throw new UserExist("Username already exists");
        }
        String encodedPassword = BCrypt.hashpw(appUserEntity.getPassword(), BCrypt.gensalt(5));
        appUserEntity.setPassword(encodedPassword);
//        appUserEntity.setRole("ROLE_OWNER");
        AppUser savedEntity = appUserRepository.save(appUserEntity);
        AppUserDto savedDto = mapToDto(savedEntity);
        return savedDto;
    }

    @Override
    public AppUserDto createPropertyManagerDetails(AppUserDto appUserDto) {
        AppUser appUserEntity = mapToEntity(appUserDto);
        Optional<AppUser> byEmail = appUserRepository.findByEmail(appUserEntity.getEmail());
        if (byEmail.isPresent()) {
            throw new UserExist("Email id exist");
        }
        Optional<AppUser> byUsername = appUserRepository.findByUsername(appUserEntity.getUsername());
        if (byUsername.isPresent()) {
            throw new UserExist("Username already exists");
        }
        String encodedPassword = BCrypt.hashpw(appUserEntity.getPassword(), BCrypt.gensalt(5));
        appUserEntity.setPassword(encodedPassword);
//        appUserEntity.setRole("ROLE_MANAGER");
        AppUser savedEntity = appUserRepository.save(appUserEntity);
        AppUserDto savedDto = mapToDto(savedEntity);
        return savedDto;
    }

    @Override
    public String deleteUserDetails(long userId) {
        appUserRepository.deleteById(userId);
        return "User id is deleted";
    }

    @Override
    public AppUserDto updateUserDetails(AppUserDto appUserDto, long userId) {
        return null;
    }

    @Override
    public UserDto<AppUserDto> getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<AppUser> all = appUserRepository.findAll(pageable);
        List<AppUser> content = all.getContent();
        List<AppUserDto> collectDto = content.stream().map(u -> mapToDto(u)).collect(Collectors.toList());
        UserDto<AppUserDto> dtos = new UserDto<>();
        dtos.setDto(collectDto);
        dtos.setPageNo(pageable.getPageNumber());
        dtos.setPageSize(pageable.getPageSize());
        dtos.setTotalPages(all.getTotalPages());
        dtos.setLast(all.isLast());
        dtos.setFirst(all.isFirst());
        return dtos;
    }

    @Override
    public AppUserDto getUserById(long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with the given id : " + id)
        );
        AppUserDto dto = mapToDto(appUser);
        return dto;


    }

    @Override
    public AppUserDto updateUser(AppUserDto appUserDto, long id) {
        String hashpw = BCrypt.hashpw(appUserDto.getPassword(), BCrypt.gensalt(5));
        AppUser appUser = appUserRepository.findById(id).get();
        appUser.setName(appUserDto.getName());
        appUser.setEmail(appUserDto.getEmail());
        appUser.setPassword(hashpw);
        appUser.setUsername(appUserDto.getUsername());
        appUser.setRole(appUser.getRole());
        AppUser saved = appUserRepository.save(appUser);
        AppUserDto dto = mapToDto(saved);
        return dto;
    }

    AppUser mapToEntity(AppUserDto dto) {
        AppUser entity = new AppUser();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    AppUserDto mapToDto(AppUser entity) {
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
