package io.github.tierneyjohn.eshow.repository;

import io.github.tierneyjohn.eshow.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息仓储层
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
}
