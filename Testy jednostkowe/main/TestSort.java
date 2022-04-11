package main;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({main.zad1.BubbleSortTest.class, main.zad1.CocktailSortTest.class, main.zad1.GnomeSortTest.class, main.zad1.InsertionSortTest.class, main.zad1.SelectionSortTest.class})
public class TestSort {
}
