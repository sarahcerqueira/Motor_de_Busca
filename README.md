# Motor_de_Busca

Para melhor visualização do README.md, link do Github:</br>
https://github.com/sarahecomp/Motor_de_Busca

## Compilação 
Passos para compilação:

1. Extrair o arquivo Motor_de_Busca
2. Abrir o terminal e navegar até a pastar "Motor_de_Busca"
3. Entrar na pasta: "Motor_de_Busca\ServerRMI\src"</br>
	 cd Motor_de_Busca\ServerRMI\src
4. Da o seguinte comando: </br>
	javac model/ServerRMI.java
5. Construir stub e skeleton: </br>
	rmic model.ServerRMI
6. Iniciar o RMI Registry </br>
	start start rmiregistry
7. Todas as pastas no "Motor_de_Buscar":</br>
	ClientRMI</br>
	ServerRMI</br>
	ServidorMulticast</br>

	são todas projetos java do Eclipse. Importe cada projeto individualmente.

8. Primeiro execute o ServidorMulticast
9. Segundo execute o ServerRMI
10. E por fim  execute o ClientRMI

## Explicação do funcionamento do sistema

 A atual versão do sistema só funciona com um de cada servidor. E só foram implementadas as seguintes funcionalidades

 <b> Funcionalidades do sistema </b>
 * Comunicação Multicast
 * Comunicação RMI
 * Comunicação UDP
 * Primeiro usuário a se cadastrar é o administrador

 <b> Funcionalidades de todo usuário</b>
 * Registro
 * Login
 * Receber notificação quando se muda a permissão do usuário (essa notificação só é mostrada no primeiro login após a concessão da permissão).

 <b> Funcionalidades do administrador </b>
 * Mudar permissão de usuário





