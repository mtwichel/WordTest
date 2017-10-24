import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {
	
	public Random rand;
	private String[] list;
	private String[] duplicates;
	private List<String> engWords;
	private int numWords;
	private int maxLength;
	
	public Main(int numWords, int maxLength){
		this.rand = new Random();
		this.createList(numWords, maxLength);
		System.out.println("Creation Done");
		try {
			engWords = this.getEnglishWords();
			System.out.println("English Words Got");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		//System.out.println("\n\n\n---------\n\n\n");
		//this.findDuplicates();
		//System.out.println("Dups Found");
	}

	public void createList(int numWords, int maxLength){
		String[] words = new String[numWords];
		
		for (int i=0; i<numWords; i++){
			words[i] = createRandWord(maxLength);
		}
		this.list = words;
		this.numWords = numWords;
		this.maxLength = maxLength;
	}
	
	public String createRandWord(int maxLength){
		int temp;
		String ans = "";
		for(int i=0; i<maxLength; i++){
			temp = rand.nextInt(27);
			if (temp == 0){
				i=maxLength;
				if(ans == ""){
					return createRandWord(maxLength);
				}
			}else{
				ans += (char)(temp + 96);
			}
		}
		return ans;
	}
	
	public String[] findDuplicates(String[] input){
		//String[] input = this.list;
		Set<String> uniquUsers = new HashSet<String>();
		List<String> tAns = new ArrayList<String>();
		
		
        for (int i = 0; i < input.length; i++) {
            if (!uniquUsers.add(input[i]))
               tAns.add(input[i]);
        }
        String[] ans = new String[tAns.size()];
        for(int i=0; i<tAns.size(); i++){
        	ans[i] = tAns.get(i);
        }
        return ans;
	}
	public int[] findNumberofOccurencesByWord(String[] input, String[] duplicates){
		int count;
		//String[] duplicates = this.duplicates;
		//String[] input = this.list;
		int[] ans = new int[duplicates.length];
		
		for(int i=0; i<duplicates.length; i++){
			count=0;
			for(int j=0; j<input.length; j++){
				if(duplicates[i].equals(input[j])){
					count+=1;
				}
			ans[i] = count;
			}
			//System.out.println(i + ": working...");
		}
		return ans;
	}
	public int[] findLenthOfWords(String[] input){
		int[] ans = new int[input.length];
		
		for(int x=0; x<input.length; x++){
			ans[x] = input[x].length();
		}
		
		return ans;
	}
	public int[] findNumberofOccurencesByLength(int[] input){
		int[] ans = new int[this.maxLength +1];
		for (int x=0; x<input.length; x++){
			ans[input[x]] += 1;
		}
		return ans;
	}
	public int[] size(){
		String[] dups = this.list;
		int[] ans = new int[dups.length];
		
		for(int x=0; x<dups.length; x++){
			ans[x] = dups[x].length();
		}
		
		return ans;
	}
	public int getMaxLenght(){
		return this.maxLength;
	}
	public String[] getList(){
		return this.list;
	}
	public String[] getDuplicatesList(){
		return this.duplicates;
	}
	public List<String> getEnglishWords() throws IOException{
		File file = new File("/Users/mtwichel/eclipse-workspace/WordTest/src/wordsCommonEn.txt");
		FileReader fr = new FileReader(file);	
		BufferedReader br = new BufferedReader(fr);
		
		List ans = new ArrayList<String>();
		
		while(br.readLine() != null){
			ans.add(br.readLine());
		}
		
		br.close();
		fr.close();
		return ans;
	}
	public List<String> findEnglishWords(){
		List ans = new ArrayList<String>();
		for(int i=0; i<this.list.length; i++){
			if (this.engWords.contains(this.list[i].toLowerCase())){
				ans.add(this.list[i]);
			}
			System.out.println("Line: " + i);
		}
		return ans;
	}
	public static void main(String[] args){
		
		long startTime = System.currentTimeMillis();
		int numWords = 10000000;
		Main main = new Main(numWords, 10);
		
		List<String> temp = main.findEnglishWords();
		long endTime = System.currentTimeMillis();
		System.out.println("Num of Words: " + temp.size() + " | Percentage: " + ((double)temp.size()/(double)numWords) * 100.00);
		
		String[] input= new String[temp.size()];
		for(int j=0; j<input.length; j++){
			input[j] = temp.get(j);
		}
		
		
		int[] lengths = main.findLenthOfWords(input);
		int[] occurence = main.findNumberofOccurencesByLength(lengths);

			for(int y=0; y<main.getMaxLenght(); y++){
				System.out.println(y + ": " + occurence[y]);
			}
	
		
		System.out.println("\n\nTest took: " + ((double)(endTime-startTime)/1000.00) + " seconds");
		
	}
}
