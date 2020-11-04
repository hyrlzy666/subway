package subway;

public class Station {
	private String Line;
	private String Name;
	private boolean transfer = false;
	
	
	
	public String getLine() {
		return Line;
	}
	public void setLine(String line) {
		Line = line;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public boolean equals(Station s){
		if(this.Name.equals(s.getName()))
		return true;
		return false;
	}
	public boolean isTransfer() {
		return transfer;
	}
	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}


}
