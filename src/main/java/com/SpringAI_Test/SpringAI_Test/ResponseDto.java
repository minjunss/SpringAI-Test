package com.SpringAI_Test.SpringAI_Test;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDto {
    private String question;
    private String answer;
}
