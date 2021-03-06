package game.logic.data;

import game.utils.Utils;

import java.io.Serializable;
import java.text.DecimalFormat;

import static java.lang.Math.round;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class MathGame implements Serializable {
    private int gameNum;
    private int num1;
    private int num2;
    private final int sec; //1800
    private double total;
    private Character operator;
    private Boolean hasWon;
    private long startTime;

    public MathGame(){
        this.gameNum = 1;
        this.num1 = this.num2 = 0;
        this.sec = 30000;
        this.total = 0;
        this.operator = ' ';
        this.hasWon = false;
        this.startTime = 0;
    }

    public void setStartTime(long startTime) { this.startTime = startTime; }
    public long getStartTime() { return startTime; }
    public int getSec() { return sec; }

    public void setHasWon(Boolean hasWon) { this. hasWon = hasWon; }

    public void setGameNum(int gameNum) { this.gameNum = gameNum; }
    public int getGameNum() { return gameNum; }

    public int sortValues(){
        return Utils.randNum(1,99);
    }

    public int getFirst() { return num1; }
    public void setFirst(int first) { this.num1 = first; }

    public int getSecond() { return num2; }
    public void setSecond(int second) { this.num2 = second; }

    public void setTotal(double total) { this.total = round(total,2); }
    public double getTotal(){ return total; }

    public void setOperator(Character operator) { this.operator = operator; }
    public Character getOperator() { return operator;}

    public void sortExpression(){
        setFirst(sortValues());
        setSecond(sortValues());
        DecimalFormat df = new DecimalFormat("#.##");
        switch (Utils.randNum(1, 4)) {
            case 1 -> {
                setOperator('+');
                setTotal(getFirst() + getSecond());
            }
            case 2 -> {
                setOperator('-');
                setTotal(getFirst() - getSecond());
            }
            case 3 -> {
                setOperator('/');
                setTotal((double) (getFirst()) / (double) (getSecond()));
            }
            case 4 -> {
                setOperator('*');
                setTotal(getFirst() * getSecond());
            }
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public String getExpression() {
        return num1 + " "+getOperator().toString()+" " + num2;
    }
}
