package cn.shiyang.tflz.service;

import cn.shiyang.tflz.entity.Game;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shiyang
 * @since 2021-02-24
 */
public interface GameService extends IService<Game> {
    void record(Integer uid, Integer score);

    List listPlayer();
}
