package org.example.Controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import org.example.Data.EnglishData;

import java.io.FileNotFoundException;

public class ExcelController {
    private static String Path="C:\\";

    private static String FileName="WordList";
    public static void setFileName(String fileName){
        FileName=fileName;
    }
    public static String getFileName(){
        return FileName;
    }
    public static void setPath(String path){
        Path=path;
    }
    public static String getPath(){
        return Path;
    }

    public static void writeExcel(){
        EasyExcel.write("D:\\WordList.xlsx", EnglishData.class).sheet(0).doWrite(EnglishData.getdataList());
    }

    public static void readExcel(){
        EasyExcel.read("D:\\WordList.xlsx", EnglishData.class,new PageReadListener<EnglishData>(dataList->
        {
            for (EnglishData data:dataList){
                System.out.println(data.getEnglish());
                EnglishData.addEnglishDataList(data);
            }
        })).sheet().doRead();
    }


}
