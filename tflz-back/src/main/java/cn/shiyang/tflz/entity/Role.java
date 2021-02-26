package cn.shiyang.tflz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

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
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "Role id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "角色名")
    @Length(min = 1, max = 15, message = "Role name的长度在[1,15]")
    private String name;

    @ApiModelProperty(value = "角色描述")
    @Length(min = 1, max = 50, message = "Role description的长度在[1,50]")
    private String description;


}
