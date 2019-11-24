# Motor_de_Busca

Para melhor visualização do README.md, link do Github:
https://github.com/sarahecomp/Motor_de_Busca

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
10. E por fim  execute o ClientRMI. Caso a excecução seja feita em máquinas diferentes (servidores e clientes em máquinas diferentes) é necessário modificar o ip do servidor RMI no CLientRMI.
O arquivo está no pacote "view", e o arquivo é "MainClientRMI.java". Substituir o ip na linha 25.

## Explicação do funcionamento do sistema

 A atual versão do sistema só funciona com um de cada servidor. E só foram implementadas as seguintes funcionalidades

 Funcionalidades do sistema
 * Comunicação Multicast
 * Comunicação RMI
 * Comunicação UDP
 * Primeiro usuário a se cadastrar é o administrador

  Funcionalidades de todo usuário
 * Registro
 * Login
 * Receber notificação quando se muda a permissão do usuário (essa notificação só é mostrada no primeiro login após a concessão da permissão).

  Funcionalidades do administrador
 * Mudar permissão de usuário





