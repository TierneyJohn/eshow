package io.github.tierneyjohn.eshow.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 音频信息实体类
 * </p>
 * <p>create file on 2022/3/18</p>
 *
 * @author TierneyJohn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Audio extends FileBase {

    /**
     * 音频长度
     */
    private Integer length;
}
