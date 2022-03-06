package plotgraph;

import plotgraph.node.*;
import plotgraph.analysis.*;
import java.util.*;

public class SemanticAnalyzer_project extends DepthFirstAdapter {

ArrayList<String> identDeclared = new ArrayList<String>();
ArrayList<String> identUsedBeforeDeclaration = new ArrayList<String>();
ArrayList<String> unusedVariables = new ArrayList<String>();
ArrayList<String> LineVariables = new ArrayList<String>();

	/*public void caseTIdentifier(TIdentifier node){
     System.out.println("ident node seen: " + node);
    }*/

	public void outADeclareoneStatement(ADeclareoneStatement node){
		identDeclared.add(node.getIdentifier().getText());
		unusedVariables.add(node.getIdentifier().getText());
		//System.out.println("Type " + node.getType().getText());
		//System.out.println("Number " + node.getExpr().toString());
		//System.out.println("ADeclareoneStatement node: " + node);
		//System.out.println("ADeclareoneStatement seen: " + node.getIdentifier());
    }
	
	public void outADeclaretwoStatement(ADeclaretwoStatement node){
		identDeclared.add(node.getIdentifier().getText());
		unusedVariables.add(node.getIdentifier().getText());
		//System.out.println("ADeclaretwoStatement node: " + node);
		//System.out.println("ADeclaretwoStatement seen: " + node.getIdentifier());
    }
	
    public void outAAssignStatement(AAssignStatement node){
		if(! identDeclared.contains(node.getIdentifier().getText()))
		{
			identUsedBeforeDeclaration.add(node.getIdentifier().getText());
		}
		//System.out.println("Astmtsataement node: " + node);
		//System.out.println("Astmtsataement seen: " + node.getIdentifier());
    }
	
	public void outACommatwoUnary(ACommatwoUnary node){
		//System.out.println("Colour node: " + node.getColour().getText());
		//Tempdata.add(node.getNumber().getText());
    }
	
	public void outACommaUnary(ACommaUnary node){
		//System.out.println("ACommaUnary node: " + node.getUnary().toString());
		//Tempdata.add(node.getVariable().toString());
		//Tempdata.add(node.getUnary().toString());
    }

    public void outALineStatement(ALineStatement node){
		System.out.println("ALineStatement node: " + node);
    }
	
	public void outACircleStatement(ACircleStatement node){
      System.out.println("ACircleStatement node: " + node);
    }
	
	public void outAPolygonStatement(APolygonStatement node){
      System.out.println("APolygonStatement node: " + node);
    }
	
	public void outANumberoneVariable(ANumberoneVariable node){
      //System.out.println("ANumberVariable node: " + node);
	  /*if(! identDeclared.contains(node.getIdentifier().getText()))
      {
        identUsedBeforeDeclaration.add(node.getIdentifier().getText());
      }*/
    }
	
	/*public void outACommaUnary(ACommaUnary node){
      System.out.println("ACommaoneUnary node: " + node);
    }*/
	
	public void outAForloopLoop(AForloopLoop node){
      System.out.println("AForloopLoop node: " + node);
    }
	
	public void inAAssignStatement(AAssignStatement node){
		//System.out.println("ANumberVariable node: " + node);
		//System.out.println("expr: " + node.getExpr());
    }
	
	public void outAIdentifier1Variable(AIdentifier1Variable node){
		if(! identDeclared.contains(node.getIdentifier().getText()))
		{
			identUsedBeforeDeclaration.add(node.getIdentifier().getText());
		}
		if(unusedVariables.contains(node.getIdentifier().getText()))
		{
			unusedVariables.remove(node.getIdentifier().getText());
		}
		//System.out.println("AIdentifier1Variable node: " + node);
    }
	
	public void outAListProgram(AListProgram node){
		for(String ident : identDeclared)
		{
			System.out.println("This is declared variable: "+ident);
		}
		for(String ident : identUsedBeforeDeclaration)
		{
			System.out.println("This is the undeclared variable: "+ident);
		}
		for(String ident : unusedVariables)
		{
			System.out.println("This is declared but not used variable: "+ident);
		}
	}
}