# GNU Makefile
JAR=jar
JAVA=java
JAVAC=javac

JFLAGS = -g 
.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $*.java

CLASSES = \
	Aluno.java\
	AlunoService.java\
	AlunoServiceImpl.java\
	Client.java\
	SecretariaService.java\
	SecretariaServiceImpl.java\
	Server.java\
	Turma.java\
	TurmaService.java\
	TurmaServiceImpl.java
	
default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm -f *.class 