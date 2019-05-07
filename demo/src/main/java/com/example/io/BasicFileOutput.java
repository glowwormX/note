package com.example.io;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * @author 徐其伟
 * @Description:
 * @date 18-12-23 上午10:52
 */
public class BasicFileOutput {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new StringReader(BufferedInputFile.read("x")));

    }
}
