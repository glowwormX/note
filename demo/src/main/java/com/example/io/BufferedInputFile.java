package com.example.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class BufferedInputFile {
    public static String read(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = reader.readLine()) != null){
                sb.append(str + "\n");
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static void main(String[] args) throws IOException {
        //直接输出
        String str = read("/home/hlkj/ideaProjects/note/demo/src/main/java/com/example/io/BufferedInputFile.java");
        System.out.println(str);

        //从内存输入
        StringReader stringReader = new StringReader(str);
        int c;
        while ((c = stringReader.read()) != -1){
            System.out.print((char) c);
        }
    }
}
