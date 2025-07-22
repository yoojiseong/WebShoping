package com.busanit501.webshoping.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class productRequestDTO {



        @NotBlank(message = "상품명은 필수 입력 값입니다.")
        private String productName;

        @NotNull(message = "가격은 필수 입력 값입니다.")
        @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
        private Integer price;

        @NotNull(message = "재고는 필수 입력 값입니다.")
        @Min(value = 0, message = "재고는 0 이상이어야 합니다..")

        private Integer stock;

        private String productTag;


}
