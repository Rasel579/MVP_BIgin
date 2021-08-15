package com.test_app.mvp_bigin.utils

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.random.Random

class Creation {
    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun fromCallable(): @NonNull Observable<Any>? {
            return Observable.fromCallable {
                return@fromCallable randomResultOperation()
            }
        }

        private fun randomResultOperation(): Any {
            Thread.sleep(Random.nextLong(1000))
            return (listOf(true, false, true)[Random.nextInt(2)]).apply {
                println(this)
            }
        }

        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }
    }


    class Consumer(val producer: Producer) {
        val stringObserver = object : Observer<String> {
            var disposable: Disposable? = null

            override fun onComplete() {
                println("onComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(s: String?) {
                println("onNext: $s")
            }

            override fun onError(e: Throwable?) {
                println("onError: ${e?.message}")
            }
        }

        val booleanObserver = object : Observer<Any>{
            override fun onSubscribe(d: Disposable?) {
                println("On subscribe callable")
            }

            override fun onNext(t: Any?) {
                println(t)
            }

            override fun onError(e: Throwable?) {
                println("error $e")
            }

            override fun onComplete() {
                println("On complete fromCallable")
            }
        }

        fun execJust() {
            producer.just()
                .subscribe(stringObserver)
        }
        fun execFromCallable(){
            producer.fromCallable()?.subscribe(booleanObserver)
        }

        fun exec() {
            execJust()
            execFromCallable()
        }
    }

}