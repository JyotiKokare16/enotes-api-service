package com.enotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.util.CollectionUtils;

import com.enotes.entity.Category;
import com.enotes.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
    private  CategoryService categoryService;

    // Constructor Injection (Best Practice)
   

    @PostMapping("/save-category")
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {

        Boolean saveCategory = categoryService.saveCategory(category);

        if (saveCategory) {
            return new ResponseEntity<>("saved", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {

        List<Category> allCategory = categoryService.getAllCategory();

        if (CollectionUtils.isEmpty(allCategory)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategory, HttpStatus.OK);
        }
    }
}