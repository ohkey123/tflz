package cn.shiyang.tflz.controller;


import cn.shiyang.tflz.entity.Notice;
import cn.shiyang.tflz.entity.User;
import cn.shiyang.tflz.service.NoticeService;
import cn.shiyang.tflz.utils.UtilsService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shiyang
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/tflz/notice")
@Api("通知前端控制器")
@Validated
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/read/{nid}")
    @ApiOperation("标记为已读信息")
    public void read(@PathVariable("nid")
                     @Valid @Min(value = 1, message = "通知id不能小于1")
                             Integer id) {
        UpdateWrapper<Notice> uw = new UpdateWrapper<>();
        uw.eq("id", id);
        uw.set("isUnread", false);
        noticeService.update(uw);
    }

    @PostMapping("/getRead/{uid}")
    @ApiOperation("通过用户id获得已读消息")
    public List getRead(@PathVariable("uid")
                        @Valid @Min(value = 1, message = "用户id不能小于1")
                                Integer uid) {
        return utilsService.getNoticeResponseDTOList(uid, false);
    }

    @PostMapping("/getUnread/{uid}")
    @ApiOperation("通过用户id获得未读消息")
    public List getUnread(@PathVariable("uid")
                          @Valid @Min(value = 1, message = "用户id不能小于1")
                                  Integer uid) {
        return utilsService.getNoticeResponseDTOList(uid, true);
    }
}

