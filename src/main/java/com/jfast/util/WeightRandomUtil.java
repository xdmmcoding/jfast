package com.jfast.util;

import java.util.ArrayList;  
import java.util.List;
import java.util.Map;
import java.util.Random;  
   
public class WeightRandomUtil {
    
    private static Random random = new Random();  
    
    public static String getRandomWeight(Map<String,Integer> map){
    	List<WeightCategory>  categorys = new ArrayList<WeightCategory>();
    	if(map == null || map.isEmpty())return null;
    	Integer weightSum = 0;  
    	for(String key: map.keySet()){
    		categorys.add(new WeightCategory(key,map.get(key)));  
    	}
    	for (WeightCategory wc : categorys) {  
            weightSum += wc.getWeight();  
        } 
        Integer n = random.nextInt(weightSum); // n in [0, weightSum)  
        Integer m = 0;  
        for (WeightCategory wc : categorys) {  
             if (m <= n && n < m + wc.getWeight()) {  
               return wc.getCategory();
             }  
             m += wc.getWeight();  
        }
        //权重为0，随机返回一个
        return (String)map.keySet().toArray()[random.nextInt(weightSum)];
    } 
}  
class WeightCategory {  
	
    private String category;  
    
    private Integer weight;  
       
    public WeightCategory() {  
        super();  
    }  
   
    public WeightCategory(String category, Integer weight) {  
        super();  
        this.setCategory(category);  
        this.setWeight(weight);  
    }  
     
    public Integer getWeight() {  
        return weight;  
    }  
   
    public void setWeight(Integer weight) {  
        this.weight = weight;  
    }  
   
    public String getCategory() {  
        return category;  
    }  
   
    public void setCategory(String category) {  
        this.category = category;  
    }  
}
