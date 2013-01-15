package source;

import java.util.ArrayList;

public class Brain {

	static int maxSize = 512;
	static ArrayList<state> diffStates;
	static int flow[][];//which state leads to which state.
	static int state[]; //0: unknown 1:win -1:lose
	
	//passes over flow, marking any states that are winning as 1 in state
	public static void findWinning(){
		boolean win;
		for(int i = 0; i < diffStates.size(); i++){
			win = false;
			if(state[i] == 0){
				for(int j = 0; j < diffStates.size(); j++){
					if(flow[i][j] == 1 && state[j] == -1)
						win = true;	
				}
				if(win)
					state[i] = 1;	
			}
		}
	}
	
	//passes over flow, marking any states that are losing as -1 in state
	public static void findLosing(){
		boolean lose;
			for(int i = 0; i < diffStates.size(); i++){
				lose = true;
				if(state[i] == 0){
					for(int j = 0; j < diffStates.size(); j++){
						if(flow[i][j] == 1 && state[j] == -1)
							lose = false;						
					}
					if(lose)
						state[i] = -1;
				}
			}
	}
	

	public static void main(String args[]){
		/*
		System.out.println("State of one");
		state one = new state(1,3,0,2,0,1);
		one.printStateLong();
		state two = new state(one);
		System.out.println("State of two");
		two.printStateShort();
		two.shift();
		System.out.println("State of two");
		two.printStateLong();
		
		System.out.println("Testing order function");
		state three = new state(0,0,2,5,0,0);
		state four = new state(2,5,0,0,0,0);
		three.order();
		three.printStateShort();
		four.order();
		four.printStateShort();
		//*/
		/*
		System.out.println("Testing child function");
		state parent = new state(0,2,1,2,1,1);
		state child = new state(1,2,0,0,1,2);
		if(parent.child(child))
			System.out.println("IS a child");
		else
			System.out.println("Is NOT a child");
		//*/
		
		//*
		state states[] = new state[512];
		int pos[] = new int[512];
		for(int i = 0; i < 512; i++)
			pos[i] = -1;
		
		int index = -1;
		
		for(int a = 0; a < 2; a++)
			for(int b = 0; b < 4; b++)
				for(int c = 0; c < 2; c++)
					for(int d = 0; d < 4; d++)
						for(int e = 0; e < 2; e++)
							for(int f = 0; f < 4; f++){
								index++;
								states[index] = new state(a,b,c,d,e,f);
								//System.out.println(index+": "+a+" "+b+" "+c+" "+d+" "+e+" "+f);
							}
		
		index++; // index now holds the number of all the states;
		diffStates = new ArrayList<state>();
		
			
			//adding all the unique states to diffStates
			boolean inThere;
			for(int i = 0; i < index; i++){//iterate over all elements in states
				states[i].order();
				inThere = false;
				for(int j = 0; j < diffStates.size(); j++){
					if(states[i].equals(diffStates.get(j)))
						inThere = true;
				}
				if(!inThere)
					diffStates.add(states[i]);			
		}
		//*/
		/*
		for(int i = 0; i < diffStates.size(); i++){
			//System.out.println("i: "+i);
			diffStates.get(i).printStateShort();
		}
		//*/
		//*
		flow = new int[diffStates.size()][diffStates.size()];
		state = new int[diffStates.size()];
		state[0] = -1;
		
		for(int i = 0; i < diffStates.size(); i++)
			for(int j = 0; j <  diffStates.size(); j++){
				flow[i][j] = 0;
				if(diffStates.get(i).child(diffStates.get(j)))
					flow[i][j] = 1;
				
				diffStates.get(j).shift();
				if(diffStates.get(i).child(diffStates.get(j)))
					flow[i][j] = 1;
				
				diffStates.get(j).shift();
				if(diffStates.get(i).child(diffStates.get(j)))
					flow[i][j] = 1;
				diffStates.get(j).shift();	
			}	
		//*/
		//*
		for(int i = 0; i < diffStates.size(); i++){
			for(int j = 0; j < diffStates.size(); j++)
				if(flow[i][j]==0)
					System.out.print(" ");
				else
					System.out.print("1");
			System.out.println("");
		}
		//*/
		
		//*
		for(int i = 0; i < 200; i++){
			findWinning();
			findLosing();			
		}
		
		
		
		for(int i = 0; i < diffStates.size(); i++){
			
			if(state[i] == 1)
				System.out.println("State "+i+ ": WIN");
			else if(state[i] == -1)
				System.out.println("State "+i+ ": LOSE");
			diffStates.get(i).printStateLong();
			System.out.print("Kids: ");
			for(int j = 0; j < diffStates.size(); j++){
				if(flow[i][j] == 1)
					if(state[j] == 1)
						System.out.print(j+":W ");
					else if(state[j] == -1)
						System.out.print(j+":L ");
			}
			System.out.println();
			System.out.println();
				
		}
		
		
		//*/
	}

}
