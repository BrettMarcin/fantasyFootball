package com.home;

public class BinaryTree {
	
	public Node root;
	
	public void addRankNode(Player data){
		/*
		if(theNode == null){
			Node newNode = new Node(data);
			return newNode;
		} else {
			String left = theNode.theData.first;
			left.concat(theNode.theData.last);
			String right = data.first;
			right.concat(data.last);
			int theSide = my_string_compare(left, right);
			if(theSide > 0){
				theNode.right = addRankNode(data, theNode);
			} else if(theSide < 0){
				theNode.left = addRankNode(data, theNode);
			} else {
				return null;
			}
			return theNode;
		}
		*/
		Node newNode = new Node(data);
		if(root == null){
			root = newNode;
		}else{
			Node focusNode = root;
			Node parent;
			while(true){
				parent = focusNode;
				String left = focusNode.theData.first;
				left.concat(focusNode.theData.last);
				String right = data.first;
				right.concat(data.last);
				int theSide = my_string_compare(left, right);
				if(theSide < 0){
					focusNode = focusNode.left;
					if(focusNode == null){
						parent.left = newNode;
						return;
					}
				} else {
					focusNode = focusNode.right;
					if(focusNode == null){
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}
	public void addInfoNode(Player thePlayer){
		String left = null;
		String right = null;
		if(root != null){
			Node focusNode = root;
			Node parent;
			left = focusNode.theData.first;
			left.concat(focusNode.theData.last);
			right = thePlayer.first;
			right.concat(thePlayer.last);
			int theSide = my_string_compare(left, right);
			if(theSide == 0){
				focusNode.addPlayerInfo(thePlayer);
				return;
			}
			while(focusNode != null){
				parent = focusNode;
				left = focusNode.theData.first;
				left.concat(focusNode.theData.last);
				right = thePlayer.first;
				right.concat(thePlayer.last);
				theSide = my_string_compare(left, right);
				if(theSide < 0){
					focusNode = focusNode.left;
				} else if(theSide > 0){
					focusNode = focusNode.right;
				} else {
					focusNode.addPlayerInfo(thePlayer);
					return;
				}
			}
		}
	}
	
	static int my_string_compare(String Left_string, String Right_string){
		  int i = 0;
		  char leftChar;
		  char rightChar;
		  
		  while(i < Left_string.length() && i < Right_string.length()){
		    leftChar = Left_string.charAt(i);
		    rightChar = Right_string.charAt(i);
		    if(leftChar > rightChar)
		    {
		      return (leftChar - rightChar);
		    } else if(leftChar < rightChar)
		    {
		      return (leftChar - rightChar);
		    }
		    i++;
		  }
		  return 0;
		}
	
	public void print_players(Node theNode){
		if(theNode != null){
			if(theNode.right != null){
				print_players(theNode.right);
			}
			if(theNode.left != null){
				print_players(theNode.left);
			}
			System.out.println("THe rank: " + Integer.toString(theNode.theData.rank) + " name: " + theNode.theData.first + " " + theNode.theData.last + " passYds: " + theNode.theData.passYards + " rushYds: " + theNode.theData.rushYards);
		}
	}
}