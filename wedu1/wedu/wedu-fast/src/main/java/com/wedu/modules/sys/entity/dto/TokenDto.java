package com.wedu.modules.sys.entity.dto;

import lombok.Data;

@Data
public class TokenDto {

    private String token;

    private int expire;
}
