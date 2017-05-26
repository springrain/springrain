package org.springrain.frame.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.BigIntegerPoint;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.FloatPoint;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;

public class LuceneSearchClause {
    
    //搜索的关键字
    private String keyword="";
    private List<BooleanClause> listClause=new ArrayList<>();
    
    
    public LuceneSearchClause(String keyword){
        this.keyword=keyword;
        
    }
    
    /**
     * 字段值等于某个值
     * @param fieldName
     * @param value
     * @return
     * @throws Exception 
     */
    public List<BooleanClause> addSearchClause(String fieldName,Class fieldType,Object value) throws Exception{
        
        if(fieldType==null||StringUtils.isEmpty(fieldName)||value==null){
            return listClause;
        }
        
      
      
         Query query=null;
         
         String typeName =fieldType.getSimpleName().toLowerCase();
         if(typeName.equals("integer")||typeName.equals("int")){//数字
             query=IntPoint.newExactQuery(fieldName, Integer.valueOf(value.toString()));
         }else if(typeName.equals("biginteger")){//数字
             query=BigIntegerPoint.newExactQuery(fieldName, new BigInteger(value.toString()));
         }else if(typeName.equals("long")){//数字
             query=LongPoint.newExactQuery(fieldName, Long.valueOf(value.toString()));
         }else if(typeName.equals("float")){//数字
             query=FloatPoint.newExactQuery(fieldName, Float.valueOf(value.toString()));
         }else if(typeName.equals("double")){//数字
             query=DoublePoint.newExactQuery(fieldName, Double.valueOf(value.toString()));
         }else if(typeName.equals("date")){//日期
             query=LongPoint.newExactQuery(fieldName, Long.valueOf(((Date)value).getTime()));
         }else{
             Term term = new Term(fieldName, value.toString());
             query = new TermQuery(term);
         }
        
        BooleanClause bc=new BooleanClause(query,Occur.MUST);
        
        listClause.add(bc);
        
        
        return listClause;
    }
    
    /**
     * 字段值在某个值中间
     * @param fieldName
     * @param minValue
     * @param maxValue
     * @return
     * @throws Exception 
     */
   public List<BooleanClause> addSearchClause(String fieldName,Class fieldType,Object minValue,Object maxValue) throws Exception {
       
       if(fieldType==null||StringUtils.isEmpty(fieldName)||minValue==null||maxValue==null){
           return listClause;
       }
       
        Query query=null;
        
        String typeName =fieldType.getSimpleName().toLowerCase();
        if(typeName.equals("integer")||typeName.equals("int")){//数字
            query=IntPoint.newRangeQuery(fieldName, Integer.valueOf(minValue.toString()),Integer.valueOf(maxValue.toString()));
        }else if(typeName.equals("biginteger")){//数字
            query=BigIntegerPoint.newRangeQuery(fieldName, new BigInteger(minValue.toString()), new BigInteger(maxValue.toString()));
        }else if(typeName.equals("long")){//数字
            query=LongPoint.newRangeQuery(fieldName, Long.valueOf(minValue.toString()), Long.valueOf(maxValue.toString()));
        }else if(typeName.equals("float")){//数字
            query=FloatPoint.newRangeQuery(fieldName, Float.valueOf(minValue.toString()), Float.valueOf(maxValue.toString()));
        }else if(typeName.equals("double")){//数字
            query=DoublePoint.newRangeQuery(fieldName, Double.valueOf(minValue.toString()), Double.valueOf(maxValue.toString()));
        }else if(typeName.equals("date")){//日期
            query=LongPoint.newRangeQuery(fieldName, Long.valueOf(((Date)minValue).getTime()), Long.valueOf(((Date)maxValue).getTime()));
        }else{
            query = TermRangeQuery.newStringRange(fieldName, minValue.toString(), maxValue.toString(), true, true);
        }
       
       BooleanClause bc=new BooleanClause(query,Occur.MUST);
       
       listClause.add(bc);
       
       
       return listClause;
   }
    
    
    
    public String getKeyword() {
        return keyword;
    }
    public List<BooleanClause> getListClause() {
        return listClause;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public void setListClause(List<BooleanClause> listClause) {
        this.listClause = listClause;
    }
    
    
    
    
    
    

}
