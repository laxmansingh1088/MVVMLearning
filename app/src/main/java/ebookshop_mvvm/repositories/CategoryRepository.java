package ebookshop_mvvm.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import ebookshop_mvvm.dao.CategoryDAO;
import ebookshop_mvvm.database.BooksDatabase;
import ebookshop_mvvm.models.Books;
import ebookshop_mvvm.models.Category;

public class CategoryRepository {

    private CategoryDAO categoryDAO;
    private LiveData<List<Category>> categoriesList;


    public CategoryRepository(Application application) {
        BooksDatabase booksDatabase = BooksDatabase.getInstance(application);
        categoryDAO = booksDatabase.getCategoryDAO();
    }


    public LiveData<List<Category>> getCategoriesList() {
        return categoryDAO.getAllCategories();
    }


    public void insertCategory(Category category) {
        new InsertCategoryAsyncTask(category, categoryDAO).execute();
    }

    public void deleteCategory(Category category) {
        new DeleteCategoryAsyncTask(category, categoryDAO).execute();
    }

    public void updateCategory(Category category) {
        new UpdateCategoryAsyncTask(category, categoryDAO).execute();
    }

    public static class InsertCategoryAsyncTask extends AsyncTask<Void, Void, Void> {

        private Category category;
        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(Category category, CategoryDAO categoryDAO) {
            this.category = category;
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDAO.insert(category);
            return null;
        }
    }


    public static class UpdateCategoryAsyncTask extends AsyncTask<Void, Void, Void> {

        private Category category;
        private CategoryDAO categoryDAO;

        public UpdateCategoryAsyncTask(Category category, CategoryDAO categoryDAO) {
            this.category = category;
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDAO.update(category);
            return null;
        }
    }


    public static class DeleteCategoryAsyncTask extends AsyncTask<Void, Void, Void> {

        private Category category;
        private CategoryDAO categoryDAO;

        public DeleteCategoryAsyncTask(Category category, CategoryDAO categoryDAO) {
            this.category = category;
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDAO.delete(category);
            return null;
        }
    }


}
