package com.credit.hsbc.service;

import org.springframework.stereotype.Service;

@Service("deleteRepeatStringStrategy")
public class DeleteRepeatStringStrategy implements StringStrategy {
    @Override
    public String process(String inputStr) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 0; i < inputStr.length(); i++) {
            char currentChar = inputStr.charAt(i);
            if (i+1  < inputStr.length() && currentChar == inputStr.charAt(i + 1)) {
                count++;
            } else {
                if (count < 3) {
                    for (int j = 0; j < count; j++) {
                        result.append(currentChar);
                    }
                }
                count = 1;
            }
        }
        return result.toString();
    }
}
