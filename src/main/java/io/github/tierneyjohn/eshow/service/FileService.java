package io.github.tierneyjohn.eshow.service;

import io.github.tierneyjohn.eshow.common.enums.FileType;

import java.io.File;

/**
 * <p>
 * 文件处理服务接口
 * </p>
 * <p>create file on 2022/3/19</p>
 *
 * @author TierneyJohn
 */
public interface FileService {

    /**
     * 通过文件名称和文件类型获取指定路径文件
     * @param fileName 文件名称
     * @param fileType 文件类型
     * @return 文件流
     */
    File getFile(String fileName, FileType fileType);

    /**
     * 通过文件绝对路径获取指定文件
     * @param path 文件绝对路径
     * @return 文件流
     */
    File getFile(String path);

    /**
     * 获取音频文件路径地址
     * @return 音频文件路径
     */
    String getAudioFilePath();

    /**
     * 获取图片文件路径地址
     * @return 图片文件路径
     */
    String getPictureFilePath();

    /**
     * 获取文本文件路径地址
     * @return 文本文件路径
     */
    String getTextFilePath();

    /**
     * 获取视频文件路径地址
     * @return 视频文件路径
     */
    String getVideoFilePath();

    /**
     * 获取临时文件路径地址
     * @return 临时文件路径
     */
    String getTempFilePath();

}
