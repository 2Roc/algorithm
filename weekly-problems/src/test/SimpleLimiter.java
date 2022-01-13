package test;

public class SimpleLimiter {
    long next = System.nanoTime();
    long interval = 1000_000_000;
    //预占下一个令牌的时间，返回能够获取令牌的时间
    synchronized long reverse(long now){
        //请求时间在下一令牌产生时间之后
        //重新计算下一令牌的产生时间
        long start = 0;
        if(now >= next) next = now;

        //能够获取令牌的时间

        return start;
    }
}
