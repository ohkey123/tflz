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
@ApiModel(value="Permission对象", description="")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "Permission id不能为空")
    private Integer id;

    @ApiModelProperty(value = "权限名")
    @Length(min = 1, max = 15, message = "Permission name的长度在[1,15]")
    private String name;

    @ApiModelProperty(value = "权限描述")
    @Length(min = 1, max = 50, message = "Permission description的长度在[1,50]")
    private String description;


}
