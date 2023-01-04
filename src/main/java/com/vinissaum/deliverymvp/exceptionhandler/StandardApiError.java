package com.vinissaum.deliverymvp.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardApiError {

    private Integer status;
    private String type;
    private String title;
    private String detail;

}
