package com.squad7.desafiolocadorasolutis.dto;

import com.squad7.desafiolocadorasolutis.enums.TermsStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class TermsResponseDto {
    private UUID id;
    private TermsStatusEnum termsStatusEnum;
    private LocalDateTime acceptAt;
}
