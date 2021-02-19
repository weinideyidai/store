package com.fei.store.controller;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.service.RoleService;
import com.fei.store.vo.RoleVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = roleService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        roleService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        roleService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid RoleVO roleVO, BindingResult result){
        if (result.hasErrors()){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        roleService.save(roleVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findRoleById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(roleService.findRoleById(id));
    }

    @RequestMapping("updateCustomer/{ids}")
    public ResponseEntity<?> updateRole(@RequestBody RoleVO roleVO,@PathVariable("ids") Integer id){
        roleService.updateRole(roleVO);
        roleService.roleAddPermission(roleVO.getId(),id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("findName/{name}")
    public ResponseEntity<?> findRoleByName(@PathVariable("name") String name){
        String str = roleService.findRoleByName(name);
        return ResponseEntity.ok(str);
    }

    @RequestMapping("findAllRole")
    public ResponseEntity<?> findAllRole(){
        return ResponseEntity.ok(roleService.findAllRole());
    }
}
