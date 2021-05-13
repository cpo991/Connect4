package game.logic.data;

import game.utils.Utils;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class MathGame {
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

    public Boolean getHasWon() { return hasWon; }
    public void setHasWon(Boolean hasWon) { hasWon = hasWon; }

    public void setGameNum(int gameNum) { this.gameNum = gameNum; }
    public int getGameNum() { return gameNum; }

    public int sortValues(){
        return Utils.randNum(1,99);
    }

    public int getFirst() { return num1; }
    public void setFirst(int first) { this.num1 = first; }

    public int getSecond() { return num2; }
    public void setSecond(int second) { this.num2 = second; }

    public void setTotal(double total) { this.total = total; }
    public double getTotal(){ return total; }

    public void setOperator(Character operator) { this.operator = operator; }
    public Character getOperator() { return operator;}

    public void sortExpression(){
        setFirst(sortValues());
        setSecond(sortValues());

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

    public String getExpression() {
        return num1 + " "+getOperator().toString()+" " + num2 + " = " + total;
    }
}
