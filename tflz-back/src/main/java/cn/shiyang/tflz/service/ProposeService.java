package cn.shiyang.tflz.service;

import cn.shiyang.tflz.dto.CreateProposeRequestDTO;
import cn.shiyang.tflz.entity.Propose;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shiyang
 * @since 2021-01-26
 */
public interface ProposeService extends IService<Propose> {
    public boolean createPropose(CreateProposeRequestDTO proposeRequestDTO, String[] tagsArray, Map msgMap);
}
