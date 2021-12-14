## **Trilha Cloud Native: Tema 06 - Calculadora Spring containerizada com Docker**

### Tecnologias usadas e passos necessários para executar a aplicação

1. Instale Docker na sua máquina local. Siga o tutorial de instalação de acordo com seu sistema operacional no site oficial do Docker a partir do link abaixo:
   https://docs.docker.com/get-docker/
2. Clone este repositório e salve-o numa pasta de livre escolha.

### Criando a imagem

Na pasta raiz do projeto, execute o comando abaixo para efetivamente criar a imagem. A _flag -t_ refere-se à opção de _tag_, ou seja, pode-se alterar o nome e a etiqueta da aplicação conforme escolha própria. No caso deste tema, foi escolhido o nome _calculator-app._

```dockerfile
sudo docker build -t calculator-app . 
```

Para atestar a funcionalidade do comando anterior, execute um novo comando `sudo docker image ls` e verifique a existência de 2 imagens relevantes: _calculator-app e jetty:9.4.44-jdk11_. A imagem de _jetty_ foi utilizada no _build_ para construir a imagem da aplicação Calculadora do projeto em questão.

### Criando o container

Novamente na pasta raiz do projeto, execute o comando abaixo para criar e executar um container baseado na imagem anterior. 

* A _flag --name_ define o nome do container para facilitar a referência futura e pode ser alterada livremente. Evita-se assim a geração de nomes aleatórios para o container. Utilizou-se _calc_ para este projeto. 
* A _flag -d_ refere-se à execução do container no _background_, ou seja, no modo _detached_. Na prática, essa opção permite que o container execute de maneira isolada e sem "pendurar" o terminal. 
* A _flag --rm_ refere-se à limpeza do container ao término da execução (no caso deste projeto, após o comando explícito de parada de execução que será mencionado posteriormente). 
* Por fim, a _flag -p_ está publicando a porta interna do container e mapeando explicitamente para uma porta única do _host_. Neste caso foi usado o formato `hostPort:containerPort` para o mapeamento. 

```dockerfile
sudo docker run --name calc -d --rm -p 8080:8080 calculator-app
```

Pode-se verificar o funcionamento até este momento através do comando `sudo docker container ls`. Observe o container devidamente criado com o nome escolhido anteriormente e baseado na imagem previamente estabelecida. O STATUS deve estar como _Up_ e as devidas portas (8080:8080) de TCP devem estar expostas. 

Se tudo estiver certo até aqui, a aplicação está pronta para o uso. Caso queira interromper a execução, use o seguinte comando (o nome deve concordar com o configurado anteriormente):

```dockerfile
sudo docker stop calc
```

### Passos para executar as operações na Calculadora

1. As operações disponíveis são as seguintes: _add/sub/mul/div/pow_. Elas se referem, respectivamente, às operações de adição, subtração, multiplicação, divisão e exponenciação. Dois números também devem ser providenciados como parâmetros _x e y_ para a função.

2. O formato URI correto para executar as operações com a Calculadora é o seguinte:
   _http://localhost:8080/calculator-app/calculations/op=insert-operation&x=insert-first-argument&y=insert-second-argument_

3. Um exemplo de subtração, portanto, é realizado da seguinte maneira: _http://localhost:8080/calculator-app/calculations?op=sub&x=9&y=3_

4. Uma página com uma mensagem de boas-vindas será apresentada e o resultado da operação aparecerá apropriadamente. A página também oferece uma funcionalidade extra de executar novas operações através do preenchimento de um formulário, ao invés do método URI apenas. A escolha é totalmente sua, afinal, a aplicação ainda pode ser executada de ambas as maneiras! 

