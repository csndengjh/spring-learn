package com.springboot.demo.arithmetic;

/**
 *  二分查算法  （升序或者降序）
 * @author dengjianhan
 * @date 2018/12/10 11:10
 */
public class Arithmetic{

    public static void main(String[] args) {
        Integer [] list = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int start = 2;
        int end = 9;
        int low = 0;
        int height = list.length -1;
        int index = 0;
        while (low < height){
            index = (height + low)% 2 == 0 ?(height + low)/2 : (height + low)/2 + 1;
            if(list[index] < end){
                height = index - 1;
            }else if(list[index] > end){
                low = index + 1;
            }else{
                System.out.println(list[index]);
                break;
            }

        }
        for (int i = index + 1;i < list.length;i++ ){
            if(list[i] < start){
                System.out.println(list[i]);
            }
        }

    }
}
