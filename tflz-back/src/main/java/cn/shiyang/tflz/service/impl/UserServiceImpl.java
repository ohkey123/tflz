package cn.shiyang.tflz.service.impl;

import cn.shiyang.tflz.entity.User;
import cn.shiyang.tflz.mapper.UserMapper;
import cn.shiyang.tflz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiyang
 * @since 2021-01-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
