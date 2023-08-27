package org.dev.kafka.poc.OrderProducer;


public class temp {

    static int ans=Integer.MIN_VALUE;
    public static void main(String[] args) {
        int height[] = new int[]{5,10,5,10,5};
        recurse(height,height.length-1);
        System.out.println(ans);
    }

    static int recurse(int[] height,int n){
        if(n==0)
        {
            int sum=0;
            for(int num:height)
                sum+=num;
            ans=Math.max(ans,sum);
            return height[0];
        }

        if(n==height.length-1||height[n-1]<=height[n]||height[n]>=height[n+1])
            return height[n]+recurse(height,n-1);
        else{
            int leftSubValue,rightSubValue;

            int temp=height[n-1];
            height[n-1]=height[n];
            leftSubValue = height[n]+recurse(height,n-1);
            //backtrack
            height[n-1]=temp;

            temp=height[n+1];
            height[n+1]=height[n];
            rightSubValue = height[n]+recurse(height,n+1);
            // backtrack
            height[n+1]=temp;

            return Math.max(leftSubValue,rightSubValue);
        }
    }
}
