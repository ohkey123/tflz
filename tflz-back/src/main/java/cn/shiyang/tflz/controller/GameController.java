package cn.shiyang.tflz.controller;


import cn.shiyang.tflz.service.GameService;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Range;
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
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/tflz/game")
@Api("游戏模块前端控制器")
@Validated
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/record/{uid}/{score}")
    public void record(@PathVariable("uid")
                       @Valid @Min(value = 1, message = "用户id不能小于1")
                               Integer uid,
                       @PathVariable("score")
                       @Valid @Range(min = 0, max = 60, message = "作弊失败！")
                               Integer score) {
        gameService.record(uid, score);
    }

    @PostMapping("/listPlayer")
    public List listPlayer() {
        return gameService.listPlayer();
    }
}

