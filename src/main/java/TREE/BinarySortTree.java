package TREE;

import java.util.Scanner;

public class BinarySortTree {
	
	public int getData() {
		return data;
	}


	public void setData(int data) {
		this.data = data;
	}


	public BinarySortTree getlNode() {
		return lNode;
	}


	public void setlNode(BinarySortTree lNode) {
		this.lNode = lNode;
	}


	public BinarySortTree getrNode() {
		return rNode;
	}


	public void setrNode(BinarySortTree rNode) {
		this.rNode = rNode;
	}


	private static int MIN=-1;
	private  int data;
	private BinarySortTree lNode;
	private BinarySortTree rNode;
	
	public BinarySortTree() {
		lNode=null;
		rNode=null;
		data=MIN;
	}
	
	public void OrderByPre() {
		System.out.print(this.getData());
		if(null!=this.getlNode()) {
		this.getlNode().OrderByPre();
		}
		if(null!=this.getrNode()) {
		this.getrNode().OrderByPre();
		}
	}
	
	public void OrderByPost() {
		if(null!=this.getlNode()) {
		this.getlNode().OrderByPost();
		}
		if(null!=this.getrNode()) {
		this.getrNode().OrderByPost();
		}
		System.out.print(this.getData());
	}
	
	public void OrderByIn() {
		if(null!=this.getlNode()) {
		this.getlNode().OrderByIn();
		}
		System.out.print(this.getData());
		if(null!=this.getrNode()) {
		this.getrNode().OrderByIn();
		}
	}
	
	public void buildTree(int data) {
		if(this.data==MIN) {
			this.data=data;
			return;
		}
		if(data<this.data) {
			if(null==this.getlNode()) {
				this.setlNode(new BinarySortTree());
			}
			this.getlNode().buildTree(data);
		}else {
			if (null==this.getrNode()) {
				this.setrNode(new BinarySortTree());
			}
			this.getrNode().buildTree(data);
		}
	}
	
	public BinarySortTree findNode(int data) {
		BinarySortTree bTree =this;
		while(bTree!=null) {
			if (bTree.getData()>data) {
				if (bTree.getlNode()==null) {
					return null;
				}
				bTree=bTree.getlNode();
			}else if (bTree.getData()<data) {
				if (bTree.getrNode()==null) {
					return null;
				}
				bTree=bTree.getrNode();
			}
			else {
			return bTree;
			}
		}
		return null;
	}
	
	public BinarySortTree findMin(BinarySortTree bTree) {
		BinarySortTree pTree=bTree;
		while(pTree.getlNode()!=null) {
			pTree=pTree.getlNode();
		}
		return pTree;
	}
		
	public BinarySortTree findNodePre(int data) {
		BinarySortTree bTree =this;
		BinarySortTree bTreePre=null;
		
		while(bTree!=null) {
			if (bTree.getData()>data&&bTree.getlNode()!=null) {
				bTreePre=bTree;
				bTree=bTree.getlNode();
			}else if (bTree.getData()<data&&bTree.getrNode()!=null) {
				bTreePre=bTree;
				bTree=bTree.getrNode();
			}
			else {
			return bTreePre;
			}
		}
		return bTreePre;
	}
	
	public void delNode(int data) {
		BinarySortTree dTree=this.findNode(data);
		BinarySortTree dTreePre=this.findNodePre(data);
		if(null==dTree) {
			System.out.println("It N0 In Tree");
			return ;
		}
		if((dTree.getlNode()==null)&&(dTree.getrNode()==null)) {
			if(dTreePre.getData()<data) {
				dTreePre.setrNode(null);
			}else {
				dTreePre.setlNode(null);
			}
		}
		else if((dTree.getlNode()!=null)&&(dTree.getrNode()==null)) {
			if(dTreePre.getData()<data) {
				dTreePre.setrNode(dTree.getlNode());
			}else {
				dTreePre.setlNode(dTree.getlNode());
			}
		}
		else if((dTree.getlNode()==null)&&(dTree.getrNode()!=null)) {
			if(dTreePre.getData()<data) {
				dTreePre.setrNode(dTree.getrNode());
			}else {
				dTreePre.setlNode(dTree.getrNode());
			}
		}
		else {
			BinarySortTree mTree=this.findMin(dTree.getrNode());
			dTree.delNode(mTree.getData());
			dTree.setData(mTree.getData());
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySortTree bTree=new BinarySortTree();
		Scanner sc = new Scanner(System.in);
		 int data = sc.nextInt();
		 while(data!=-1) {
			 bTree.buildTree(data);
			 data = sc.nextInt();
		 }
		 bTree.OrderByIn();
		 System.out.println("-");
		 bTree.OrderByPost();
		 System.out.println("-");
		 bTree.OrderByPre();
		 System.out.println("-");
		 System.out.println("请问要删除哪个节点数据：输入-1结束");
		 int deldata = sc.nextInt();
		 while(deldata!=-1) {
			 bTree.delNode(deldata);
			 bTree.OrderByIn();
			 System.out.println("-");
			 bTree.OrderByPost();
			 System.out.println("-");
			 bTree.OrderByPre();
			 System.out.println("-");
			 deldata = sc.nextInt();
		 }
	}

}