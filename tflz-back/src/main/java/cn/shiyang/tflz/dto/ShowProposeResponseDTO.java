package cn.shiyang.tflz.dto;

import cn.shiyang.tflz.entity.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Propose对象", description = "")
public class ShowProposeResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "倡议唯一id")
    private Integer id;

    @ApiModelProperty(value = "倡议创建者")
    private UserLoginSucceedResponseDTO builder;

    @ApiModelProperty(value = "倡议标签")
    private List<Tag> tags;

    @ApiModelProperty(value = "倡议主题")
    private String name;

    @ApiModelProperty(value = "倡议概述")
    private String description;

    @ApiModelProperty(value = "倡议点赞数")
    private Integer cntLike;

    @ApiModelProperty(value = "倡议创建时间")
    private String timeCreate;

    @ApiModelProperty(value = "倡议最后一次回复时间")
    private String timeReply;

    @ApiModelProperty(value = "倡议所有回复（在为Community主页展示时为null，展示Propose详情时填充）")
    private List<ShowReplyResponseDTO> replies;
}