package com.netting.util;

import java.io.File;
import java.util.List;

import com.netting.bean.TongjiBean;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class CreateXL {

	public static void createExcel(String path,List<TongjiBean> list,String xlsName,String sheetName) {
		try {
			// 在path路径下建立一个excel文件
			WritableWorkbook wbook = Workbook.createWorkbook(new File(path));
			// 创建一个工作表 第一个工作区
			WritableSheet wsheet = wbook.createSheet("数据清单", 0);
			// 设置excel里的字体
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.NO_BOLD, false);
			// 给标题规定字体的格式
			WritableCellFormat titleFormat = new WritableCellFormat(wf);
			String[] title = { "日期", "数量"};
			// 设置表头
			for (int i = 0; i < title.length; i++) {
				// 一列列的打印表头 按照我们规定的格式
				Label excelTitle = new Label(i, 0, title[i], titleFormat);

				// 把标头加到我们的工作区
				wsheet.addCell(excelTitle);
			}
			if(list!=null&&list.size()>0) {
				for(int i = 0 ;i<list.size();i++) {
					Label account = new Label(0, i+1, list.get(i).getTitle());
					Label password = new Label(1, i+1, list.get(i).getValue());

					// 把值加到工作表中
					wsheet.addCell(account);
					wsheet.addCell(password);
				}
			}

			// 写入文件
			wbook.write();
			wbook.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	
	public static void createTongjiExcel(String path,String time,String json1,String json2,String json3,String biaoti,String xlsName,String sheetName) {
		try {
			// 在path路径下建立一个excel文件
			WritableWorkbook wbook = Workbook.createWorkbook(new File(path));
			// 创建一个工作表 第一个工作区
			WritableSheet wsheet = wbook.createSheet("数据清单", 0);
			// 设置excel里的字体
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.NO_BOLD, false);
			// 给标题规定字体的格式
			WritableCellFormat titleFormat = new WritableCellFormat(wf);
			String[] title = ("时间,"+biaoti).split(",");
			// 设置表头
			for (int i = 0; i < title.length; i++) {
				// 一列列的打印表头 按照我们规定的格式
				Label excelTitle = new Label(i, 0, title[i], titleFormat);

				// 把标头加到我们的工作区
				wsheet.addCell(excelTitle);
			}
			String[] a1 = null; String[] a2 = null; String[] a3 = null;
			if(json3!=null&&!"".equals(json3)) a3 = json3.split(",");
			if(json2!=null&&!"".equals(json2))a2 = json2.split(",");
			if(json1!=null&&!"".equals(json1)) {
				a1 = json1.split(",");
				int j = 0;
				for(int i = 1 ;i<a1.length;i=i+2) {
					String s1 = ""; String s2 = ""; String s3 = ""; String[] time1 = time.split(",");
					if(a1!=null&&!"".equals(a1)&&a1.length>1) s1 = a1[i].replace("[", "").replace("]", "");
					if(a2!=null&&!"".equals(a2)&&a2.length>1) s2 = a2[i].replace("[", "").replace("]", "");
					if(a3!=null&&!"".equals(a3)&&a3.length>1) s3 = a3[i].replace("[", "").replace("]", "");
					Label t = new  Label(0, i/2+1, time1[j].replace("[", "").replace("]", ""));
					Label l1 = new Label(1, i/2+1, s1);
					Label l2 = new Label(2, i/2+1,s2);
					Label l3 = new Label(3, i/2+1, s3);
					j++;
					// 把值加到工作表中
					wsheet.addCell(t);
					wsheet.addCell(l1);
					wsheet.addCell(l2);
					wsheet.addCell(l3);
				}
			}

			// 写入文件
			wbook.write();
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}
