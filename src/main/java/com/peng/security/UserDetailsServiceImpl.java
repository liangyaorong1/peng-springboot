package com.peng.security;


import com.peng.entity.User;
import com.peng.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userVo=userService.findByUsername(username);//查询当前登录用户
        if(Objects.isNull(userVo)){//如果用户等于null
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        // TODO 查询当前用户的的权限信息
        // TODO 将登录的用户保存到AccountUser类中
        List<String> authorities=userService.findPermissions(userVo.getId());
        // 将查询出来的用户信息封装成AccountUser对象返回
        return new AccountUser(userVo.getId(),userVo.getUsername(),userVo.getPassword(),authorities);
        //return new AccountUser(sysUserVo.getId(), sysUserVo.getUsername(), sysUserVo.getPassword(),new TreeSet<>());
    }
    //通过角色与权限的关联表的用户编号获取呀到用户权限集合
//    public List<GrantedAuthority> getUserAuthority(Long userId){
//        //把权限字符串封装成GrantedAuthority列表
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(
//                sysUserService.getUserAuthorityInfo(userId));
//    }

}
