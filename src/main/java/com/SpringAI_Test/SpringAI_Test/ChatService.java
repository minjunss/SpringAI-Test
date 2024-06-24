package com.SpringAI_Test.SpringAI_Test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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

    public Flux<String> stream(RequestDto requestDto) {
        Prompt prompt = new Prompt(new UserMessage(requestDto.getMessage()));
        return extractContent(openAiChatModel.stream(prompt).cache());
    }

    private Flux<String> extractContent(Flux<ChatResponse> chatResponseFlux) {
        return chatResponseFlux.flatMap(chatResponse ->
                Flux.fromIterable(chatResponse.getResults())
                        .flatMap(resultObject -> {
                            String content = resultObject != null ? resultObject.getOutput().getContent() : null;

                            if (content != null && !content.isEmpty()) {
                                return Flux.just(content);
                            } else {
                                return Flux.empty();
                            }
                        })
        );
    }
}
