package com.example.newspapermanagment.dto.response;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Getter
public class UserResponse {
    private String token;
}
