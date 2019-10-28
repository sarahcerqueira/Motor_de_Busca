# Motor_de_Busca

## Compilação 
Passos para compilação:

1. Extrair o arquivo Motor_de_Busca
2. Abrir o terminal e navegar até a pastar "Motor_de_Busca"
3. Entrar na pasta: "Motor_de_Busca\ServerRMI\src"
	cd Motor_de_Busca\ServerRMI\src
4. Da o seguinte comando:
	javac model/ServerRMI.java
5. Construir stub e skeleton:
	rmic model.ServerRMI
6. Iniciar o RMI Registry
	start start rmiregistry
7. Todas as pastas no "Motor_de_Buscar":
	ClientRMI
	ServerRMI
	ServidorMulticast

	são todas projetos java do Eclipse. Importe cada projeto individualmente.

8. Primeiro execute o ServidorMulticast
9. Segundo execute o ServerRMI
10. E por fim o ClientRMI

## Explicação do funcionamento do sistema

 A atual versão do sistema só funciona com um de cada servidor. E só fora impementadas as seguintes funcionalidades

 <b> Funcionalidades do sistema <b>
 * Comunicação Multicast
 * Comunicação RMI
 * Comunicação UDP

 <b> Funcionalidades de todo usuário<b>
 * Registro
 * Login
 * Receber notificação quando se muda a permissão do usuário

 <b> Funcionalidades do administrador <b>
 * Mudar permissão de usuário





