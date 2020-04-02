package ebookshop_mvvm.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import ebookshop_mvvm.dao.BooksDAO;
import ebookshop_mvvm.database.BooksDatabase;
import ebookshop_mvvm.models.Books;

public class BooksRepository {

    private BooksDAO booksDAO;
    private LiveData<List<Books>> booksList;

    public BooksRepository(Application application) {
        BooksDatabase booksDatabase = BooksDatabase.getInstance(application);
        booksDAO = booksDatabase.getBooksDAO();
    }

    public LiveData<List<Books>> getBooksList(int categoryId) {
        return booksDAO.getBooks(categoryId);
    }

    public LiveData<List<Books>> getAllBooks() {
        return booksDAO.getAllBooks();
    }


    public void insertBook(Books books) {
        new InsertBookAsyncTask(booksDAO).execute(books);
    }

    public void deleteBook(Books books) {
        new DeleteBookAsyncTask(booksDAO).execute(books);
    }

    public void updateBook(Books books) {
        new UpdateBookAsyncTask(booksDAO).execute(books);
    }


    private static class InsertBookAsyncTask extends AsyncTask<Books, Void, Void> {
        private BooksDAO booksDAO;

        public InsertBookAsyncTask(BooksDAO booksDAO) {
            this.booksDAO = booksDAO;
        }

        @Override
        protected Void doInBackground(Books... books) {
            booksDAO.insert(books[0]);
            return null;
        }
    }

    private static class DeleteBookAsyncTask extends AsyncTask<Books, Void, Void> {
        private BooksDAO booksDAO;

        public DeleteBookAsyncTask(BooksDAO booksDAO) {
            this.booksDAO = booksDAO;
        }

        @Override
        protected Void doInBackground(Books... books) {
            booksDAO.delete(books[0]);
            return null;
        }
    }


    private static class UpdateBookAsyncTask extends AsyncTask<Books, Void, Void> {
        private BooksDAO booksDAO;

        public UpdateBookAsyncTask(BooksDAO booksDAO) {
            this.booksDAO = booksDAO;
        }

        @Override
        protected Void doInBackground(Books... books) {
            booksDAO.update(books[0]);
            return null;
        }
    }
}
