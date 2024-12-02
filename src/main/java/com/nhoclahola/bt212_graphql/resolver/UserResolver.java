package com.nhoclahola.bt212_graphql.resolver;

import com.nhoclahola.bt212_graphql.entities.Category;
import com.nhoclahola.bt212_graphql.entities.User;
import com.nhoclahola.bt212_graphql.services.CategoryService;
import com.nhoclahola.bt212_graphql.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserResolver
{
    private final UserService userService;
    private final CategoryService categoryService;

    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    public List<Category> allCategories() {
        return categoryService.getAllCategories();
    }

    public List<Category> categoriesByUser(Integer userId) {
        return categoryService.getCategoriesByUserId(userId);
    }

    public User createUser(String fullName, String email, String password) {
        return userService.createUser(fullName, email, password);
    }

    public User updateUser(Integer id, String fullName, String email, String password) {
        return userService.updateUser(id, fullName, email, password);
    }

    public boolean deleteUser(Integer id) {
        return userService.deleteUser(id);
    }
}
