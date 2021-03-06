package com.che58.ljb.rxjava.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.che58.ljb.rxjava.R;
import com.che58.ljb.rxjava.utils.XgoLog;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJava实现搜索关键字推荐Demo
 * Created by ljb on 2016/3/24.
 */
public class DebounceFragment extends RxFragment {

    @Bind(R.id.et_search)
    EditText et_search;

    @Bind(R.id.iv_x)
    ImageView iv_x;

    @Bind(R.id.lv_list)
    ListView lv_list;
    private ArrayAdapter<String> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_text_change, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchKeyWordDemo();
    }

    /**
     * 搜索关键字提醒Demo
     */
    private void searchKeyWordDemo() {
        RxTextView.textChangeEvents(et_search)
                .debounce(150, TimeUnit.MILLISECONDS)  // debounce:每次文本更改后有150毫秒的缓冲时间，默认在computation调度器
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        clearPage(textViewTextChangeEvent);
                    }
                })
                .filter(new Func1<TextViewTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        return !TextUtils.isEmpty(textViewTextChangeEvent.text());
                    }
                })
                .switchMap(new Func1<TextViewTextChangeEvent, Observable<List<String>>>() {
                    @Override
                    public Observable<List<String>> call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        return getKeyWordFormNet(textViewTextChangeEvent.text().toString().trim()).subscribeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())  // 触发后回到Android主线程调度器
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        initPage(strings);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    private void clearPage(TextViewTextChangeEvent event) {
        String text = event.text().toString().trim();
        if (mAdapter != null && TextUtils.isEmpty(text)) {
            mAdapter.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initPage(List<String> keyWords) {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_list, R.id.tv_text, keyWords);
            lv_list.setAdapter(mAdapter);
            lv_list.setOnItemClickListener(itemClick());
        } else {
            mAdapter.clear();
            mAdapter.addAll(keyWords);
            mAdapter.notifyDataSetChanged();
        }
    }

    private AdapterView.OnItemClickListener itemClick() {
        return new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DebounceFragment.this.getActivity(), "搜索:" + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        };
    }

    /**
     * 模拟网路接口获取匹配到的关键字列表
     */
    private Observable<List<String>> getKeyWordFormNet(final String key) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {

            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                boolean b = Thread.currentThread() == Looper.getMainLooper().getThread();
                XgoLog.d("IO线程::" + !b);

                SystemClock.sleep(500);
                // 这里是网络请求操作...
                List<String> datas = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    datas.add("KeyWord:" + key + i);
                }
                subscriber.onNext(datas);
                subscriber.onCompleted();
            }
        });
    }

    @OnClick(R.id.iv_x)
    void clear() {
        et_search.setText("");
    }
}
