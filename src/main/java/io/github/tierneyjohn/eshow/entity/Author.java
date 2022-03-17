package io.github.tierneyjohn.eshow.entity;

import org.bson.types.ObjectId;

import java.time.LocalDate;

/**
 * <p>
 * 作者信息实体类
 * </p>
 * <p>create file on 2022/3/18</p>
 *
 * @author TierneyJohn
 */
public class Author {

    /**
     * 文档 id
     */
    private ObjectId id;

    /**
     * 名称
     */
    private String name;

    /**
     * 出生年月
     */
    private LocalDate birthday;

    /**
     * 封面图
     */
    private Picture cover;

    /**
     * 详情图集合
     */
    private PicturePackage picturePackage;

    /**
     * 描述
     */
    private String describe;
}
