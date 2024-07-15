package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Category;
import odk.kalanso.stock.exception.CategoryNotFoundException;
import odk.kalanso.stock.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //List des Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    //GET par Id
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }
    //Create new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    //Modifier Category
    public Category updateCategory(Category category, int id) {
        Optional<Category> existCategory = categoryRepository.findById(id);
        if (existCategory.isPresent()) {
            throw new CategoryNotFoundException(String.format("category id %s nest pas trouve"));
        }
        return categoryRepository.save(category);
    }
    //Delete Catgory
    public void deleteCategory(int id) {
        Optional<Category> existCategory = categoryRepository.findById(id);
        if (existCategory.isPresent()) {
            throw new CategoryNotFoundException(String.format("category id %s nest pas trouve"));
        }
         categoryRepository.deleteById(id);
    }
}
