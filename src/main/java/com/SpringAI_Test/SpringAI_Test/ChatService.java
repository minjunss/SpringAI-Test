package com.SpringAI_Test.SpringAI_Test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final OpenAiChatModel openAiChatModel;

    public ResponseDto question(RequestDto requestDto) {
        String answer = openAiChatModel.call(requestDto.getMessage());

        ResponseDto response = ResponseDto.builder()
                .question(requestDto.getMessage())
                .answer(answer)
                .build();

        return response;
    }
}
