package io.github.tierneyjohn.eshow.dto;

import io.github.tierneyjohn.eshow.common.enums.FileType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 视频数据 DTO
 * </p>
 * <p>create file on 2022/3/20</p>
 *
 * @author TierneyJohn
 */
@Data
public class VideoDTO {

    @NotBlank(message = "必须输入文件名")
    private String name;

    @NotBlank(message = "必须输入标识码")
    private String code;

    @NotBlank(message = "必须输入源文件路径")
    private String path;

    private Integer length;

    private Long size;

    @NotNull(message = "必须输入文件类型")
    private FileType type;

    private String suffix;

}
