package io.github.tierneyjohn.eshow.entity;

import io.github.tierneyjohn.eshow.common.enums.FileType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * <p>
 * 文件信息公共父类
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class FileBase {

    /**
     * 文档 id
     */
    private ObjectId id;

    /**
     * 标识码
     */
    private String code;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件分类
     */
    private FileType type;

    /**
     * 文件存储路径
     */
    private String path;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件描述
     */
    private String describe;

    /**
     * 作者列表
     */
    private List<Author> authors;
}
