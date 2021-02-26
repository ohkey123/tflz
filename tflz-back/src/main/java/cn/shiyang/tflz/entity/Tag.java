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
@ApiModel(value = "Tag对象", description = "")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标签唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "Tag id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "标签名")
    @Length(min = 1, max = 20, message = "Tag name的长度在[1,20]")
    private String name;


}
