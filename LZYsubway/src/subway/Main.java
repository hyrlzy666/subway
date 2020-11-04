package subway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {

		Graph G=new Graph();
		String pathname = ".\\station.txt";
		ArrayList<String> Information=new ArrayList<String>();
		ArrayList<Line> allLines=new ArrayList<Line>();
		
		
		try (
				FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader)
	        ) {
	            String line;	        
	            while ((line = br.readLine()) != null) {
	            	Information.add(line);
	            }
	     } 
		catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		for(int i=0;i<Information.size();i+=2) {
			Line lines =new Line();
			String LineName=Information.get(i);
			lines.setName(LineName);
			String[] stations=Information.get(i+1).split("\\s+");	
			
			for(String s:stations) {
				Station curStation=new Station();
				curStation.setLine(LineName);
				curStation.setName(s);
				lines.getSubwayStation().add(curStation);
			}
			
			allLines.add(lines);
		}
		
		
		G.InitGraph(allLines);
		new ui(G,allLines).setVisible(true);;
		
	}
}
