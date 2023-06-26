all: run

clean:
	rm -f out/MatrixFrobeniusNormAlgorithm.jar out/MatrixFrobeniusNormTask.jar

out/MatrixFrobeniusNormAlgorithm.jar: ./out/parcs.jar ./src/MatrixFrobeniusNormAlgorithm.java
	@javac -cp ./out/parcs.jar ./src/MatrixFrobeniusNormAlgorithm.java
	@jar cf out/MatrixFrobeniusNormAlgorithm.jar -C ./src MatrixFrobeniusNormAlgorithm.class
	@rm -f ./src/MatrixFrobeniusNormAlgorithm.class

out/MatrixFrobeniusNormTask.jar: ./out/parcs.jar ./src/MatrixFrobeniusNormTask.java
	@javac -cp ./out/parcs.jar ./src/MatrixFrobeniusNormTask.java
	@jar cf out/MatrixFrobeniusNormTask.jar -C ./src MatrixFrobeniusNormTask.class
	@rm -f ./src/MatrixFrobeniusNormTask.class

build: out/MatrixFrobeniusNormAlgorithm.jar out/MatrixFrobeniusNormTask.jar

run: out/MatrixFrobeniusNormAlgorithm.jar out/MatrixFrobeniusNormTask.jar
	@cd out && java -cp './parcs.jar:MatrixFrobeniusNormAlgorithm.jar' MatrixFrobeniusNormAlgorithm
	@echo "Enter matrix dimensions (e.g. 4 4):"
	@read dimensions; cd out && java -cp './parcs.jar:MatrixFrobeniusNormTask.jar' MatrixFrobeniusNormTask $${dimensions}

.PHONY: all clean build run