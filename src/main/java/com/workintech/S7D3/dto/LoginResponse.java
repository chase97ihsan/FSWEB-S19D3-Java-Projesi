package com.workintech.S7D3.dto;

import com.workintech.S7D3.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Member member;
    private String token;
}
