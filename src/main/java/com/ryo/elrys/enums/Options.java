package com.ryo.elrys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Options {
    WITHOUT_REQUEST("noReq");
    private final String options;
}
