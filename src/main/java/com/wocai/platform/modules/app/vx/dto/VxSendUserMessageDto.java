package com.wocai.platform.modules.app.vx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class VxSendUserMessageDto implements Serializable {

    String openid;
    String type;
    String templateId;
    String[] keywords;
}
