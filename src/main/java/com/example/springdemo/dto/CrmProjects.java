package com.example.springdemo.dto;

import com.example.springdemo.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CrmProjects {

    private User user;
    private int id;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String title;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String description;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String techstack;

    @Pattern(regexp="(^$|[0-9]{5})", message = "should be between 0-9 digits")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String investment;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String duration;


}
