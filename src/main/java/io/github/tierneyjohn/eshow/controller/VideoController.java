package io.github.tierneyjohn.eshow.controller;

import io.github.tierneyjohn.eshow.entity.Video;
import io.github.tierneyjohn.eshow.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 视频文件流媒体控制器
 * </p>
 * <p>create file on 2022/3/20</p>
 *
 * @author TierneyJohn
 */
@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/info/{code}")
    public Video getVideoInfo(@PathVariable String code) {
        return videoService.findVideoInfoByCode(code);
    }

    @GetMapping("/io/{code}")
    public void getVideoIO(@PathVariable String code, HttpServletResponse response) {
        videoService.findVideoIO(code, response);
    }
}
