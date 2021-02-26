package cn.shiyang.tflz.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Notice展示对象", description = "")
public class ShowNoticeResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知唯一id")
    private Integer id;

    @ApiModelProperty(value = "倡议名")
    private String pname;

    @ApiModelProperty("倡议id")
    private Integer pid;

    @ApiModelProperty("发起用户名")
    private String sname;

    @ApiModelProperty("发起用户id")
    private Integer sid;

    @ApiModelProperty(value = "为1表示未读，为0表示已读")
    private Boolean isUnread;

    @ApiModelProperty(value = "回复创建时间")
    private String timeCreate;


}
