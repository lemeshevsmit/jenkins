package com.mycompany.app;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        boolean logging = args != null && !Arrays.equals(args, new String[]{}) && "-print".equals(args[0]);
        Resource resource = new Resource();
        Numbers numbers = new Numbers(logging,resource);
        numbers.calculate();
    }
}

