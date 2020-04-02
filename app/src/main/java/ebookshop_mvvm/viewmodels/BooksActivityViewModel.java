package ebookshop_mvvm.viewmodels;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ebookshop_mvvm.models.Books;
import ebookshop_mvvm.models.Category;
import ebookshop_mvvm.repositories.BooksRepository;
import ebookshop_mvvm.repositories.CategoryRepository;

public class BooksActivityViewModel extends AndroidViewModel {

    private BooksRepository booksRepository;
    private CategoryRepository categoryRepository;
    LiveData<List<Category>> listCategory;
    LiveData<List<Books>> listBooks;

    public BooksActivityViewModel(@NonNull Application application) {
        super(application);
        booksRepository = new BooksRepository(application);
        categoryRepository = new CategoryRepository(application);
    }

    public LiveData<List<Category>> getListCategory() {
        listCategory = categoryRepository.getCategoriesList();
        return listCategory;
    }

    public LiveData<List<Books>> getListBooks(int categoryId) {
        listBooks = booksRepository.getBooksList(categoryId);
        return listBooks;
    }

    public LiveData<List<Books>> getAllBooks() {
        listBooks = booksRepository.getAllBooks();
        return listBooks;
    }


    public void addNewBook(Books book) {
        booksRepository.insertBook(book);
    }

    public void updateBook(Books book) {
        booksRepository.updateBook(book);
    }

    public void deleteBook(Books book) {
        booksRepository.deleteBook(book);
    }


    public void addCategory(Category category) {
        categoryRepository.insertCategory(category);
    }

    public void updateCategory(Category category) {
        categoryRepository.updateCategory(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteCategory(category);
    }
}
