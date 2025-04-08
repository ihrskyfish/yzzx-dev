package com.wocai.platform.modules.business.book.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx-api
 * @description
 * @author: YouName
 * @create: 2023-07-12 16:37
 **/
@Data
public class BookListDto implements Serializable {
    private String username;

    private String userPhone;

    private String userId;

}
