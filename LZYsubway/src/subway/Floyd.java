package subway;

import java.util.ArrayList;
import java.util.List;

public class Floyd {
	private List<Station> Stationlist = new ArrayList<Station>();
	public ArrayList<String> getShortestPath(String s1,String s2,Graph G,List<Line> Lines) throws Exception{
		int size=G.getMax();
		int[][] path=new int[size][size];
		int[][] d=G.getDist();		
		for(int i=0;i<size;i++)
		    for(int j=0;j<size;j++){
		        path[i][j]=j;
		}
				
		for(int k=0; k<size; k++){
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++) {
					if(d[i][k]!=-1&&d[k][j]!=-1) {
						if(d[i][j]>d[i][k]+d[k][j]) {
							d[i][j]=d[i][k]+d[k][j];
							path[i][j]=path[i][k];
						}	
					}
				}
			}
		}
		int start=G.findStation(s1);
		int end=G.findStation(s2);		
		if(start==-1)
			throw new Exception("起点不存在");
		if(end==-1)
			throw new Exception("终点不存在");	
		if(start==end)
			throw new Exception("起点和终点不能相同");	
		ArrayList<String> result=new ArrayList<String>();
		if(start!=-1&&end!=-1) {
			int count=0;
			int temp=path[start][end];
			Stationlist.add(G.getStations().get(start));
			while(temp!=end ) {
				Stationlist.add(G.getStations().get(temp));
				
				temp=path[temp][end];
			}
			Stationlist.add(G.getStations().get(temp));
			result.add(Integer.toString(Stationlist.size()));	
			result.add(Stationlist.get(0).getName());	
			for(int i=1;i<Stationlist.size()-1;i++) {	
				result.add(Stationlist.get(i).getName());	
				if(Stationlist.get(i).isTransfer()==true) {
					String res=IsTransferLine(Stationlist.get(i-1).getName(),Stationlist.get(i).getName(),Stationlist.get(i+1).getName(),Lines);
					if(res!=null)
						result.add(res);
						
				}
			}	
			result.add(Stationlist.get(Stationlist.size()-1).getName());
			
		}
		
		return result;
	}
	public String IsTransferLine(String pre,String mid,String next,List<Line> allLines) {
		String start=null;
		String end=null;
		for(Line s:allLines) {
			if(s.HaveStation(pre)&&s.HaveStation(mid))
				start=s.getName();
			if(s.HaveStation(mid)&&s.HaveStation(next))
				end=s.getName();
		}
		if(!start.equals(end))
			return end;
			
		return null;
	}
}
