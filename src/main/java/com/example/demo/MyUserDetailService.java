package com.example.demo;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayDeque;
import java.util.ArrayList;
/*
* 驗證與授權
* AuthenticationManagerBuilder 的 inMemoryAuthentication
* 或 jdbcAuthentication 設定驗證來源時，
* 會各自套用 UserDetailsManagerConfigurer 的子類實例，真正在進行時驗證，
* 就會從 UserDetailsManagerConfigurer 取得 UserDetailsService 實例，
* 以便從驗證來源取得使用者名稱、密碼、角色清單等資訊。
* */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    //@Transactional 代表如果有異常數據不會插入資料庫 會自動回滾 廣泛應用在public
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernmae) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(usernmae)
                .orElseThrow(()->new UsernameNotFoundException("user not found with username"));
//       return new User("foo","foo",new ArrayList<>());
        return UserPrinciple.build(user);
    }
}
