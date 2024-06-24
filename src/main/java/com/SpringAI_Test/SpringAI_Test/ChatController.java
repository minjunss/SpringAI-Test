package com.SpringAI_Test.SpringAI_Test;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chatGPT")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/call")
    public ResponseEntity call(@RequestBody RequestDto requestDto) {

        return ResponseEntity.ok(chatService.question(requestDto));
    }

    @PostMapping("/stream")
    public Flux<String> stream(@RequestBody RequestDto requestDto) {
        return chatService.stream(requestDto);
    }
}
