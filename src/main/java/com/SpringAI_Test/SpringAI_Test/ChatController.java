package com.SpringAI_Test.SpringAI_Test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatGPT")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/call")
    public ResponseEntity call(@RequestBody RequestDto requestDto) {

        return ResponseEntity.ok(chatService.question(requestDto));
    }
}
