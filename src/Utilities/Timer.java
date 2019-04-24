package Utilities;

public class Timer {
    double st;
    public void st(){
       this.st =  System.currentTimeMillis();
    }
    public double stopAndRead(){
       return (System.currentTimeMillis()-this.st)/1000.0 ;
    }
}
