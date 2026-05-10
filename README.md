# 🚗 Automanager API

API desenvolvida em **Java com Spring Boot** para gerenciamento de clientes, incluindo dados como endereço, documentos e telefones.

---

## 📋 Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

- Java 17+  
- Maven  
- MySQL Server  

---

## ⚙️ Configuração do Banco de Dados

1. Acesse o MySQL e crie o banco de dados:

```sql
CREATE DATABASE automanager;

spring.datasource.url=jdbc:mysql://localhost:3306/automanager
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

⚠️ Importante:

O banco automanager precisa existir antes de rodar
Verifique usuário e senha do MySQL (erro comum: Access denied for user)

▶️ Como rodar o projeto

Clone o repositório:

git clone <url-do-repositorio>

Entre na pasta:

cd automanager

Execute o projeto:

./mvnw spring-boot:run

Ou:

mvn spring-boot:run

A aplicação irá subir em:
## 🔗 Endpoints da API

Todos os controllers da aplicação seguem o padrão REST e possuem o **CRUD completo**.

### Pessoas e estrutura
- Clientes → `/clientes`
- Usuários → `/usuarios`
- Empresas → `/empresas`

### Contato e endereço
- Documentos → `/documentos`
- Endereços → `/enderecos`
- Telefones → `/telefones`

### Operações comerciais
- Veículos → `/veiculos`
- Vendas → `/vendas`
- Itens de venda → `/itens-venda`

### Produtos e serviços
- Mercadorias → `/mercadorias`
- Serviços → `/servicos`

### Segurança
- Credenciais → `/credenciais`
