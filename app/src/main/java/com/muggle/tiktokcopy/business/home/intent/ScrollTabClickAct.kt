package com.muggle.tiktokcopy.business.home.intent

/**
 * @date 2026/3/27 0:36
 * @author muggle
 * @desc
 */
sealed interface ScrollTabClickAct {

    /**
     * 选中tab
     */
    class SelectTab(val index: Int) : ScrollTabClickAct


    /**
     * 长按修改tab顺序
     */
    object LongClickTab : ScrollTabClickAct


    /**
     * 刷新tab下数据
     */
    class RefreshTab(val index: Int) : ScrollTabClickAct

}