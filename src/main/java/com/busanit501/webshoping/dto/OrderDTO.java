package com.busanit501.webshoping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long orderid;

    private Long memberid;

    private LocalDateTime orderdate;

    private boolean status;
}
