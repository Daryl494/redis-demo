package com.daryl.controller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author：Daryl
 * date: 2023/1/10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String name;
    private Integer age;
}
