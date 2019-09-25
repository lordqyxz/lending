package com.p2p.lending.util.un

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/***
 * 日期工具类
 *
 * @author damao
 */
object DateAndTimeUtil {
    /***
     * 日期月份减一个月
     *
     * @param datetime
     * 日期(2014-11)
     * @return 2014-10
     */
    fun dateFormat(datetime: String): String {
        val sdf = SimpleDateFormat("yyyy-MM")
        var date: Date? = null
        try {
            date = sdf.parse(datetime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val cl = Calendar.getInstance()
        cl.time = date!!
        cl.add(Calendar.MONTH, -1)
        date = cl.time
        return sdf.format(date)
    }

    fun dateFormat(date: Date): String {
        val sdf = SimpleDateFormat("yyyy-MM")
        return sdf.format(date)
    }

    /****
     * 传入具体日期 ，返回具体日期减一个月。
     *
     * @param date
     * 日期(2014-04-20)
     * @return 2014-03-20
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun subMonth(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val dt = sdf.parse(date)
        val rightNow = Calendar.getInstance()
        rightNow.time = dt

        rightNow.add(Calendar.MONTH, -1)
        val dt1 = rightNow.time

        return sdf.format(dt1)
    }

    /****
     * 获取月末最后一天
     *
     * @param sDate
     * 2014-11-24
     * @return 30
     */
    private fun getMonthMaxDay(sDate: String): String {
        val sdf_full = SimpleDateFormat("yyyy-MM-dd")
        val cal = Calendar.getInstance()
        var date: Date? = null
        try {
            date = sdf_full.parse("$sDate-01")
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        cal.time = date!!
        val last = cal.getActualMaximum(Calendar.DATE)
        return last.toString()
    }

    // 判断是否是月末
    fun isMonthEnd(date: Date): Boolean {
        val cal = Calendar.getInstance()
        cal.time = date
        return cal.get(Calendar.DATE) == cal
                .getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    /***
     * 日期减一天、加一天
     *
     * @param option
     * 传入类型 pro：日期减一天，next：日期加一天
     * @param _date
     * 2014-11-24
     * @return 减一天：2014-11-23或(加一天：2014-11-25)
     */
    fun checkOption(option: String, _date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val cl = Calendar.getInstance()
        var date: Date? = null

        try {
            date = sdf.parse(_date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        cl.time = date!!
        if ("pre" == option) {
            // 时间减一天
            cl.add(Calendar.DAY_OF_MONTH, -1)

        } else if ("next" == option) {
            // 时间加一天
            cl.add(Calendar.DAY_OF_YEAR, 1)
        }
        date = cl.time
        return sdf.format(date)
    }

    /***
     * 判断日期是否为当前月， 是当前月返回当月最小日期和当月目前最大日期以及传入日期上月的最大日和最小日
     * 不是当前月返回传入月份的最大日和最小日以及传入日期上月的最大日和最小日
     *
     * @param date
     * 日期 例如：2014-11
     * @return String[] 开始日期，结束日期，上月开始日期，上月结束日期
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun getNow_Pre_Date(date: String): Array<String?> {

        val str_date = arrayOfNulls<String>(4)
        val now = Date()
        val sdf = SimpleDateFormat("yyyy-MM")
        val sdf_full = SimpleDateFormat("yyyy-MM-dd")
        val stMonth = sdf.format(now)
        var stdate: String// 开始日期
        var endate: String// 结束日期
        var preDate_start: String// 上月开始日期
        var preDate_end: String// 上月结束日期

        // 当前月
        if (date == stMonth) {
            stdate = "$stMonth-01" // 2014-11-01
            endate = sdf_full.format(now)// 2014-11-24
            preDate_start = subMonth(stdate)// 2014-10-01
            preDate_end = subMonth(endate)// 2014-10-24
        } else {
            // 非当前月
            val monthMaxDay = getMonthMaxDay(date)
            stdate = "$date-01"// 2014-10-01
            endate = "$date-$monthMaxDay"// 2014-10-31
            preDate_start = subMonth(stdate)// 2014-09-01
            preDate_end = subMonth(endate)// 2014-09-30
        }
        str_date[0] = stdate
        str_date[1] = endate
        str_date[2] = preDate_start
        str_date[3] = preDate_end

        return str_date
    }

    @Throws(ParseException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        /*
         * String a =DateAndTimeUtil.dateFormat(new Date());
         * System.out.println(a); String b =
         * DateAndTimeUtil.subMonth("2014-03-31"); System.out.println(b);
         * SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); Date
         * dt=sdf.parse("2014-03-31");
         * System.out.println(DateAndTimeUtil.isMonthEnd(dt));
         */
        var str: String? = null
        // str = DateAndTimeUtil.checkOption("next", "2014-11-30");
        // str = getMonthMaxDay("2014-11-24");
        // str = dateFormat("2014-11");
        str = getNow_Pre_Date("2014-10")[0]
        println(str)
    }
}  