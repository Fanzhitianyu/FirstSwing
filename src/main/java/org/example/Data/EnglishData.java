package org.example.Data;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.util.ListUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@EqualsAndHashCode
public class EnglishData {
    @ExcelIgnore
    private static final List<EnglishData> dataList= ListUtils.newArrayList();


    public static EnglishData add(String english){
        return new EnglishData(english);
    }
    public EnglishData(){
    }

    private EnglishData(String english){
        English=english;
        Times=1;
    }
    public EnglishData(String english,Integer times){
        English=english;
        Times=times;
    }
    public static void addEnglishData(String english){
        dataList.add(new EnglishData(english));
    }
    public static void addEnglishDataList(EnglishData english){
        dataList.add(english);
    }
    @ExcelProperty(value = "Word",index = 0)
    private String English;
    @ExcelProperty(value = "Times",index = 1)
    private Integer Times;

    private void setEnglish(String english){
        this.English=english;
    }
    public void setTimes(Integer times){
        Times=times;
    }
    public String getEnglish(){
        return English;
    }
    public Integer getTimes(){
        return Times;
    }
    public void Times(){
        Times+=1;
    }

    public static List<EnglishData> getdataList(){
        return dataList;
    }

}
