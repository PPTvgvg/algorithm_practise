package com.kuaka.cn.algorithm.bitwiseoperations;

import com.kuaka.cn.algorithm.NumberUtils;

/**
 * @author liqiang.chen
 * @time 2023/3/15 20:17
 **/
public class BitwiseOperations {
    /**
     * 给定一个int类型的数据，其中只有一个数出现了奇数次，其他数都出现偶数次
     * 请找出这个出现奇数次的数
     */
    public static int search(int[] source) {
        if (source == null || source.length == 0) {
            return -1;
        }

        int result = 0;
        for (int i : source) {
            result ^= i;
        }

        return result;
    }

    /**
     * 给定一个int类型的数据，其中只有两个数出现了奇数次，其他数都出现偶数次
     * 请找出这两个出现奇数次的数
     */
    public static int[] searchTwo(int[] source) {
        // 假设出现奇数次的数的值分别为a 和 b，那么init = a^b
        int init = search(source);

        // 找到init 二进制形式中最后一位唯一的位置
        int temp = init & (~init + 1);

        int index = 0;
        while (((temp >> index) & 1) == 0) {
            index++;
        }

        int a = 0;
        for (int number : source) {
            if (((number >> index) & 1) == 1) {
                a ^= number;
            }
        }

        int b = init ^ a;

        return new int[] {a, b};
    }

    /**
     * 给定一个int类型的数据，其中只有一个数出现了K数次，其他数都出现M数次
     * M>1, K<M
     * 请找出这个出现K数次的数
     */
    public static int searchK(int[] source, int K, int M) {
        int[] base = new int[32];
        for (int number:source) {
            for (int i = 0; i < 32; i++) {
                base[i] += (number >> i) & 1;
            }
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (base[i] % M == K) {
                result |= 1 << i;
            }
        }

        return result;
    }

    public static int[] createSource(int min, int max, int maxLength, int scatterFactor) {
        java.util.Map<Integer, Integer> cache = new java.util.HashMap<>();
        int onlyOne = NumberUtils.random(min, max);
        int onlyOneNumber = NumberUtils.getEvenOrOdd(min, maxLength/scatterFactor, 1);
        cache.put(onlyOne, onlyOneNumber);
        int otherNumber = NumberUtils.getEvenOrOdd(Math.min(min, maxLength - onlyOneNumber), Math.max(min, maxLength - onlyOneNumber), 0);
        int[] array = new int[otherNumber + onlyOneNumber];
//
//        cache.forEach((key, value) -> {
//            System.out.print(key + ":" + value + ",");
//        });
//        System.out.println();

        int index = 0;

        for (int i = 0; i < onlyOneNumber; i++) {
            array[index++] = onlyOne;
        }

        while (index < array.length) {
            int number = NumberUtils.getEvenOrOdd(2, Math.max(2, otherNumber/scatterFactor), 0);
            int value;
            do {
                value = NumberUtils.random(min, max);
            } while (cache.containsKey(value));

            for (int i = 0; i < number; i++) {
                array[index++] = value;
            }

            cache.put(value, number);

            otherNumber -= number;
        }

        for (int i = 0; i < array.length; i++) {
            int random = NumberUtils.random(0, array.length-1);
            if ( random != i) {
                array[i] = array[i]^array[random];
                array[random] = array[i]^array[random];
                array[i] = array[i]^array[random];
            }
        }

//        for (int i = 0; i < array.length; i++) {
//            if (i > 0 && i < array.length - 1) {
//                System.out.print(array[i] + ",");
//            } else if (i == 0) {
//                System.out.print("[" +array[i] + ",");
//            } else {
//                System.out.println(array[i] + "]");
//            }
//        }

//        cache.forEach((key, value) -> {
//            System.out.print(key + ":" + value + ",");
//        });

//        System.out.println();

        return array;
    }

    public static int[] createSourceTwo(int min, int max, int maxLength, int scatterFactor) {
        int onlyOne = NumberUtils.random(min, max);
        int onlyOneNumber = NumberUtils.getEvenOrOdd(min, maxLength/scatterFactor, 1);
        int onlyTwo;
        do {
            onlyTwo = NumberUtils.random(min, max);
        } while (onlyTwo == onlyOne);
        int onlyTwoNumber = NumberUtils.getEvenOrOdd(min, (maxLength - onlyOneNumber)/scatterFactor, 1);
        int otherNumber = NumberUtils.getEvenOrOdd(Math.min(min, maxLength - onlyOneNumber- onlyTwoNumber), Math.max(min, maxLength - onlyOneNumber-onlyTwoNumber), 0);
        int[] array = new int[otherNumber + onlyOneNumber + onlyTwoNumber];
        java.util.Map<Integer, Integer> cache = new java.util.HashMap<>();
        cache.put(onlyOne, onlyOneNumber);
        cache.put(onlyTwo,onlyTwoNumber);

        cache.forEach((key, value) -> {
            System.out.print(key + ":" + value + ",");
        });
        System.out.println();

        int index = 0;

        for (int i = 0; i < onlyOneNumber; i++) {
            array[index++] = onlyOne;
        }

        for (int i = 0; i < onlyTwoNumber; i++) {
            array[index++] = onlyTwo;
        }

        while (index < array.length) {
            int number = NumberUtils.getEvenOrOdd(2, Math.max(2, otherNumber/scatterFactor), 0);
            int value;
            do {
                value = NumberUtils.random(min, max);
            } while (cache.containsKey(value));

            for (int i = 0; i < number; i++) {
                array[index++] = value;
            }

            cache.put(value, number);

            otherNumber -= number;
        }

        for (int i = 0; i < array.length; i++) {
            int random = NumberUtils.random(0, array.length-1);
            if ( random != i) {
                array[i] = array[i]^array[random];
                array[random] = array[i]^array[random];
                array[i] = array[i]^array[random];
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (i > 0 && i < array.length - 1) {
                System.out.print(array[i] + ",");
            } else if (i == 0) {
                System.out.print("[" +array[i] + ",");
            } else {
                System.out.println(array[i] + "]");
            }
        }

        cache.forEach((key, value) -> {
            System.out.print(key + ":" + value + ",");
        });

        System.out.println();

        return array;
    }

    public static int testOne(int[] source) {
        java.util.Map<Integer, Integer> map = getResultMap(source);

        for (java.util.Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if ((entry.getValue() & 1) == 1) {
                return entry.getKey();
            }
        }

        return 0;
    }

    public static java.util.Map<Integer, Integer> getResultMap(int[] source) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        for (int number : source) {
            if (map.containsKey(number)) {
                map.put(number, map.get(number) + 1);
            } else {
                map.put(number, 1);
            }
        }

        return map;
    }

    public static java.util.List<Integer> testTwo(int[] source) {
        java.util.Map<Integer, Integer> map = getResultMap(source);

        java.util.List<Integer> result = new java.util.ArrayList<>();
        for (java.util.Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if ((entry.getValue() & 1) == 1) {
                result.add(entry.getKey());
            }
        }

        return result;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
//            int[] source = createSource(0, 1000, 2000, 50);
//            int result = search(source);
//            if (result != testOne(source)) {
//                System.out.println("出错了");
//            }

//            int[] source = createSourceTwo(1,1000, 2000, 50);
//            int[] result1 = searchTwo(source);
//            java.util.List<Integer> list = testTwo(source);
//            System.out.println(list.get(0) + "---" + list.get(1));
//            System.out.println(result1[0] + "---" + result1[1]);
//            if (!testTwo(source).containsAll(java.util.Arrays.asList(result1))) {
//                System.out.println("出错了");
//            }
//            System.out.println(result[0] + ":" + result[1]);

            int result = searchK(new int[] {23,23,55,23,23,23,1,1,1,55,1,1,2,2,2,2,2,3,55,3,3,3,3}, 3, 5);
            System.out.println(result);
        }
    }
}
