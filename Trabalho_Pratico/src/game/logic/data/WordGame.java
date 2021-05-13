package game.logic.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static game.logic.data.Constants.MAX_WORDS;
import static game.logic.data.Constants.MIN_WORDS;
import static game.utils.Utils.randNum;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class WordGame {
    private List<String> words;
    private int sec;
    private Boolean hasWon;
    private long startTime;

    public WordGame(){
        this.words = new ArrayList<>();
        this.sec = 0;
        this.hasWon = false;
        this.startTime = 0;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public void setStartTime(long startTime) { this.startTime = startTime; }
    public long getStartTime() { return startTime; }

    public Boolean getHasWon() { return hasWon; }
    public void setHasWon(Boolean hasWon) { hasWon = hasWon; }

    public String sortWord() {
        int num = randNum(MIN_WORDS,MAX_WORDS);
        String word = null;
        try
        {
            File file = new File("src/game/utils/words.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuilder sb=new StringBuilder();
            String line;
            int count=1;
            while((line=br.readLine())!=null)
            {
                if(count == num)
                    sb.append(line);
                count++;
            }
            fr.close();
            word = sb.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return word;
    }

    public Boolean addWord(String word) {
        if(!words.contains(word)) {
            words.add(word);
            return true;
        }
        return false;
    }

    public void clearWords(){ this.words = new ArrayList<>();}

    public void add5Words() {
        int words = 0;
        String newWord;
        while (words != 5){
            newWord = sortWord();
            if(addWord(newWord))
                words++;
        }
    }

    public void setSec(){
        this.sec = getWordsString().length()*10000;
    }

    public int getSec() { return sec; }


    public String getWordsString(){
        String wordsString;
        wordsString = String.join(" ",words);
        return wordsString;
    }
}
