package cn.shiyang.tflz.controller;


import cn.shiyang.tflz.dto.CreateReplyRequestDTO;
import cn.shiyang.tflz.service.ReplyService;
import cn.shiyang.tflz.utils.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shiyang
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/tflz/reply")
@Api("回复前端控制器")
@Validated
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/createReply")
    @ApiOperation("创建回复")
    public Map createReply(@RequestBody @Valid CreateReplyRequestDTO dto,
                           HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        // 检查验证码
        if (!utilsService.verifyCheckCode(map, dto.getCheckCode(), (String) session.getAttribute("checkCode"))) {
            return map;
        }

        if (!replyService.createReply(dto)) {
            return map;
        }

        map.put("isSucceed", true);
        return map;
    }
}

