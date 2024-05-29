package net.mycampany.myWEBAPPStudy.service;

import net.mycampany.myWEBAPPStudy.exception.UserNotFoundException;
import net.mycampany.myWEBAPPStudy.repository.UserRepostory;
import net.mycampany.myWEBAPPStudy.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepostory repostory;

    public List<User> listall(){
        return  (List<User>) repostory.findAll();
    }

    public void save(User user) {
           repostory.save(user);
    }

    public User login(String email, String password) {
        return repostory.User_login(email, password);
    }


    public  User get(Integer id) throws UserNotFoundException {
        Optional<User> result= repostory.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("could not any users with  ID "+id);
    }
    public  void delete  (Integer id) throws UserNotFoundException {
        Long count= repostory.countById(id);
        if(count==null || count==0){
            throw new UserNotFoundException("could not any users with  ID "+id);
        }
        repostory.deleteById(id);
    }
}
