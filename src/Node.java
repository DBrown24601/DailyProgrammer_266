import java.io.*;
import java.util.Arrays;
import java.util.Hashtable;

public class Node {
	protected Hashtable<Integer, Node> connections;
	protected int ID;

	public Node(int _x)
	{
		this.ID = _x;
		this.connections = new Hashtable<Integer, Node>();
	}
	
	int getID()
	{
		return this.ID;
	}
	
	public void addConnection(Node n)
	{
		if(!this.connections.containsKey(n.getID()))
		{
			this.connections.put(n.getID(), n);
		}
	}
	
	public int getNumConnections()
	{
		return this.connections.size();
	}
	
	public static void main(String[] args) throws IOException
	{
		String fileName = "nodeList.txt";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		Node[] nodes = new Node[Integer.parseInt(br.readLine())];
		
		for(int x = 0; x < nodes.length; x++)
		{
			nodes[x] = new Node((x+1));
		}
		
		//Read in values
		String line;
		String[] lines;
		while ((line = br.readLine()) != null)
		{
			lines = line.split(" ");
			int connectorNode = Integer.parseInt(lines[0]);
			int connectionNode = Integer.parseInt(lines[1]);
			
			nodes[connectorNode - 1].addConnection(nodes[connectionNode - 1]);
			nodes[connectionNode - 1].addConnection(nodes[connectorNode - 1]);
			
			Arrays.fill(lines, null);
		}
		
		//Read out connections
		System.out.println("Number of nodes: " + nodes.length);
		for(int x = 0; x < nodes.length; x++)
		{
			System.out.printf("Node %d has a degree of %d\n",nodes[x].getID(),nodes[x].getNumConnections());
		}
		br.close();
	}
	
}
