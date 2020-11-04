package subway;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private int [][] Dist =new int[500][500];
	private int Max;
	public List<Station> Stations = new ArrayList<Station>();
    public void InitGraph(List<Line> Lines) {
    	for(int i=0;i<Lines.size();i++) {
			List<Station> Stations=Lines.get(i).getSubwayStation();
			for(int j=0;j<Stations.size();j++) {
				int index=this.getIndex(Stations.get(j));
				if(index==-1)
					Stations.add(Stations.get(j));
				else if(index!=-1) {
					Stations.get(index).setTransfer(true);
					Lines.get(i).getSubwayStation().get(j).setTransfer(true);
				}
			}	
		}
		
		
		Max=Stations.size();
		
		for(int i=0;i<Max;i++)
			for(int j=0;j<Max;j++){
				if(i==j)
				Dist[i][j]=0;
				else
				Dist[i][j]=500;
			}		
		
		
		for(Line line:Lines) {
			List<Station> Stations=line.getSubwayStation();
			for(int j=0;j<Stations.size()-1;j++) {
				int start =this.getIndex(Stations.get(j));
				int end =this.getIndex(Stations.get(j+1));
				
				Dist[start][end]=1;
				Dist[end][start]=1;
			}	
		}
    }
    public int getIndex(Station s) {	
		for(int i=0;i<Stations.size();i++)
			if(Stations.get(i).getName().equals(s.getName()))
				return i;	
		return -1;
	}
    public int findStation(String name) {
		for(int i=0;i<Stations.size();i++)
			if(Stations.get(i).getName().equals(name))
				return i;	
		return -1;
	}
	public int getMax() {
		return Max;
	}
	public void setMax(int max) {
		Max = max;
	}
	public int[][] getDist() {
		return Dist;
	}
	public void setDist(int[][] dist) {
		Dist = dist;
	}
	public List<Station> getStations() {
		return Stations;
	}
	public void setStations(List<Station> stations) {
		Stations = stations;
	}
	
}
