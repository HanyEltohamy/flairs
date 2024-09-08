package com.ft.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Email(message = "Email is not in a valid format!")
    @NotBlank(message = "Email is required!")
    private String mail;

    @NotBlank(message = "Name is required!")
    @Size(min = 3, message = "Name must have at least 3 characters!")
    @Size(max = 20, message = "Name can have at most 20 characters!")
    private String name;
}
