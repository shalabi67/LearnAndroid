package com.learn.androidreactiveprogramming

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe



class NetworkService {
    public fun createPublisher(): PublishSubject<Int> {
        return PublishSubject.create<Int>();
    }

    /*
    public fun getData() : Observable<Int> {
        val observable = Observable.create<Int> { emitter ->
            try {
                Thread.sleep(1000)
                emitter.onNext(1)
                Thread.sleep(200)
                emitter.onNext(2)

                Observable.just(100, 200, 300, 400)
                        .subscribe{i -> emitter.onNext(i) }
                Thread.sleep(200)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

        return observable
    }
    */
    public fun getData(start : Int): Observable<Int> {
        val observable = Observable.create<Int> { emitter ->
            try {
                Thread.sleep(1000)
                emitter.onNext(1*start)
                Thread.sleep(200)
                emitter.onNext(2*start)

                Observable.just(100*start, 200*start, 300*start, 400*start)
                        .subscribe { i -> emitter.onNext(i) }
                Thread.sleep(200)
                Observable.just(1000, 2000, 3000, 4000)
                        .subscribe { i ->
                            emitter.onNext(i)
                            Thread.sleep(50)
                        }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

        return observable
    }
}