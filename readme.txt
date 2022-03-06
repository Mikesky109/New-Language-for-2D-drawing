Prepare：
	P1. Download sablecc-3.2.zip
	P2. In your computer, make a folder called sablecc.
	P3. Unpack sablecc-3.2 into the folder sablecc/, then move grammar.txt, Compiler.java, 
	    CodeGenerator.java, demo.java, DrawEngine.java and SemanticAnalyzer_project.java there too.
	P4. In the folder ".../sablecc", open terminal in Linux (or open Command Prompt in Windows).

Compile：
	C1. Input commmand "java -jar sablecc-3.2/lib/sablecc.jar sablecc-3.2/grammar.txt"
	C2. Put Compiler.java, CodeGenerator.java, demo.java, DrawEngine.java and SemanticAnalyzer_project.java 
            into the folder "../sablecc/sablecc-3.2/plotgraph"
	C3. Go to the folder "../sablecc/sablecc-3.2/"
	C4. Input command "javac plotgraph/Compiler.java" 
	
Draw:
	D1. Create a file input.txt and add some valid contents
	D2. Input command "java plotgraph.Compiler input.txt"
	D3. Change input.txt and add some valid contents to draw another picture
	D3. Use D2
	