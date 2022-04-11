package main;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({main.zad1.Main1Test.class, main.zad2.Main2Test.class,main.zad3.Main3Test.class,main.zad4.Main4Test.class,main.zad5.Main5Test.class})
public class TestMains {

}
