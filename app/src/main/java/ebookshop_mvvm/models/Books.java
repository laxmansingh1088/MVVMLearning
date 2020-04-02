package ebookshop_mvvm.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "books_table", foreignKeys = @ForeignKey(entity = Category.class
        , parentColumns = "id"
        , childColumns = "category_id", onDelete = CASCADE))
public class Books extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "book_price")
    private String bookPrice;
    @ColumnInfo(name = "book_description")
    private String bookDescription;
    @ColumnInfo(name = "category_id")
    private int categoryId;

    public Books() {
    }

    public Books(String bookName, String bookPrice, String bookDescription, int categoryId) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookDescription = bookDescription;
        this.categoryId = categoryId;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(BR.bookName);
    }

    @Bindable
    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
        notifyPropertyChanged(BR.bookPrice);
    }

    @Bindable
    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
        notifyPropertyChanged(BR.bookDescription);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }
}
