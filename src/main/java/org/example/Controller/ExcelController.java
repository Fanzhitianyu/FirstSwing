package org.example.Controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.example.Data.EnglishData;

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
        EasyExcel.write(getPath()+getFileName()+".xlsx", EnglishData.class).sheet("English").doWrite(EnglishData.getdataList());
    }

    public static void readExcel(){
        EasyExcel.read(getPath() + getFileName() + ".xlsx", EnglishData.class, new ReadListener<EnglishData>() {
            @Override
            public void invoke(EnglishData data, AnalysisContext context) {
                EnglishData.addEnglishDataList(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        }).sheet().doRead();
    }


}
