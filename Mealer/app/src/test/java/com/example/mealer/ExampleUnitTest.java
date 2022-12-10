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
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void recherche_repas_nonOffert(){
        UserHelperClass test=new UserHelperClass();
        String cuisinier="lila@gmail.com";
        String repas="Salade césar";
        test.ajouterRepas(cuisinier, repas);
        Boolean result=test.rechercher(repas);
        assertFalse(result);
    }
    @Test
    public void recherche_repas_Offert(){
        UserHelperClass test=new UserHelperClass();
        String cuisinier="george@gmail.com";
        String repas="Poutine";
        test.ajouterRepas(cuisinier, repas);
        test.offrir(cuisinier, repas);
        Boolean result=test.rechercher(repas);
        assertTrue(result);
    }
    @Test
    public void evaluation(){
        UserHelperClass test=new UserHelperClass();
        String cuisinier="ryan@gmail.com";
        int note=4;
        test.evaluer(cuisinier, note);
        int grade=test.getNote(cuisinier);
        assertEquals(note, grade);
    }

    @Test
    public void evaluation_invalide(){
        UserHelperClass test=new UserHelperClass();
        String cuisinier="amelia@gmail.com";
        int note=-2;
        Boolean validation=test.evaluer(cuisinier, note);
        assertFalse(validation);
    }
}