package com.credit.hsbc;

import com.credit.hsbc.service.StringStrategy;
import com.credit.hsbc.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
@Slf4j
class HsbcApplicationTests {


    //方式一
    @Resource
//    @Resource(name = "replaceRepeatStringStrategy")
    private StringStrategy deleteRepeatStringStrategy;

    //方式二
    @Autowired
    @Qualifier("replaceRepeatStringStrategy")
    private StringStrategy replaceRepeatStringStrategy;


    /**
     * 如果连续3个或更多个字符相同，请将其从字符串中删除。重复此过程，
     * 直到不超过3个相同的字符并排坐着。
     */
    @Test
    public void demo1Process() {
        String inputStr = "aabcccbbad";
//        String inputStr = "gtttggaabcccbbad";
        log.info("删除相同字符方式=》原字符串：{}", inputStr);
        int strNumber = StrUtils.countDistinctChar(inputStr);
        for (int i = 0; i < strNumber; i++) {
            String newString = deleteRepeatStringStrategy.process(inputStr);
            if (newString.equals(inputStr)) {
                break;
            }else {
                inputStr = newString;
            }
            log.info("第{}次循环处理结果输出：{}",i+1, newString);
        }
    }


    /**
     * 如果连续3个或更多个字符相同,替换成按字母顺序排列在它前面的单个字符
     * 重复此过程，直到不超过3个相同的字符并排坐着
     */
    @Test
    public void demo2Process() {
        String inputStr = "abcccbad";
//        String inputStr = "feeefabcccbad";
        log.info("相同字符替换方式=》原字符串：{}", inputStr);
        int strNumber = StrUtils.countDistinctChar(inputStr);
        for (int i = 0; i < strNumber; i++) {
            String newString = replaceRepeatStringStrategy.process(inputStr);
            if (newString.equals(inputStr)) {
                break;
            }else {
                inputStr = newString;
            }
            log.info("第{}次循环处理结果输出：{}",i+1, newString);
        }
    }



}
