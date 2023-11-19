package com.blooging.controller;

import com.blooging.payloads.ApiResponse;
import com.blooging.payloads.CategoryDto;
import com.blooging.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){

        System.out.println(categoryDto);
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);

        System.out.println(createCategory);

        return  new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    // Update

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId)
    {
//        System.out.println(categoryDto);
//        System.out.println(catId);
        CategoryDto updateCategory = this.categoryService.updateCategory( categoryDto,catId);
//        System.out.println(updateCategory);
        return  new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
    }

    // Delete

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory (@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);

        return  new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted",true),HttpStatus.OK);
    }

    // Get
    @GetMapping("/{catId}")
    public   ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto categoryDto = this.categoryService.getCategory(catId);

        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }

    // Get All
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> list =this.categoryService.getCategories();
        return  ResponseEntity.ok(list);
    }

}
