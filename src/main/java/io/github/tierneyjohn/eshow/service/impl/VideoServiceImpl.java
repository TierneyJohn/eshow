package io.github.tierneyjohn.eshow.service.impl;

import com.alibaba.fastjson.JSON;
import io.github.tierneyjohn.eshow.common.exception.FileException;
import io.github.tierneyjohn.eshow.entity.Video;
import io.github.tierneyjohn.eshow.repository.VideoRepository;
import io.github.tierneyjohn.eshow.service.FileService;
import io.github.tierneyjohn.eshow.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * 视频文件服务实现类
 * </p>
 * <p>create file on 2022/3/20</p>
 *
 * @author TierneyJohn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    private final FileService fileService;

    @Override
    public Video findVideoInfoByCode(String code) {
        log.info("视频信息获取方法: <== 请求码: {}", code);
        Video video = videoRepository.findOneByCode(code);
        log.info("视频信息获取成功: ==> 视频信息: {}", JSON.toJSONString(video));
        return video;
    }

    @Override
    public void findVideoIO(String code, HttpServletResponse response) {
        log.info("视频流获取方法: <== 请求码: {}", code);
        Video video = findVideoInfoByCode(code);

        if (Objects.isNull(video)) {
            log.error("视频流获取方法: ======> 请求码不存在: {}", code);
            throw new FileException("请求码不存在");
        }

        File file = fileService.getFile(video.getPath());

        if (Objects.isNull(file)) {
            log.error("视频流获取方法: ======> 视频文件不存在");
            throw new FileException("视频文件不存在");
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            response.setHeader("Content-Type", "video/" + video.getSuffix() + ";charset=UTF-8");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            log.error("视频流获取方法: ======> 视频流返回异常");
            throw new FileException("视频流返回异常");
        }
        log.info("视频流获取方法: ======> 视频流获取成功");
    }
}
