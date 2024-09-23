package com.airbnb.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserDto<A> {
    private List<AppUserDto> dto;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;
}
