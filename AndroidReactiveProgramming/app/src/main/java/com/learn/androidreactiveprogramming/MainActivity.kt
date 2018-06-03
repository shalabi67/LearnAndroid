package com.learn.androidreactiveprogramming

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var button : Button
    lateinit var  editTextBefrorThrottle : EditText
    lateinit var  editTextAfterThrottle : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById<Button>(R.id.buttonClickMe)
        editTextBefrorThrottle = findViewById<EditText>(R.id.editTextBefrorThrottle)
        editTextAfterThrottle = findViewById<EditText>(R.id.editTextAfterThrottle)

        //setupBasicUsingAndroid()
        //setupBasicUsingRxJava()
        //setupBasicUsingRxJavaNoMapLambda()
        setupBasicUsingRxJavaUsingObserver()
        //setupBasicUsingRxJavaUsingConsumer()
    }

    private fun setupBasicUsingRxJavaUsingConsumer() {
        val buttonPublisher = PublishSubject.create<View>()
        button.setOnClickListener { v -> buttonPublisher.onNext(v!!) }

        /*
        val consumer = object : Consumer<View> {
            override fun accept(t: View?) {
                incrementAfterThrottle()
            }

        }
        */

        val consumer =  { view : View -> incrementAfterThrottle() }
        buttonPublisher
                .map { t ->
                    incrementBeforeThrottle()
                    t
                }
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(consumer)
    }

    private fun setupBasicUsingRxJavaUsingObserver() {
        val buttonPublisher = PublishSubject.create<View>()
        button.setOnClickListener { v -> buttonPublisher.onNext(v!!) }

        val observer = object : Observer<View> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: View) {
                incrementAfterThrottle()
            }

            override fun onError(e: Throwable) {

            }

        }

        buttonPublisher
                .map { t ->
                    incrementBeforeThrottle()
                    t
                }
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(observer)

    }

    private fun setupBasicUsingRxJavaNoMapLambda() {
        val buttonPublisher = PublishSubject.create<View>()
        button.setOnClickListener { v -> buttonPublisher.onNext(v!!) }

        buttonPublisher
                /*
                .map(object : Function<View, View> {
                    override fun apply(t: View): View {
                        incrementBeforeThrottle()
                        return t
                    }
                })*/
                .map { t ->
                    incrementBeforeThrottle()
                    t
                }
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe{incrementAfterThrottle()}
    }

    private fun setupBasicUsingRxJava() {
        val buttonPublisher = PublishSubject.create<View>()
        button.setOnClickListener { v -> buttonPublisher.onNext(v!!) }

        buttonPublisher
                .map{ incrementBeforeThrottle()}
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe{incrementAfterThrottle()}
    }

    private fun setupBasicUsingAndroid() {
        RxView.clicks(button)
                .map{ incrementBeforeThrottle()}
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe{incrementAfterThrottle()}
    }

    private fun incrementBeforeThrottle() {
        incrementEditText(editTextBefrorThrottle)
    }

    private fun incrementAfterThrottle() {
        incrementEditText(editTextAfterThrottle)
    }

    private fun incrementEditText(editText : EditText) {
        var counter : Int = Integer.valueOf(editText.text.toString())
        counter++
        editText.setText(counter.toString())
    }

}
