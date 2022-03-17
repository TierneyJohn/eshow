package io.github.tierneyjohn.eshow.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 图片集数据实体类
 * </p>
 * <p>create file on 2022/3/18</p>
 *
 * @author TierneyJohn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PicturePackage extends FileBase {

    /**
     * 图片集封面路径
     */
    private String coverPath;

    /**
     * 图片数据对象集合
     */
    private List<Picture> pictures;

}
