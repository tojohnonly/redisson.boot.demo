package com.ensk.demo.redisson;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class RedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testLock() {
        RLock lock = redissonClient.getLock("ensk_lock");
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(1, 30, TimeUnit.SECONDS);
            if (isLocked) {
                System.out.println("获取锁成功");
                Thread.sleep(3000L);
            } else {
                System.out.println("获取锁失败");
                return;
            }
        } catch (Exception e) {
            // TODO
        } finally {
            if (isLocked) {
                System.out.println("释放锁");
                lock.unlock();
            }
        }

    }
}