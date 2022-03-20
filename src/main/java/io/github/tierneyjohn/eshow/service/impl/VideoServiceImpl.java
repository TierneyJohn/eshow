package io.github.tierneyjohn.eshow.service.impl;

import com.alibaba.fastjson.JSON;
import io.github.tierneyjohn.eshow.common.constant.FileTypePrefixCode;
import io.github.tierneyjohn.eshow.common.enums.FileType;
import io.github.tierneyjohn.eshow.common.exception.FileException;
import io.github.tierneyjohn.eshow.dto.VideoDTO;
import io.github.tierneyjohn.eshow.entity.Video;
import io.github.tierneyjohn.eshow.repository.VideoRepository;
import io.github.tierneyjohn.eshow.service.FileService;
import io.github.tierneyjohn.eshow.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        log.info("视频信息获取方法: <====== 请求码: {}", code);
        Video video = videoRepository.findOneByCode(code);
        log.info("视频信息获取成功: ======> 视频信息: {}", JSON.toJSONString(video));
        return video;
    }

    @Override
    public void findVideoIO(String code, HttpServletResponse response) {
        log.info("视频流获取方法: <====== 请求码: {}", code);
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

    @Override
    public String upload(@NotNull VideoDTO validator) {
        log.info("视频文件上传方法: <====== 文件信息: {}", validator);

        File file = new File(validator.getPath());

        if (!file.exists() || !file.isFile()) {
            log.error("视频文件上传方法: ======> 文件路径不正确");
            throw new FileException("文件路径不正确");
        }

        // 标记 Code 整形
        validator.setCode(FileTypePrefixCode.VIDEO_PREFIX_CODE + validator.getCode());

        String filePath = fileService.getVideoFilePath() + validator.getCode() + File.separator;

        // 创建相应文件夹
        if (new File(filePath).exists()) {
            // 文件夹已存在
            log.warn("视频文件上传方法: ======> 文件夹已存在");
        } else {
            // 文件夹不存在
            new File(filePath).mkdir();
        }

        File newFile = new File(filePath + validator.getName() + "." + validator.getSuffix());

        try (FileInputStream inputStream = new FileInputStream(file); FileOutputStream outputStream = new FileOutputStream(newFile)) {
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            log.error("视频文件上传方法: ======> 文件转存异常");
            throw new FileException("文件转存异常");
        }
        log.info("视频文件上传方法: ======> 文件转存成功");

        Video video = new Video();
        video.setCode(validator.getCode());
        video.setName(validator.getName());
        video.setType(FileType.VIDEO);
        video.setPath(newFile.getPath());
        video.setSuffix(validator.getSuffix());
        video.setSize(newFile.length());

        videoRepository.save(video);
        log.info("视频文件上传方法: ======> 信息保存成功");
        return "SUCCESS";
    }
}
