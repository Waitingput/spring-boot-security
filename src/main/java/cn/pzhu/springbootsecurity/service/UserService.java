package cn.pzhu.springbootsecurity.service;

import cn.pzhu.springbootsecurity.entity.User;
import cn.pzhu.springbootsecurity.mapper.AmdinMapper;
import cn.pzhu.springbootsecurity.mapper.StudentMapper;
import cn.pzhu.springbootsecurity.mapper.TeacherMapper;
import cn.pzhu.springbootsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AmdinMapper amdinMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;

    //通过查询数据库加载登陆用户的信息，在用户登陆时自动调用
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //加载用户信息
        User user = userMapper.lodaUserByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUserId(user.getId()));
        return user;
    }
    public boolean changeUserPassword(String username,String password,String newpassword){
        boolean flag = false;
        User user =userMapper.lodaUserByUsername(username);
        newpassword = BCrypt.hashpw(newpassword,BCrypt.gensalt());
        boolean checkpw = BCrypt.checkpw(password, user.getPassword());
        if (BCrypt.checkpw(password,user.getPassword()))
        {
            User nuser = new User();
            nuser.setUsername(username);
            nuser.setPassword(newpassword);
            flag = userMapper.updateUserPassword(nuser);
        }
        return flag ? true : false;
    }
    public Integer getRoleId(String username,String author){
        Integer id = null;
        if(author.equals("student")){
            id = studentMapper.findStudentByNo(username);
        }else if(author.equals("teacher")){
            id = teacherMapper.findTeacherIdByNo(username);
        }else {
            id = amdinMapper.findAmdinIdByNo(username);
        }
        return id;
    }
}
