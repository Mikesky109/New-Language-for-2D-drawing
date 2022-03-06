package plotgraph;

import plotgraph.node.*;
import plotgraph.analysis.*;
import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;

public class CodeGenerator extends DepthFirstAdapter {
	static char TYPE_UNKNOWN='U';
	static char TYPE_INT='I';
	static char TYPE_REAL='R';
	
	
	//record the type of each identifier
	Hashtable<String,String> symtable=new Hashtable<String,String>();
	
	Hashtable<String,String> intIdenValue=new Hashtable<String,String>();

	// record the type of each node (no need to modify the "Node" class)
	Hashtable<Node,Character> nodetype=new Hashtable<Node,Character>();
	StringBuffer codebuf=new StringBuffer();
	
	//Temp List for variable save
	ArrayList<String> Tempdata = new ArrayList<String>();

////////////////////////////// Helper methods //////////////////////////////
	public void setNodeDataType(Node node,char type) {
		nodetype.put(node,new Character(type));
	}
	
	public String getStringValue(String iden) {
		String temp = intIdenValue.get(iden);
		if (temp != null){
			return temp;
		}
		reportError("The variable " + iden + " not been declare\n");
		temp = "";
		return temp;
	}
	
	public void reportError(String message) {
		System.out.println(message);
		System.exit(0);
	}
	
	public void emitCode(String msg) {
		codebuf.append(msg);
	}
	
	public void emitCode(Node node) {
		codebuf.append(node.toString());
	}
	
////////////////////////////// Node traversal //////////////////////////////

	public void inStart(Start node) {
		emitCode("package plotgraph;\n\n");
		emitCode("import java.io.*;\nimport java.util.*;\nimport java.awt.*; \nimport java.awt.geom.*; \nimport javax.swing.*; \n");
		emitCode("public class demo extends DrawEngine {\n");
		emitCode("	public demo() { \n");
		emitCode("		super(\"Group XYZ: Drawing Demo\"); \n");
		emitCode("	}\n");
		emitCode("	public void drawObjects(Graphics2D g2d) {\n");
		emitCode("		double xcur,ycur,xnext,ynext;\n");
		emitCode("		double angle_rad = 0;\n");
	}
	public void outStart(Start node) {
		emitCode("}\n\n");
		emitCode("	public static void main(String[] args) {\n");
		emitCode("		SwingUtilities.invokeLater(new Runnable() {\n");
		emitCode("		@Override\n");
		emitCode("		public void run() {new demo().setVisible(true);}}\n");
		emitCode("		);\n");
		emitCode("	}\n");
		emitCode("}\n");
		System.out.println("-----------CODE-----------");
		System.out.println(codebuf.toString());
		try{
			String currentDirectory = new java.io.File( "." ).getCanonicalPath();
			FileWriter demo = new FileWriter(currentDirectory + "/plotgraph/" + "demo.java");
			demo.write(codebuf.toString());
			demo.flush();
			demo.close();
			/*File file = new File(currentDirectory + "/plotgraph/" + "demo.java");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);*/
		} catch (IOException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	
	public void inALineStatement(ALineStatement node){
		Tempdata.clear();
	}
	
	public void inACircleStatement(ACircleStatement node){
		Tempdata.clear();
	}
	
	public void inAPolygonStatement(APolygonStatement node){
		Tempdata.clear();
	}
	
	public void outALineStatement(ALineStatement node){
		//System.out.println("ALineStatement node: " + node);
		for(String ident : Tempdata)
		{
			/*System.out.println("Value For Line: "+Tempdata.get(0));
			System.out.println("Value For Line: "+Tempdata.get(1));
			System.out.println("Value For Line: "+Tempdata.get(2));
			System.out.println("Value For Line: "+Tempdata.get(3));
			System.out.println("Value For Line: "+Tempdata.get(4));*/
		}
		emitCode("		g2d.setColor(Color." + Tempdata.get(4) + ");");
		emitCode("\n");
		emitCode("		g2d.drawLine(" + Tempdata.get(0) + "," + Tempdata.get(1) + "," + Tempdata.get(2) + "," + Tempdata.get(3) + ");"); 
		emitCode("\n");
		Tempdata.clear();
		
	}
	
	public void outACircleStatement(ACircleStatement node){
		//System.out.println("ACircleStatement node: " + node);
		for(String ident : Tempdata)
		{
			//System.out.println("Value For Circle: "+Tempdata.get(4));
		}
		emitCode("		g2d.setColor(Color." + Tempdata.get(3) + ");");
		emitCode("\n");
		emitCode("		g2d.drawOval(" + (Integer.parseInt(Tempdata.get(0)) -  Integer.parseInt(Tempdata.get(2))) + "," + (Integer.parseInt(Tempdata.get(1)) - Integer.parseInt(Tempdata.get(2))) + "," + Tempdata.get(2) + "," + Tempdata.get(2) + ");");
		emitCode("\n");
		Tempdata.clear();
	}
	
	public void outAPolygonStatement(APolygonStatement node){
		//System.out.println("APolygonStatement node: " + node);
		for(String ident : Tempdata)
		{
			//System.out.println("Value For Circle: "+Tempdata.get(4));
		}	
		emitCode("		g2d.setColor(Color." + Tempdata.get(4) + ");");
		emitCode("\n");
		emitCode("		xcur=" + Tempdata.get(0) + ";\n");
		emitCode("		ycur=" + Tempdata.get(1) + ";\n");
		emitCode("		for (int i=0;i<" + Tempdata.get(2) + ";i++){\n");
		emitCode("			angle_rad+=2*Math.PI/" + Tempdata.get(2) + ";\n");
		emitCode("			xnext=xcur+" + Tempdata.get(3) + "*Math.cos(angle_rad);\n");
		emitCode("			ynext=ycur+" + Tempdata.get(3) + "*Math.sin(angle_rad);\n");
		emitCode("			g2d.drawLine((int)xcur,(int)ycur,(int)xnext,(int)ynext);\n");
		emitCode("			xcur=xnext;\n");
		emitCode("			ycur=ynext;\n");
		emitCode("		}\n");
		emitCode("\n");
		Tempdata.clear();
	}
	
	
	public void outANumberoneVariable(ANumberoneVariable node){
		//System.out.println("ANumberVariable node: " + node);
		Tempdata.add(node.getNumber().getText());
    }
	
	public void outAIdentifier1Variable(AIdentifier1Variable node){
		//System.out.println("AIdentifier1Variable node: " + node);
		Tempdata.add(getStringValue(node.getIdentifier().getText()));
    }
	
	public void outADeclareoneStatement(ADeclareoneStatement node){
		symtable.put(node.getIdentifier().getText(), node.getType().getText());
		intIdenValue.put(node.getIdentifier().getText(), node.getExpr().toString());
	}
	
	public void outADeclaretwoStatement(ADeclaretwoStatement node){
		symtable.put(node.getIdentifier().getText(), node.getType().getText());
		
	}
	
	public void outACommaUnary(ACommaUnary node){
		//System.out.println("ACommatwoUnary node: " + node.getColour().getText());
		//Tempdata.add(node.getVariable().toString());
		//Tempdata.add(node.getUnary().toString());
    }
	
	public void outACommatwoUnary(ACommatwoUnary node){
		//System.out.println("ACommatwoUnary node: " + node.getColour().getText());
		Tempdata.add(node.getColour().getText());
    }
	
	/*public void outAColouroneGetcolour(AColouroneGetcolour node){
		//System.out.println("AColouroneGetcolour node: " + node);
		Tempdata.add(node.getColour().getText());
    }*/
	
}