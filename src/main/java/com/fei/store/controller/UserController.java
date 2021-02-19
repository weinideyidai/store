package com.fei.store.controller;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.common.utils.ShortMessageUtils;
import com.fei.store.service.RoleService;
import com.fei.store.service.UserService;
import com.fei.store.vo.UserVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = userService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        userService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        userService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid UserVO userVO, BindingResult result){
        if (result.hasErrors()){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        userService.save(userVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @RequestMapping("updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserVO userVO){
        userService.updateUser(userVO);
        roleService.deletRoleByUid(userVO.getId());
        userService.userAddRole(userVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("findName/{name}")
    public ResponseEntity<?> findUserByName(@PathVariable("name") String name){
        String str = userService.findUserByName(name);
        return ResponseEntity.ok(str);
    }

    //短信接口
    @RequestMapping("phoneCode/{phone}")
    public ResponseEntity<?> phoneMessage(@PathVariable("phone") String phone){
        String random = ShortMessageUtils.random();
        ShortMessageUtils.code(random,phone);
        stringRedisTemplate.opsForValue().set(phone, random,60, TimeUnit.SECONDS);
        return ResponseEntity.ok(null);
    }

    //更新密码
    @RequestMapping("updatePassword/{code}")
    public ResponseEntity<?> updatePassword(@RequestBody UserVO userVO,@PathVariable("code") String code){
        userService.updatePassword(userVO,code);
        return ResponseEntity.ok(null);
    }
}
