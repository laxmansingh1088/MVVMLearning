package ebookshop_mvvm.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import ebookshop_mvvm.dao.BooksDAO;
import ebookshop_mvvm.dao.CategoryDAO;
import ebookshop_mvvm.models.Books;
import ebookshop_mvvm.models.Category;


@Database(entities = {Category.class, Books.class}, version = 1)
public abstract class BooksDatabase extends RoomDatabase {

    private static BooksDatabase instance;

    public abstract CategoryDAO getCategoryDAO();

    public abstract BooksDAO getBooksDAO();


    public static synchronized BooksDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , BooksDatabase.class
                    , "books_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }

        return instance;
    }


    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InItDatabaseInitialization(instance).execute();
        }
    };


    private static class InItDatabaseInitialization extends AsyncTask<Void, Void, Void> {
        private CategoryDAO categoryDAO;
        private BooksDAO booksDAO;

        public InItDatabaseInitialization(BooksDatabase booksDatabase) {
            categoryDAO = booksDatabase.getCategoryDAO();
            booksDAO = booksDatabase.getBooksDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Category category1 = new Category();
            category1.setCategoryName("Fiction");
            category1.setCategoryDescription("Fiction are type of books");

            Category category2 = new Category();
            category2.setCategoryName("Drama");
            category2.setCategoryDescription("Drama is full of suspense and comedy books");

            Category category3 = new Category();
            category3.setCategoryName("Stories");
            category3.setCategoryDescription("Stories are full of new story");

            Category category4 = new Category();
            category4.setCategoryName("Programming");
            category4.setCategoryDescription("Programming books are helpful in creating softwares and applications");

            Books books1 = new Books("Java", "Rs 355", "Java Programming", 4);
            Books books2 = new Books("Harry Potter", "Rs 325", "Ghost and magic book", 1);
            Books books3 = new Books("The Girl in Red", "Rs 555", "It is a story of a girl.", 3);
            Books books4 = new Books("Meghdoot", "Rs 455", "Full drama", 2);
            Books books5 = new Books("C#", "Rs 770", "c sharp Programming", 4);


            categoryDAO.insert(category1);
            categoryDAO.insert(category2);
            categoryDAO.insert(category3);
            categoryDAO.insert(category4);

            booksDAO.insert(books1);
            booksDAO.insert(books2);
            booksDAO.insert(books3);
            booksDAO.insert(books4);
            booksDAO.insert(books5);


            return null;
        }
    }

}
