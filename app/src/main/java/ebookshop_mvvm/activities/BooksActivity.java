package ebookshop_mvvm.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvmlearning.R;
import com.example.mvvmlearning.databinding.ActivityBooksBinding;

import java.util.ArrayList;
import java.util.List;

import ebookshop_mvvm.adapters.CategorySpinnerAdapter;
import ebookshop_mvvm.models.Books;
import ebookshop_mvvm.models.Category;
import ebookshop_mvvm.viewmodels.BooksActivityViewModel;
import utils.Utils;

public class BooksActivity extends AppCompatActivity {

    private BooksActivityViewModel booksActivityViewModel;
    private ActivityBooksBinding activityBooksBinding;
    private BooksActivityClickHandlers clickHandlers;

    private CategorySpinnerAdapter categorySpinnerAdapter;
    private List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        activityBooksBinding = DataBindingUtil.setContentView(this, R.layout.activity_books);
        clickHandlers = new BooksActivityClickHandlers();
        activityBooksBinding.setActivityClickHandler(clickHandlers);

        categorySpinnerAdapter = new CategorySpinnerAdapter(this, categoryList);
        activityBooksBinding.spinnerCategory.setAdapter(categorySpinnerAdapter);
        booksActivityViewModel = ViewModelProviders.of(this).get(BooksActivityViewModel.class);
        booksActivityViewModel.getListCategory().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryList.clear();
                categoryList.addAll(categories);
                categorySpinnerAdapter.notifyDataSetChanged();
                for (int i = 0; i < categories.size(); i++) {
                    Utils.showLog("Categories are:- " + categories.get(i).getId() + "-" + categories.get(i).getCategoryName());
                }
            }
        });


        booksActivityViewModel.getListBooks(1).observe(this, new Observer<List<Books>>() {
            @Override
            public void onChanged(List<Books> books) {
                for (int i = 0; i < books.size(); i++) {
                    Utils.showLog("Books are:- " + books.get(i).getId() + "-" + books.get(i).getBookName());
                }
            }
        });

        booksActivityViewModel.getAllBooks().observe(this, new Observer<List<Books>>() {
            @Override
            public void onChanged(List<Books> books) {
                for (int i = 0; i < books.size(); i++) {
                    Utils.showLog("Books are:- " + books.get(i).getId() + "-" + books.get(i).getBookName());
                }
            }
        });
    }


    public class BooksActivityClickHandlers {
        public void onFabAddBooksClick(View view) {
            Toast.makeText(BooksActivity.this, "ok", Toast.LENGTH_SHORT).show();
        }
    }
}
