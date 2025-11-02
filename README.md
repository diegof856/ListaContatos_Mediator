# ListaContatos_Mediator

Repositório do projeto **ListaContatos_Mediator**, desenvolvido por **diegof856**.

## ✨ Visão geral

Este projeto implementa um sistema de lista de contatos utilizando o padrão **Mediator**.  
O objetivo é permitir a gestão de contatos (criação, listagem, atualização e remoção) de forma desacoplada, aplicando boas práticas de arquitetura de software.

## 🛠 Pré‑requisitos

Antes de começar, certifique‑se de ter instalado em sua máquina:

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (versão 11 ou superior)  
- [Maven](https://maven.apache.org/install.html) ou [Gradle](https://gradle.org/install/)  
- Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VSCode, etc.)

## 🚀 Instalação e execução

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/diegof856/ListaContatos_Mediator.git
cd ListaContatos_Mediator
```

### 2️⃣ Compilar o projeto

Se estiver usando Maven:

```bash
mvn clean install
```

Se estiver usando Gradle:

```bash
./gradlew build    # Linux/macOS
gradlew.bat build  # Windows
```

### 3️⃣ Executar a aplicação

Após o build, execute o arquivo `.jar` gerado ou rode a classe principal (`Main`) diretamente da sua IDE:

```bash
java -jar target/ListaContatos_Mediator-1.0.jar
```

### 4️⃣ Uso

- Adicione novos contatos informando nome, telefone e e‑mail.  
- Liste os contatos existentes.  
- Atualize ou remova contatos conforme necessário.

## 📂 Estrutura do projeto

```
/src
  /main
    /java       → código‑fonte principal
    /resources  → arquivos de configuração
  /test         → testes automatizados
pom.xml ou build.gradle → script de build
```

## 🤝 Contribuição

Contribuições são bem-vindas!  
Sinta-se à vontade para abrir *issues* e *pull requests* com sugestões ou melhorias.

## 🧑 Autor

Desenvolvido por [diegof856](https://github.com/diegof856)

## 📜 Licença

Distribuído sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.
