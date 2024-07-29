package com.springbootwebassignment.Week2.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springbootwebassignment.Week2.Annotations.PasswordValidation;
import com.springbootwebassignment.Week2.Annotations.PrimeNumberValidation;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @JsonProperty("isActive")
    private Boolean isActive;

    @PasswordValidation
    private String Password;

    @PrimeNumberValidation
    private Integer checkPrime;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }
}
