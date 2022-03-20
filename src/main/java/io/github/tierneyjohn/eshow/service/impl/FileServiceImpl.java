package io.github.tierneyjohn.eshow.service.impl;

import io.github.tierneyjohn.eshow.common.constant.FileTypePath;
import io.github.tierneyjohn.eshow.common.enums.FileType;
import io.github.tierneyjohn.eshow.common.exception.FileException;
import io.github.tierneyjohn.eshow.common.property.EShowProperty;
import io.github.tierneyjohn.eshow.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 * 文件处理服务实现类
 * </p>
 * <p>create file on 2022/3/19</p>
 *
 * @author TierneyJohn
 */
@Slf4j
@Service
@EnableConfigurationProperties({EShowProperty.class})
public class FileServiceImpl implements FileService {

    private final EShowProperty property;

    public FileServiceImpl(EShowProperty property) throws IOException {
        this.property = property;
        initFilePath();
    }

    @Override
    public File getFile(String fileName, FileType fileType) {

        log.info("{} 文件获取中 ==>>  ", fileName);

        if (Objects.isNull(fileType)) {
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);

            String[] audioSuffix = new String[]{"mp3"};
            String[] pictureSuffix = new String[]{"png", "jpg", "jpeg"};
            String[] textSuffix = new String[]{"txt", "doc", "docx"};
            String[] videoSuffix = new String[]{"mp4", "avi"};

            if (Arrays.asList(audioSuffix).contains(fileSuffix)) {
                log.info("文件类型 ==>>  AUDIO");
                fileType = FileType.AUDIO;
            } else if (Arrays.asList(pictureSuffix).contains(fileSuffix)) {
                log.info("文件类型 ==>>  PICTURE");
                fileType = FileType.PICTURE;
            } else if (Arrays.asList(textSuffix).contains(fileSuffix)) {
                log.info("文件类型 ==>>  TEXT");
                fileType = FileType.TEXT;
            } else if (Arrays.asList(videoSuffix).contains(fileSuffix)) {
                log.info("文件类型 ==>>  VIDEO");
                fileType = FileType.VIDEO;
            } else {
                log.warn("文件获取失败 ==>> 不支持的文件类型");
                throw new FileException("不支持的文件类型");
            }
        }

        String filePath = switch (fileType) {
            case AUDIO -> FileTypePath.AUDIO_PATH;
            case PICTURE -> FileTypePath.PICTURE_PATH;
            case TEXT -> FileTypePath.TEXT_PATH;
            case VIDEO -> FileTypePath.VIDEO_PATH;
        };

        String path = property.getResourcePath() + File.separator + filePath + File.separator;
        log.info("文件地址 ==>>  {}", path);
        return new File(path + fileName);
    }

    @Override
    public File getFile(String path) {
        log.info("获取指定路径文件 <=== 文件路径: {}", path);
        File file = new File(path);
        if (file.exists()) {
            log.info("获取指定路径文件 ===> 文件获取成功");
            return file;
        }
        log.warn("获取指定路径文件 ===> 文件获取失败");
        return null;
    }

    /**
     * 初始化项目资源路径
     */
    private void initFilePath() {
        log.info("初始化项目资源路径 ===>>>>>>>>>>");
        File file = new File(property.getResourcePath());
        if (!file.exists()) {
            log.info("项目资源路径不存在,初始化项目路径 >>>>>>>>>>");
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("<<<<<<<<<<=== 初始化项目资源路径失败");
                throw new FileException("初始化项目资源路径失败");
            }
        }

        String audioPath = property.getResourcePath() + File.separator + FileTypePath.AUDIO_PATH;
        String picturePath = property.getResourcePath() + File.separator + FileTypePath.PICTURE_PATH;
        String textPath = property.getResourcePath() + File.separator + FileTypePath.TEXT_PATH;
        String videoPath = property.getResourcePath() + File.separator + FileTypePath.VIDEO_PATH;

        if (!new File(audioPath).exists()) {
            log.info("初始化音频路径 >>>>>>>>>>");
            try {
                new File(audioPath).mkdir();
            } catch (SecurityException e) {
                log.error("<<<<<<<<<< 音频路径初始化失败");
                throw new FileException("音频路径初始化失败");
            }
            log.info("<<<<<<<<<< 音频路径初始化成功");
        }

        if (!new File(picturePath).exists()) {
            log.info("初始化图片路径 >>>>>>>>>>");
            try {
                new File(picturePath).mkdir();
            } catch (SecurityException e) {
                log.error("<<<<<<<<<< 图片路径初始化失败");
                throw new FileException("图片路径初始化失败");
            }
            log.info("<<<<<<<<<< 图片路径初始化成功");
        }

        if (!new File(textPath).exists()) {
            log.info("初始化文本路径 >>>>>>>>>>");
            try {
                new File(textPath).mkdir();
            } catch (SecurityException e) {
                log.error("<<<<<<<<<< 文本路径初始化失败");
                throw new FileException("文本路径初始化失败");
            }
            log.info("<<<<<<<<<< 文本路径初始化成功");
        }

        if (!new File(videoPath).exists()) {
            log.info("初始化视频路径 >>>>>>>>>>");
            try {
                new File(videoPath).mkdir();
            } catch (SecurityException e) {
                log.error("<<<<<<<<<< 视频路径初始化失败");
                throw new FileException("视频路径初始化失败");
            }
            log.info("<<<<<<<<<< 视频路径初始化成功");
        }

        log.info("<<<<<<<<<<=== 项目资源路径初始化成功");
    }
}
