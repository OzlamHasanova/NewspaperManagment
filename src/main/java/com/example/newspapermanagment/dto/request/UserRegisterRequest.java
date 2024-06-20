package com.example.newspapermanagment.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @NotBlank
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;

}