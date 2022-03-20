package io.github.tierneyjohn.eshow.controller;

import io.github.tierneyjohn.eshow.common.exception.FileException;
import io.github.tierneyjohn.eshow.dto.VideoDTO;
import io.github.tierneyjohn.eshow.entity.Video;
import io.github.tierneyjohn.eshow.service.VideoService;
import io.github.tierneyjohn.eshow.vo.ResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 * 视频文件流媒体控制器
 * </p>
 * <p>create file on 2022/3/20</p>
 *
 * @author TierneyJohn
 */
@Slf4j
@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/info/{code}")
    public Video getVideoInfo(@PathVariable String code) {
        log.info("视频信息获取方法: <====== 标识码 {}", code);
        Video video = videoService.findVideoInfoByCode(code);
        if (Objects.isNull(video)) {
            log.warn("视频信息获取方法: ======> 没有找到指定视频信息");
            throw new FileException("没有找到指定视频信息");
        }
        log.info("视频信息获取方法: ======> 视频信息获取成功");
        return video;
    }

    @GetMapping("/io/{code}")
    public void getVideoIO(@PathVariable String code, HttpServletResponse response) {
        videoService.findVideoIO(code, response);
    }

    @PostMapping("/upload")
    public ResponseVO uploadVideo(@RequestBody VideoDTO validator) {
        String code = videoService.upload(validator);
        return ResponseVO.buildStringResult(code);
    }
}
