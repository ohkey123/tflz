package cn.shiyang.tflz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("创建倡议时的请求对象")
public class CreateProposeRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @Min(value = 1, message = "用户id不能小于1")
    private Integer uid;

    @ApiModelProperty("标题")
    @Length(min = 1, max = 30, message = "标题的长度在[1,30]")
    private String name;

    @ApiModelProperty("概述")
    @Length(min = 1, max = 200, message = "概述的长度在[1,200]")
    private String description;

    @ApiModelProperty("标签信息")
    @NotNull(message = "标签不能为空")
    private String tags;

    @ApiModelProperty("验证码")
    @NotNull(message = "验证码不能为空")
    private String checkCode;
}
