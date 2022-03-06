package plotgraph;
import plotgraph.parser.*;
import plotgraph.lexer.*;
import plotgraph.node.*;
import java.io.*;

public class Compiler
{
public static void main(String[] args)
  {
    try {
    
    // Create a Parser instance.
    Parser p = new Parser(
      new Lexer (
      new PushbackReader(
      new BufferedReader(
      new FileReader(args[0])), 1024)));
                               
      // Parse the input.
      Start tree = p.parse();
	  tree.apply(new SemanticAnalyzer_project());
	  tree.apply(new CodeGenerator());
	  String currentDirectory = new java.io.File( "." ).getCanonicalPath();
	  currentDirectory = currentDirectory.replace("\\","/");
	  File dir = new File(currentDirectory);
	  String cmd = "javac plotgraph/demo.java";
	  Process process = Runtime.getRuntime().exec(cmd, null, dir);
	  process.waitFor();
	  cmd = "java plotgraph.demo";
	  Process process1 = Runtime.getRuntime().exec(cmd, null, dir);
	  //tree.apply(new DrawingX());
	  //tree.apply(new function());
      }
      catch(Exception e) { System.out.println(e.getMessage()); }
   }
}
