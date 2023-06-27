all: run

clean:
	rm -f out/Parcs.jar out/MatrixFrobeniusNormTask.jar

out/Parcs.jar: out/parcs.jar src/Parcs.java
	@javac -cp /out/parcs.jar src/Parcs.java
	@jar cf out/Parcs.jar -C src Parcs.class -C src
	@rm -f src/Parcs.class

out/MatrixFrobeniusNormTask.jar: out/parcs.jar src/MatrixFrobeniusNormTask.java
	@javac -cp out/parcs.jar src/MatrixFrobeniusNormTask.java
	@jar cf out/MatrixFrobeniusNormTask.jar -C src MatrixFrobeniusNormTask.class -C src
	@rm -f src/MatrixFrobeniusNormTask.class

build: out/Parcs.jar out/MatrixFrobeniusNormTask.jar

run: out/Parcs.jar out/MatrixFrobeniusNormTask.jar
	@cd out && java -cp 'parcs.jar:Parcs.jar' Parcs
	
