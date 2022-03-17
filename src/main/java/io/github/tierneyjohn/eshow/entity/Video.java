package io.github.tierneyjohn.eshow.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 视频信息实体类
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Video extends FileBase {

    /**
     * 视频长度
     */
    private Integer length;

    /**
     * 视频封面
     */
    private Picture cover;

    /**
     * 视频详情图片
     */
    private PicturePackage picturePackage;
}
