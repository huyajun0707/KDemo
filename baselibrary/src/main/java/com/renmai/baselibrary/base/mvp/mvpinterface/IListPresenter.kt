package com.renmai.baselibrary.base.mvp.mvpinterface


/**
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-10 18:04
 * @Describe    ： 列表页presenter层接口
 */
interface IListPresenter: IPresenter {

    /**
     * 获取列表数据
     */
    fun getListData()

    /**
     * 获取列表为空时的提示语
     */
    fun getEmptyTip(): String?
}