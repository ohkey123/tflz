package cn.shiyang.tflz.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author shiyang
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Propose对象", description = "")
public class Propose implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "倡议唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "Propose id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "倡议创建者id")
    @Min(value = 1, message = "User id不能小于1")
    private Integer uid;

    @ApiModelProperty(value = "倡议主题")
    @Length(min = 1, max = 30, message = "Propose name的长度在[1,30]")
    private String name;

    @ApiModelProperty(value = "倡议概述")
    @Length(min = 1, max = 200, message = "Propose description的长度在[1,200]")
    private String description;

    @ApiModelProperty(value = "倡议点赞数")
    @Min(value = 0, message = "点赞数不能小于0")
    private Integer cntLike;

    @ApiModelProperty(value = "倡议创建时间")
    @NotNull(message = "Propose创建时间不能为空")
    private Date timeCreate;

    @ApiModelProperty(value = "倡议最后一次回复时间")
    @NotNull(message = "Propose最后回复时间不能为空")
    private Date timeReply;


}
