package ebookshop_mvvm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ebookshop_mvvm.models.Books;

@Dao
public interface BooksDAO {

    @Insert
    void insert(Books books);

    @Delete
    void delete(Books books);

    @Update
    void update(Books books);

    @Query("SELECT * FROM books_table")
    LiveData<List<Books>> getAllBooks();

    @Query("SELECT * FROM books_table WHERE category_id==:categoryId")
    LiveData<List<Books>> getBooks(int categoryId);
}
