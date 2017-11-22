package com.bwie.sss.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.bwie.sss.presenter.BasePresenter

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/22
 */

abstract class BaseActivity<V, T : BasePresenter<V>> : AppCompatActivity() {

    private var presenter: T? = null

    protected abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        presenter = getPresenter()
        if (presenter != null) {
            presenter!!.attachView(this as V)
        }
        initData()//初始化的方法
    }

    protected abstract fun getPresenter(): T

    protected abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter!!.deattachView()
        }
    }
}
