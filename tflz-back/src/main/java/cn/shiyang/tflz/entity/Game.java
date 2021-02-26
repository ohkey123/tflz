package cn.shiyang.tflz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author shiyang
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Game对象", description="")
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "游戏记录唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "Game id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "玩家id")
    @Min(value = 1, message = "User id不能小于1")
    private Integer uid;

    @ApiModelProperty(value = "游戏分数")
    @Min(value = 1, message = "Game score不能小于1")
    private Integer score;

    @ApiModelProperty(value = "记录创建时间")
    @NotNull(message = "Game 创建时间不能为空")
    private Date timeCreate;


}
