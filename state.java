/*
 * This class is a description of a 2 player game called triNim
 * The board is made up of 12 coins in the shape of a triangle.
 * 
 *                 * 
 *                * *
 *               *   *
 *              *     *
 *             * * * * *
 *             
 *On your turn you can remove as many coins as you want from one side of the
 *triangle. The player that removes the last coin wins. 
 */




package source;

import java.util.ArrayList;

public class state {

	int point1;
	int edge1;
	int point2;
	int edge2;
	int point3;
	int edge3;
	
	//constructor
	public state(int point1, int edge1, int point2, int edge2, int point3, int edge3){
		this.point1 = point1;
		this.edge1 = edge1;
		this.point2 = point2;
		this.edge2 = edge2;
		this.point3 = point3;
		this.edge3 = edge3;	
	}
		
	//constructor
	public state(state old){
		this.point1 = old.point1;
		this.edge1 = old.edge1;
		this.point2 = old.point2;
		this.edge2 = old.edge2;
		this.point3 = old.point3;
		this.edge3 = old.edge3;	
	}

	//rotates the data. Has the effect of rotating the state
	public void shift(){
		
		int tempEdge, tempPoint;
		
		tempPoint = this.point1;
		tempEdge = this.edge1;
		this.point1 = this.point2;
		this.edge1 = this.edge2;
		this.point2 = this.point3;
		this.edge2 = this.edge3;
		this.point3 = tempPoint;
		this.edge3 = tempEdge;	
	}
	
	//reflects the state across on of its
	//3 lines of symmetry.
	public void reflect(){
		int tmp = this.edge1;
		this.edge1 = this.edge2;
		this.edge2 = tmp;	
		
		tmp = this.point1;
		this.point1 = this.point3;
		this.point3 = tmp;
	}
	
	
	public boolean equals(state y){
		
		boolean equals = false;
		
		if(this.same(y))
			equals = true;
		
		y.reflect();
		y.order();
		
		if(this.same(y))
			equals = true;
		
		y.reflect();
		y.order();
		
		return equals;
		
		
		
		
	}
	
	
	public boolean same(state x){
		if(this.edge1 == x.edge1)
			if(this.edge2 == x.edge2)
				if(this.edge3 == x.edge3)
					if(this.point1 == x.point1)
						if(this.point2 == x.point2)
							if(this.point3 == x.point3)
								return true;
		return false;
	}
	
	//returns an int version of the state
	public int toInt(){
		int a = 0;
		
		a += this.point1;
		a *=10;
		a +=this.edge1;
		a *=10;
		a += this.point2;
		a *=10;
		a +=this.edge2;
		a *=10;
		a += this.point3;
		a *=10;
		a +=this.edge3;
		a *=10;
		
		return a;		
	}
	
	
	//orders the state so that of the
	//biggest int value of it remains
	public void order(){
		int value1 = 0;
		int value2 = 0;
		int value3 = 0;
		int rValue1 = 0; //reflected values
		int rValue2 = 0;
		int rValue3 = 0;
		
		int biggest = 0;
		
		
		value1 = this.toInt();		
		this.shift();		
		value2 = this.toInt();		
		this.shift();		
		value3 = this.toInt();		
		this.shift();
		
		this.reflect();
		rValue1 = this.toInt();		
		this.shift();		
		rValue2 = this.toInt();		
		this.shift();		
		rValue3 = this.toInt();		
		this.shift();
		this.reflect();
		
		//System.out.println("value1: " + value1);
		//System.out.println("value2: " + value2);
		//System.out.println("value3: " + value3);
		
		if(value1 > biggest)
			biggest = value1;
		if(value2 > biggest)
			biggest = value2;
		if(value3 > biggest)
			biggest = value3;
		if(rValue1 > biggest)
			biggest = rValue1;
		if(rValue2 > biggest)
			biggest = rValue2;
		if(rValue3 > biggest)
			biggest = rValue3;
		
		if(value1 == biggest){
			return;
		}
		if(value2 == biggest){
			this.shift();
			return;
		}
		if(value3 == biggest){
			this.shift();
			this.shift();
			return;
		}
		if(rValue1 == biggest){
			this.reflect();
			return;
		}
		if(rValue2 == biggest){
			this.reflect();
			this.shift();
			return;
		}
		if(rValue3 == biggest){
			this.reflect();
			this.shift();
			this.shift();
			return;
		}
		
	}
	
	//prints long version
	public void printStateLong(){
		
		System.out.println("  "+this.point1);
		System.out.println(" "+this.edge1+" "+this.edge3);
		System.out.println(this.point2+ " " + this.edge2+ " " + this.point3);
		
	}
	
	//prints short version
	public void printStateShort(){
		//System.out.println(index+": "+point1+" "+edge1+" "+point2+" "+edge2+" "+point3+" "+edge3);
		System.out.println(point1+" "+edge1+" "+point2+" "+edge2+" "+point3+" "+edge3);
	}
	
	
	
	//returns true if you can get from the parent to the child
	//in a legal move, otherwise returns false
	//does not check for rotation or reflection
	//use child()
	private boolean childOne(state child){
		
		int diff[] = {0,0,0,0,0,0};
		
		//0's are along one of the sides
		int mask1[] = {0,0,0,-1,-1,-1}; //left side
		int mask2[] = {-1,-1,0,0,0,-1}; //bottem
		int mask3[] = {0,-1,-1,-1,0,0}; //right side
				
		//diff shows how many pieces were removed from each zone
		diff[0] = this.point1 - child.point1;
		diff[1] = this.edge1 - child.edge1;
		diff[2] = this.point2 - child.point2;
		diff[3] = this.edge2 - child.edge2;
		diff[4] = this.point3 - child.point3;
		diff[5] = this.edge3 - child.edge3;
		
		//tests if negative pieces were removed
		for(int i = 0; i < 6; i++)
			if(diff[i] < 0)
				return false;
		
		//tests if at least one piece was removed
		boolean passes = false;
		for(int i = 0; i < 6; i++){
			if(diff[i] > 0)
				passes = true;
		}
		if(!passes)
			return false;
		
		//the next 3 for loops test if pieces were removed 
		//from only one side.
		passes = true;
		for(int i = 0; i < 6; i++){
			if(diff[i] * mask1[i] < 0)
				passes = false;
		}
		if(passes)
			return true;
		
		passes = true;
		for(int i = 0; i < 6; i++){
			if(diff[i] * mask2[i] < 0)
				passes = false;
		}
		if(passes)
			return true;
		
		passes = true;
		for(int i = 0; i < 6; i++){
			if(diff[i] * mask3[i] < 0)
				passes = false;
		}
		if(passes)
			return true;
		
		return false;
	}
	
	
	//tests every state of a to see if it's a child of this
	public boolean child(state a){
		
		boolean result = false;
		
		if(this.childOne(a))
			result = true;
		a.shift();
		if(this.childOne(a))
			result = true;
		a.shift();
		if(this.childOne(a))
			result = true;
		a.shift();
		
		a.reflect();
		if(this.childOne(a))
			result = true;
		a.shift();
		if(this.childOne(a))
			result = true;
		a.shift();
		if(this.childOne(a))
			result = true;
		a.shift();
		a.reflect();
		
		
		return result;
	}
	
	public static ArrayList<state> getUniqueStates(){
		
		ArrayList<state> states = new ArrayList<state>();
		
		for(int a = 0; a < 2; a++)
			for(int b = 0; b < 4; b++)
				for(int c = 0; c < 2; c++)
					for(int d = 0; d < 4; d++)
						for(int e = 0; e < 2; e++)
							for(int f = 0; f < 4; f++){
								states.add(new state(a,b,c,d,e,f));
								//System.out.println(index+": "+a+" "+b+" "+c+" "+d+" "+e+" "+f);
							}
		
		ArrayList<state> diffStates = new ArrayList<state>();
		
		
		//adding all the unique states to diffStates
		boolean inThere;
		for(int i = 0; i < states.size(); i++){//iterate over all elements in states
			states.get(i).order();
			inThere = false;
			for(int j = 0; j < diffStates.size(); j++){
				if(states.get(i).equals(diffStates.get(j)))
					inThere = true;
			}
			if(!inThere)
				diffStates.add(states.get(i));	
			
			
		}
		return diffStates;
	}
	
}
