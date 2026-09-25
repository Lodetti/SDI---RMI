JAVAC = javac
JAVA = java
JFLAGS = -g
CP = "lib/*;."

# Lista de todos os arquivos .java dentro do pacote sca
SOURCES = \
	sca/Aluno.java \
	sca/AlunoService.java \
	sca/AlunoServiceImpl.java \
	sca/Client.java \
	sca/SecretariaService.java \
	sca/SecretariaServiceImpl.java \
	sca/Server.java \
	sca/Turma.java \
	sca/TurmaService.java \
	sca/TurmaServiceImpl.java \
	sca/WSSCA.java \
	sca/WSSCAImpl.java \
	sca/WSServer.java

# Converte os nomes .java para .class
CLASSES = $(SOURCES:.java=.class)

default: compile

compile:
	$(JAVAC) $(JFLAGS) -cp $(CP) sca/*.java

run-rmi:
	$(JAVA) -cp $(CP) sca.Server

run-ws:
	$(JAVA) -cp $(CP) sca.WSServer

clean:
	del /Q sca\*.class