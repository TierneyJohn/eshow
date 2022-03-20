package io.github.tierneyjohn.eshow.repository;

import io.github.tierneyjohn.eshow.entity.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 视频信息仓储层
 * </p>
 * <p>create file on 2022/3/20</p>
 *
 * @author TierneyJohn
 */
@Repository
public interface VideoRepository extends MongoRepository<Video, ObjectId> {

    Video findOneByCode(String code);
}
