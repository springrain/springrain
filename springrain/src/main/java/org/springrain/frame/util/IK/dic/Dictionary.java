/**
 * IK 中文分词  版本 5.0
 * IK Analyzer release 5.0
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 * 
 */
package org.springrain.frame.util.IK.dic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 词典管理类,单子模式
 */
public class Dictionary {
    private static final Logger logger = LoggerFactory.getLogger(Dictionary.class);
    /*
     * 主词典对象
     */
    private static DictSegment mainDict = new DictSegment((char) 0);

    /*
     * 停止词词典
     */
    private static DictSegment stopWordDict = new DictSegment((char) 0);
    /*
     * 量词词典
     */
    private static DictSegment quantifierDict = new DictSegment((char) 0);

    /**
     * 配置对象
     */
    static {
        loadFile(stopWordDict, "stopword.dic");
        
        loadFile(quantifierDict, "quantifier.dic");
     
        loadFile(mainDict, "main2012.dic");
    }

    /**
     * 加载停止符
     * 
     * @param word
     */
    public static void addStopWord(String word) {
        if (StringUtils.isNotBlank(word)) {
            stopWordDict.fillSegment(word.trim().toLowerCase().toCharArray());
        }
    }

    /**
     * 批量加载停止符
     * 
     * @param words
     *            Collection<String>词条列表
     */
    public static void addStopWord(Collection<String> words) {

        if (CollectionUtils.isEmpty(words)) {
            return;
        }

        for (String word : words) {
            addStopWord(word);
        }
    }

    /**
     * 加载新量词
     * 
     * @param word
     */

    public static void addQuantifier(String word) {
        if (StringUtils.isNotBlank(word)) {
            quantifierDict.fillSegment(word.trim().toLowerCase().toCharArray());
        }
    }

    /**
     * 批量加载新量词
     * 
     * @param words
     *            Collection<String>词条列表
     */
    public static void addQuantifier(Collection<String> words) {

        if (CollectionUtils.isEmpty(words)) {
            return;
        }

        for (String word : words) {
            addQuantifier(word);
        }
    }

    /**
     * 移除（屏蔽）词条
     * 
     * @param word
     */
    public static void disableWord(String word) {
        if (StringUtils.isNotBlank(word)) {
            mainDict.disableSegment(word.trim().toLowerCase().toCharArray());
        }
    }

    /**
     * 批量移除（屏蔽）词条
     * 
     * @param words
     */
    public static void disableWord(Collection<String> words) {
        if (CollectionUtils.isEmpty(words)) {
            return;
        }
        for (String word : words) {
            disableWord(word);
        }
    }

    /**
     * 加载新词条
     * 
     * @param word
     */
    public static void addWord(String word) {
        if (StringUtils.isNotBlank(word)) {
            // 批量加载词条到主内存词典中
            mainDict.fillSegment(word.trim().toLowerCase().toCharArray());
        }
    }

    /**
     * 批量加载新词条
     * 
     * @param words
     *            Collection<String>词条列表
     */
    public static void addWord(Collection<String> words) {

        if (CollectionUtils.isEmpty(words)) {
            return;
        }

        for (String word : words) {
            addWord(word);
        }
    }

    /**
     * 检索匹配主词典
     * 
     * @param charArray
     * @return Hit 匹配结果描述
     */
    public static Hit matchInMainDict(char[] charArray) {
        return mainDict.match(charArray);
    }

    /**
     * 检索匹配主词典
     * 
     * @param charArray
     * @param begin
     * @param length
     * @return Hit 匹配结果描述
     */
    public static Hit matchInMainDict(char[] charArray, int begin, int length) {
        return mainDict.match(charArray, begin, length);
    }

    /**
     * 检索匹配量词词典
     * 
     * @param charArray
     * @param begin
     * @param length
     * @return Hit 匹配结果描述
     */
    public static Hit matchInQuantifierDict(char[] charArray, int begin, int length) {
        return quantifierDict.match(charArray, begin, length);
    }

    /**
     * 从已匹配的Hit中直接取出DictSegment，继续向下匹配
     * 
     * @param charArray
     * @param currentIndex
     * @param matchedHit
     * @return Hit
     */
    public static Hit matchWithHit(char[] charArray, int currentIndex, Hit matchedHit) {
        DictSegment ds = matchedHit.getMatchedDictSegment();
        return ds.match(charArray, currentIndex, 1, matchedHit);
    }

    /**
     * 判断是否是停止词
     * 
     * @param charArray
     * @param begin
     * @param length
     * @return boolean
     */
    public static boolean isStopWord(char[] charArray, int begin, int length) {
        return stopWordDict.match(charArray, begin, length).isMatch();
    }
    
    
    private static void loadFile(DictSegment dictSegment,String file){

        InputStream is =null;
        BufferedReader br =null;
            //如果找不到扩展的字典，则忽略
            try {
                is = Dictionary.class.getResourceAsStream(file);
                 br = new BufferedReader(new InputStreamReader(is , "UTF-8"), 512);
                String theWord = null;
                do {
                    theWord = br.readLine();
                    if (theWord != null && !"".equals(theWord.trim())) {
                        dictSegment.fillSegment(theWord.trim().toLowerCase().toCharArray());
                    }
                } while (theWord != null);
                
            } catch (IOException e) {
                logger.error("Extension Stop word Dictionary loading exception.",e);
                
            }finally{
                try {
                    if(br != null){
                        br.close();
                        br = null;
                    }
                    if(is != null){
                        is.close();
                        is = null;
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
    }
    
    

}
