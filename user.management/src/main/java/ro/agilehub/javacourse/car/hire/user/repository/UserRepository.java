package ro.agilehub.javacourse.car.hire.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.agilehub.javacourse.car.hire.user.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> getAllByUserStatus(String status);
}
