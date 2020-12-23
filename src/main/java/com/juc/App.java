package com.juc;

/**
 * Hello world!
 *
 */
public class App 
{
	public  void twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] + nums[j] == target){
                    System.out.println(nums[i] +","+nums[j]);
                }
            }
        }
    }


    public static void main(String[] args){
    	App s = new App();
        int nums[]  = {2, 7, 4, 5,9,0};
        s.twoSum(nums,9);
    }
}
