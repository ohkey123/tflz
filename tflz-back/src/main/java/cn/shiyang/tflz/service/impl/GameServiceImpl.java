package cn.shiyang.tflz.service.impl;

import cn.shiyang.tflz.dto.ShowGameScoreResponseDTO;
import cn.shiyang.tflz.entity.Game;
import cn.shiyang.tflz.entity.User;
import cn.shiyang.tflz.mapper.GameMapper;
import cn.shiyang.tflz.mapper.UserMapper;
import cn.shiyang.tflz.service.GameService;
import cn.shiyang.tflz.utils.UtilsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shiyang
 * @since 2021-02-24
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UtilsService utilsService;

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @Transactional
    public void record(Integer uid, Integer score) {
        // 更新用户活跃状态
        Date date = new Date();
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("id", uid);
        uw.set("time_modify", date);
        userMapper.update(null, uw);

        QueryWrapper<Game> qw = new QueryWrapper<>();
        qw.eq("uid", uid);
        Game cord = gameMapper.selectOne(qw);
        if (cord == null) {
            Game game = new Game();
            game.setUid(uid);
            game.setScore(score);
            game.setTimeCreate(date);
            gameMapper.insert(game);
            return;
        }

        if (score > cord.getScore()) {
            Game game = new Game();
            game.setUid(uid);
            game.setScore(score);
            game.setTimeCreate(date);
            gameMapper.insert(game);
        }
        return;
    }

    @Override
    public List listPlayer() {
        QueryWrapper<Game> qw = new QueryWrapper<>();
        qw.orderByDesc("score");
        List<Game> li = gameMapper.selectList(qw);
        List<ShowGameScoreResponseDTO> ret = new ArrayList<>(li.size());

        for (int i = 0; i < li.size(); i++) {
            ShowGameScoreResponseDTO sg = new ShowGameScoreResponseDTO();
            sg.setId(li.get(i).getId());
            sg.setRank(i + 1);
            sg.setScore(li.get(i).getScore());
            sg.setTimeCreate(format.format(li.get(i).getTimeCreate()));
            sg.setPlayer(utilsService.findUserThenToResponseDTO("id",
                    String.valueOf(li.get(i).getUid()), false));
            ret.add(sg);
        }

        return ret;
    }
}
