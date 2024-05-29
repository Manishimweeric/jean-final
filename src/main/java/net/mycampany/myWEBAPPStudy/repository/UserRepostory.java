package net.mycampany.myWEBAPPStudy.repository;

import net.mycampany.myWEBAPPStudy.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepostory  extends CrudRepository<User,Integer> {
    public  long countById(Integer id);

    @Query("SELECT u FROM User u WHERE u.Email = :email AND u.Password = :password")
    User User_login(@Param("email") String email, @Param("password") String password);


}
