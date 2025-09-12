package com.shop.pitomets.database.repository;

import com.shop.pitomets.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// насчёт JPA конечно хз стоит ли использовать, но так проще,
// а ваще это надо в отдельный микросервис уносить (работу с БД)


// да этого здесь быть не должно, уносить надо в отдельный микросервис
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(Long uid);
}
