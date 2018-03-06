package com.rong.core;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.rong.dao.Memorandum;

/**
 * 日历展示
 * @author Wenqiang-Rong
 * @date 2018年3月5日
 */
public class CanlendarFace {
	public String showHtml(String path,String year, String month, String date) {
		int y, m, d;
		GregorianCalendar temp = new GregorianCalendar();// 取得当前的日历（格里高里历）

		try {

			y = Integer.parseInt(year);

			m = Integer.parseInt(month);

			d = Integer.parseInt(date);

		} catch (Exception e) {

			y = temp.get(GregorianCalendar.YEAR); // 取得年份 ;

			m = temp.get(GregorianCalendar.MONTH); // 取的月份

			d = temp.get(GregorianCalendar.DAY_OF_MONTH); // 取的当前日

		}

		if (y < 1900 || y > 9999)
			y = temp.get(GregorianCalendar.YEAR); // 取得年份 ;

		if (m == -1) {

			y--;

			m = 11;

		} else if (m == 12) {

			y++;

			m = 0;

		} else if (m < -1 || m > 12) {

			m = temp.get(GregorianCalendar.MONTH); // 取的月份

		}

		if (d < 1 || d > 31)
			d = 1;// 默认日

		Calendar thisMonth = Calendar.getInstance();

		thisMonth.set(y, m, d);

		thisMonth.setFirstDayOfWeek(Calendar.SUNDAY);

		thisMonth.set(Calendar.DAY_OF_MONTH, 1);

		int firstIndex = thisMonth.get(Calendar.DAY_OF_WEEK) - 1;

		int maxIndex = thisMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

		String[] days = new String[42];

		for (int i = 0; i < 42; i++) { // 初始化表格数据

			days[i] = "&nbsp;";

		}

		for (int i = 0; i < maxIndex; i++) { // 初始化具有实际日期值得部分

			days[firstIndex + i] = String.valueOf(i + 1);

		}

		String face = "";

		face += "<table id=\"t1\" width=\"91%\" border=\"1\" align=\"center\" cellpadding=\"2\" cellspacing=\"1\" bordercolordark=\"#94CCF6\" bordercolorlight=\"#fefefe\">";

		face += "<tr>";

		face += "<td height=\"35\" colspan=\"7\" align=\"center\" bgcolor=\"#CAE8FF\">";

		face += "<a href=\""+path+"/index?year=" + (y - 1) + "&month=" + m + "&date=1\">上一年</a>&nbsp;";

		face += "<a href=\""+path+"/index?year=" + y + "&month=" + (m - 1) + "&date=1\">上一月</a>";

		face += "<strong id='yearMonth' class=\"STYLE4\" data-year='"+y+"' data-month='"+(m + 1)+"'>　" + y + " 年 " + (m + 1) + "  月 </strong>";// "+d
																						// +"
																						// 日

		face += "<a href=\""+path+"/index?year=" + y + "&month=" + (m + 1) + "&date=1\">下一月</a>&nbsp;";

		face += "<a href=\""+path+"/index?year=" + (y + 1) + "&month=" + m + "&date=1\">下一年</a>";

		face += "</td></tr>";

		face += "<tr>";

		face += "<td width=\"14%\" align=\"center\" bgcolor=\"#C8E1FA\"><span class=\"STYLE3\">日</span></td>";

		face += "<td width=\"14%\" align=\"center\" bgcolor=\"#C8E1FA\">一</td>";

		face += "<td width=\"14%\" align=\"center\" bgcolor=\"#C8E1FA\">二</td>";

		face += "<td width=\"14%\" align=\"center\" bgcolor=\"#C8E1FA\">三</td>";

		face += "<td width=\"14%\" align=\"center\" bgcolor=\"#C8E1FA\">四</td>";

		face += "<td width=\"14%\" align=\"center\" bgcolor=\"#C8E1FA\">五</td>";

		face += "<td align=\"center\" bgcolor=\"#C8E1FA\"><span class=\"STYLE3\">六</span></td>";

		face += "</tr>";

		for (int j = 0; j < 6; j++) {

			face += "<tr id=\"line" + j + "\">";

			for (int i = j * 7; i < (j + 1) * 7; i++) {

				if (i == 35 && days[i].equals("&nbsp;")) {

					face += "<script>line5.style.display=\"none\"</script>";// "<td>&nbsp;</td>";

					break;

				}

				if (days[i].equalsIgnoreCase(String.valueOf(temp.get(GregorianCalendar.DAY_OF_MONTH)))
						&& y == temp.get(GregorianCalendar.YEAR) && m == temp.get(GregorianCalendar.MONTH)) {

					face += "<td bgcolor=\"#E7FEE8\" height=\"50\" align=\"center\"> <span class=\"STYLE10\" onclick='showPop("+days[i]+")'>";//今天

				} else if ((i % 7) == 0 || ((i + 1) % 7) == 0) {

					face += "<td height=\"50\" align=\"center\" ><span class=\"STYLE6\"  onclick='showPop("+days[i]+")'>";//周六，周日

				} else {

					face += "<td height=\"50\" align=\"center\" ><span class=\"STYLE7\" onclick='showPop("+days[i]+")'>";//周1~周5

				}

				face += days[i] + "</span><span style='color:red;display: block; width: 90%;height: 50%;' onclick='detail("+days[i]+")'> "+(Memorandum.count(y,m,days[i])==0?"":Memorandum.count(y,m,days[i]))+" </span>";

				face += "</td>";

			}

			face += "</tr>";

		}

		face += "</table>";

		return face;

	}

}