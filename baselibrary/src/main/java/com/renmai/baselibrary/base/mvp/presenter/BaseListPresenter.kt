//package com.dakai.android.base.mvp.presenter
//
//import com.dakai.android.base.mvp.mvpinterface.*
//import com.dakai.android.network.domain.BaseResponse
//import com.dakai.android.util.TextUtil
//import java.lang.reflect.Field
//import java.lang.reflect.ParameterizedType
//import java.util.ArrayList
//
///**
// * 列表presenter基类
// * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
// * @version     ： 1.0
// * @date        ： 2019-10-10 17:24
// */
//abstract class BaseListPresenter<T: Any, F: Any, V : IListView<T, F>>(view: V, model: IListModel<T>) :
//    BasePresenter<V>(view), IListPresenter {
//
//    protected var mDataList: List<F>? = null
//    protected var mModel: IListModel<T>? = null
//
//    init {
//        mView = view
//        mModel = model
//    }
//
//    override fun destroyThing() {}
//
//    override fun getListData() {
//        mWeakOwner?.get()?.let {
//            mModel?.getListData(mView?.getQueryParams(), it, getDataCallback()) ?: mView?.showErrorMessage(RuntimeException("Model cannot be null!!"))
//        }
//    }
//
//    /**列表数据回调处理，如果有特殊业务，可尝试重写此函数*/
//    protected  open fun getDataCallback(): DataCallback<T> {
//        return object : DataCallback<T> {
//            override fun onBaseDataHandle(baseData: BaseResponse<T>?) {
//                if (baseData != null) {
//                    mView?.setBaseData(baseData)
//                    if (baseData.states == 200) {
//                        //展示数据
//                        showData(baseData.result)
//                    } else {
//                        onFailed(baseData.msg)
//                    }
//                } else {
//                    //BaseResponse为空，显示获取数据失败视图
//                    onFailed("未获取到网络数据。")
//                }
//            }
//
//            override fun onStart() {
//                mView?.showLoading()
//            }
//
//            override fun showData(t: T?) {
//                //此处为返回code==200的情况
//                if (t != null) {
//                    mDataList = getFields<T>(t.javaClass, t)
//                    if (mDataList != null && mDataList!!.count() > 0) {
//                        //view显示数据
//                        mView?.showData(mDataList!!)
//                    } else {
//                        //列表为空
//                        mView?.showEmpty(getEmptyTip())
//                    }
//                } else {
//                    //返回数据为空
//                    mView?.showEmpty(getEmptyTip())
//                }
//            }
//
//            override fun onFailed(msg: String) {
//                //返回code!=200情况
//                mView?.showFailureMessage(msg)
//            }
//
//            override fun onError(e: Throwable) {
//                mView?.showErrorMessage(e)
//            }
//
//            override fun onComplete() {
//                mView?.hideLoading()
//            }
//        }
//    }
//
//    protected fun <H> getFields(caz: Class<*>, h: H): ArrayList<F>? {
//        var getFiledCount = 0
//        try {
//            val field = caz.declaredFields
//            val fArrayList = ArrayList<F>()
//            for (aField in field) {
//                aField.isAccessible = true
//                if (getListFiledName() != null) {
//                    //如果泛型类型为空的话就跳过后面的逻辑，继续下一个循环
//                    aField.genericType ?: continue
//                    for (i in 0 until getListFiledName()!!.size) {
//                        val name = getListFiledName()!![i]
//                        val list = getListFromFiled(caz, aField, h, name)
//                        if (list != null) {
//                            fArrayList.addAll(list)
//                            getFiledCount++
//                            if (getFiledCount >= getListFiledName()!!.size) {
//                                return fArrayList
//                            }
//                        }
//                    }
//                } else {
//                    if (aField.type.name == "java.util.List" || aField.type.name == "java.util.ArrayList") {
//                        //如果泛型类型为空的话就跳过后面的逻辑，继续下一个循环
//                        aField.genericType ?: continue
//                        return getListFromFiled(caz, aField, h, "")
//                    }
//                }
//            }
//            return fArrayList
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }
//
//    protected fun getListFiledName(): Array<String>? {
//        return null
//    }
//
//    @Throws(Exception::class)
//    protected fun <H> getListFromFiled(
//        caz: Class<*>,
//        aField: Field,
//        h: H,
//        listFieldName: String
//    ): ArrayList<F>? {
//        val o = getObjectFromFiled(false, caz, aField, h, listFieldName)
//        return if (o == null) null else o as ArrayList<F>?
//    }
//
//    @Throws(Exception::class)
//    protected fun <H> getObjectFromFiled(
//        isObject: Boolean,
//        caz: Class<*>,
//        aField: Field,
//        h: H,
//        listFieldName: String
//    ): Any? {
//        return if (isObject || aField.genericType is ParameterizedType) {
//            val fieldName = aField.name
//            val stringLetter = fieldName.substring(0, 1).toUpperCase()
//            val getName = "get" + stringLetter + fieldName.substring(1)
//            println(getName)
//            // 获取相应的方法
//            val getMethod = caz.getMethod(getName)
//            val o1 = getMethod.invoke(h)
//            return when {
//                TextUtil.isEmptyStr(listFieldName) -> o1
//                listFieldName == fieldName -> o1
//                else -> null
//            }
//        } else null
//    }
//}