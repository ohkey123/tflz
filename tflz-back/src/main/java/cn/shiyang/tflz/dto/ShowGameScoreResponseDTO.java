package cn.shiyang.tflz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "游戏分数展示DTO", description = "")
public class ShowGameScoreResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "游戏记录唯一id")
    private Integer id;

    @ApiModelProperty(value = "玩家")
    private UserLoginSucceedResponseDTO player;

    @ApiModelProperty(value = "游戏分数")
    private Integer score;

    @ApiModelProperty(value = "排名")
    private Integer rank;

    @ApiModelProperty(value = "记录创建时间")
    private String timeCreate;


}