package io.github.tierneyjohn.eshow.service;

import io.github.tierneyjohn.eshow.dto.VideoDTO;
import io.github.tierneyjohn.eshow.entity.Video;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 视频文件服务接口
 * </p>
 * <p>create file on 2022/3/20</p>
 *
 * @author TierneyJohn
 */
public interface VideoService {

    /**
     * 通过视频 Code 获取视频信息
     * @param code 视频 Code
     * @return 详细信息
     */
    Video findVideoInfoByCode(String code);

    /**
     * 通过视频 Code 获取播放流
     * @param code 视频 Code
     * @param response 播放流
     */
    void findVideoIO(String code, HttpServletResponse response);

    /**
     * 视频上传方法
     * @param validator 待上传文件信息
     * @return 视频 code
     */
    String upload(VideoDTO validator);
}
