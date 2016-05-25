package com.ppjun.controller;

import com.ppjun.model.UserEntity;
import com.ppjun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * user
 *
 * @author Rc3
 * @create 2016/05/25  11:43
 */


@Controller
public class UserController
{

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index()
    {

        return "index";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap)
    {
        List<UserEntity> userEntityList = userRepository.findAll();
        modelMap.addAttribute("userList", userEntityList);
        return "admin/users";

    }


    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser()
    {
        return "admin/addUser";
    }

    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity)
    {

        System.out.println(userEntity.getUsername());
        userRepository.saveAndFlush(userEntity);
        return "redirect:/admin/users";

    }

    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap)
    {

        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail";
    }

    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap)
    {

        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }


    @RequestMapping(value = "/admin/users/updateP",method = RequestMethod.POST )
    public String updateUserPost(@ModelAttribute("userP") UserEntity user){
          userRepository.updateUser(user.getUsername(),user.getPassword(),user.getId());

        userRepository.flush();
        return "redirect:/admin/users";

    }

    @RequestMapping(value = "/admin/users/delete/{id}" ,method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer id){

        userRepository.delete(id);
        userRepository.flush();
        return "redirect:/admin/users";
    }

}
