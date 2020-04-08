package rx_java;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmlearning.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    public static final String TAG = "RxJavaActivity";
    private TextView textView;
    private String greeting = "Hello RxAndroid Dev";
    private Observable<String> observable;
    private CompositeDisposable compositeDisposable;

    //Map, Flatmap operators https://www.androidhive.info/RxJava/map-flatmap-switchmap-concatmap/
    // Subjects:-    https://blog.mindorks.com/understanding-rxjava-subject-publish-replay-behavior-and-async-subject-224d663d452f


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        textView = findViewById(R.id.textView);
        compositeDisposable = new CompositeDisposable();

        observable = Observable.just(greeting);

        compositeDisposable.add(Observable.just(greeting)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d(TAG, "onNext method called   "+s);
                     //   textView.setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError method called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete method called");
                    }
                }));

        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d(TAG, "onNext method called");
                        Toast.makeText(RxJavaActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError method called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete method called");
                    }
                }));


        runFromArrayFilter();
        runRangeFilter();
        runCreateFilter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }


    private void runFromArrayFilter() {

        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Mango");
        fruits.add("Papaya");

        String[] fruitss = {"Apple", "Mango", "Papaya"};
        compositeDisposable.add(Observable.fromArray(fruitss)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d("rxOperators", "fromIterable(), fromArray():--   " + s.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


    private void runRangeFilter() {

        Integer[] nums = {1, 2, 3, 4, 5};
        compositeDisposable.add(Observable.range(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {

                    @Override
                    public void onNext(@NonNull Integer s) {
                        Log.d("rxOperators", "range():--   " + s.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


    private void runCreateFilter() {


        compositeDisposable.add(Observable.create(new ObservableOnSubscribe<FruitsModel>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<FruitsModel> emitter) throws Throwable {

                List<FruitsModel> fruitsModels = new ArrayList<>();
                fruitsModels.add(new FruitsModel("Mango", 10));
                fruitsModels.add(new FruitsModel("Banana", 20));
                fruitsModels.add(new FruitsModel("Papaya", 50));

                for (FruitsModel fruits : fruitsModels) {
                    fruits.setPrice(5);
                    emitter.onNext(fruits);
                }

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<FruitsModel>() {

                    @Override
                    public void onNext(@NonNull FruitsModel fruit) {
                        Log.d("rxOperators", "create():--   " + fruit.getFruitName().toString()+"   Price:-"+fruit.getPrice());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


}
