package pers.zengsx.toolkit.test.example.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.zengsx.toolkit.test.example.dao.IUserDAO;
import pers.zengsx.toolkit.test.example.model.User;

import java.util.Optional;

/**
 * @class-name: TestController
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 18:02
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final IUserDAO userDAO;

    @GetMapping("/test/{val}")
    public String testIndex(@PathVariable String val) {
        if ("throwError".equals(val)) {
            throw new RuntimeException(val);
        }
        return val;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return Optional.ofNullable(id).map(userDAO::getById).orElse(null);
    }

}
