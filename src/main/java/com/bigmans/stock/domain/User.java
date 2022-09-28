package com.bigmans.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    @NotNull
    private int id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    private String name;
}
