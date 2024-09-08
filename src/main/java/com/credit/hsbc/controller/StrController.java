package com.credit.hsbc.controller;

import com.credit.hsbc.service.StringStrategy;
import com.credit.hsbc.utils.StrUtils;
import com.credit.hsbc.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestMapping("/shbc")
@RestController
@Slf4j
public class StrController {

    //方式一
//    @Resource(name = "replaceRepeatStringStrategy")
    @Resource
    private StringStrategy deleteRepeatStringStrategy;

    //方式二
    @Autowired
    @Qualifier("replaceRepeatStringStrategy")
    private StringStrategy replaceRepeatStringStrategy;

    /**
     * 如果连续3个或更多个字符相同，请将其从字符串中删除。重复此过程，
     * 直到不超过3个相同的字符并排坐着。
     * @param inputStr
     * @return
     */
    @GetMapping("/demo1String")
    public Result demo1String(@RequestParam("inputStr") String inputStr) {
        if(Objects.isNull(inputStr)){
            return Result.fail("字符串不能为空！");
        }
//        String inputStr = "aabcccbbad";
//        String inputStr = "gtttggaabcccbbad";
        log.info("删除相同字符方式=》原字符串：{}", inputStr);
        int strNumber = StrUtils.countDistinctChar(inputStr);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strNumber; i++) {
            String newString = deleteRepeatStringStrategy.process(inputStr);
            if (newString.equals(inputStr)) {
                break;
            }else {
                inputStr = newString;
            }
            list.add(newString);
            log.info("第{}次循环处理结果输出：{}",i+1, newString);
        }
//        assertEquals("d", deleteRepeatStringStrategy.process(inputStr));
        return Result.success(list);
    }

    /**
     * 如果连续3个或更多个字符相同,替换成按字母顺序排列在它前面的单个字符
     * 重复此过程，直到不超过3个相同的字符并排坐着
     * @param inputStr
     * @return
     */
    @GetMapping("/demo2String")
    public Result demo2String(@RequestParam("inputStr") String inputStr) {
        if(Objects.isNull(inputStr)){
            return Result.fail("字符串不能为空！");
        }
//        String inputStr = "abcccbad";
//        String inputStr = "feeefabcccbad";
        log.info("相同字符替换方式=》原字符串：{}", inputStr);
        List<String> list = new ArrayList<>();
        int strNumber = StrUtils.countDistinctChar(inputStr);
        for (int i = 0; i < strNumber; i++) {
            String newString = replaceRepeatStringStrategy.process(inputStr);
            if (newString.equals(inputStr)) {
                break;
            }else {
                inputStr = newString;
            }
            list.add(newString);
            log.info("第{}次循环处理结果输出：{}",i+1, newString);
        }
//        assertEquals("d", replaceRepeatStringStrategy.process(inputStr));
        return Result.success(list);
    }

}
