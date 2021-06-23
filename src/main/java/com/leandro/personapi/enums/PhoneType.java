package com.leandro.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {
    HOME("Home"),
    PRIVATE("Mobile"),
    COMMERCIAL("Commercial");

    private  final String description;
}
