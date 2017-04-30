package com.patrickshim.univtodolist.tasks;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by patrickshim on 01/05/2017.
 */

public class SampleTest {

    private int checkme = 1;

    @Test
    public void shouldPassOne() {
        checkme = checkme + 1;
        System.out.println(checkme);
        Assert.assertEquals(1, 1);
    }

    @Test
    public void shouldPassTwo() {
        checkme = checkme + 1;
        System.out.println(checkme);
        Assert.assertEquals(2, 2);
    }
}
