package com.example.mealer;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void valid_inputsverification_repas(){
        UserHelperClass test=new UserHelperClass();
        Boolean result=test.validinputs("pates", "plat principal", "italien", "farine, oeufs", "aucun", "20.25");
        assertTrue(result);
    }
    @Test
    public void invalid_inputsverification_repas(){
        UserHelperClass test=new UserHelperClass();
        Boolean result=test.validinputs("chocolat", "dessert", "", "cacao", "noix", "10");
        assertFalse(result);
    }
    @Test
    public void repas_offert(){
        UserHelperClass test=new UserHelperClass();
        test.setOffert(1);
        Boolean result = test.offert();
        assertTrue(result);
    }

    @Test
    public void supprimer_repasOffert(){
        UserHelperClass test=new UserHelperClass("chocolat", "italien", "cacao", "dessert", "noix", "10");
        test.setOffert(1);
        Boolean result = test.supprimer("chocolat");
        assertFalse(result);
    }
}