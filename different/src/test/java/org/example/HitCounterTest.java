package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HitCounterTest {

    @Test
    void should_return4() {
        HitCounter hitCounter = new HitCounter(10);
        hitCounter.hit(100001000);
        hitCounter.hit(100002000);
        hitCounter.hit(10005000);
        hitCounter.hit(10010000);

        Assertions.assertEquals(4, hitCounter.getHits(10010000));
    }

    @Test
    void should_return3() {
        HitCounter hitCounter = new HitCounter(10);
        hitCounter.hit(100001000);
        hitCounter.hit(100002000);
        hitCounter.hit(100005000);
        hitCounter.hit(100011000);

        Assertions.assertEquals(3, hitCounter.getHits(100012000));
    }

}