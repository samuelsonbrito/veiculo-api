# Veículos API

API REST para cadastro e consulta de veículos, desenvolvida com **Spring Boot 3**, **Java 17**, **JPA/Hibernate**, banco de dados **H2 InMemory** e migrations com **Flyway**.

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas com separação clara de responsabilidades:

```
web/           → Controllers REST + Configuração OpenAPI + DTOs de entrada/saída
application/   → Serviços, casos de uso (Commands e Queries)
domain/        → Entidades, Enumeradores, Interfaces de Repositório
infra/         → Entidade JPA, Implementação do Repositório, Configuração do H2 e Flyway
```

### Fluxo das camadas

```
HTTP Request → Controller (Web)
                  ↓
             VeiculoService (Application)
                  ↓
             VeiculoRepository interface (Domain)
                  ↓
             VeiculoRepositoryImpl (Infra) → JPA → H2
```

### Padrão CQRS leve
- **Commands**: `AdicionarVeiculoCommand`, `AtualizarVeiculoCommand`
- **Queries**: `VeiculoResult`

---

## 🚀 Como executar

Escolha uma das duas formas abaixo:

---

### 🐳 Opção 1 — Docker (sem precisar de Java instalado)

**Pré-requisitos:** Docker e Docker Compose instalados.

```bash
docker compose up --build
```

Para encerrar:

```bash
docker compose down
```

---

### ☕ Opção 2 — Maven local (requer Java 17+)

**Pré-requisitos:** Java 17+ e Maven 3.8+ instalados.

**Linux / Git Bash:**
```bash
./mvnw spring-boot:run
```

**Windows (PowerShell):**
```powershell
.\mvnw.cmd spring-boot:run
```

**Ou via Maven global:**
```bash
mvn spring-boot:run
```

**Gerar o JAR e executar:**
```bash
./mvnw clean package
java -jar target/veiculos-api-1.0.0.jar
```

---

Em ambos os casos a aplicação estará disponível em `http://localhost:8080`.

---

## 📖 Como acessar o Swagger

Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

O Swagger permite:
- Visualizar todos os endpoints disponíveis
- Testar as operações diretamente pelo browser
- Consultar os modelos de entrada e saída com seus tipos e validações

---

## 🗄️ Como acessar a base de dados (H2 Console)

Acesse a página de login e preencha manualmente:

```
http://localhost:8080/h2-console
```

| Campo         | Valor                      |
|---------------|----------------------------|
| JDBC URL      | `jdbc:h2:mem:veiculosdb`   |
| User Name     | `sa`                       |
| Password      | *(deixe em branco)*        |

---

## 📋 Endpoints disponíveis

| Método   | Endpoint              | Descrição                        |
|----------|-----------------------|----------------------------------|
| `POST`   | `/api/veiculos`       | Cadastrar um novo veículo        |
| `PUT`    | `/api/veiculos/{id}`  | Atualizar um veículo existente   |
| `GET`    | `/api/veiculos/{id}`  | Consultar um veículo por ID      |
| `GET`    | `/api/veiculos`       | Listar veículos com paginação    |
| `DELETE` | `/api/veiculos/{id}`  | Remover um veículo               |

### Parâmetros de paginação (GET /api/veiculos)

| Parâmetro | Descrição             | Padrão |
|-----------|-----------------------|--------|
| `page`    | Número da página      | 0      |
| `size`    | Tamanho da página     | 10     |
| `sort`    | Campo de ordenação    | id     |

Exemplo: `GET /api/veiculos?page=0&size=5&sort=modelo,asc`

---

## 📦 Exemplo de payload

### Cadastrar veículo (POST /api/veiculos)

```json
{
  "descricao": "Sedan completo, único dono",
  "marca": "TOYOTA",
  "modelo": "Corolla XEi",
  "opcionais": "Ar condicionado, direção elétrica, rodas de liga",
  "valor": 89900.00
}
```

### Marcas disponíveis

`CHEVROLET`, `FIAT`, `FORD`, `HONDA`, `HYUNDAI`, `JEEP`, `KIA`, `MERCEDES_BENZ`, `NISSAN`, `RENAULT`, `TOYOTA`, `VOLKSWAGEN`, `VOLVO`, `OUTROS`

---

## 🧪 Executar testes

```bash
./mvnw test
```

---

## 🛠️ Tecnologias utilizadas

| Tecnologia            | Versão  | Finalidade                      |
|-----------------------|---------|---------------------------------|
| Java                  | 17      | Linguagem                       |
| Spring Boot           | 3.2.4   | Framework principal             |
| Spring Data JPA       | -       | Persistência                    |
| Hibernate             | -       | ORM                             |
| H2 Database           | -       | Banco de dados InMemory         |
| Flyway                | -       | Migrations de banco             |
| Jakarta Bean Validation| -      | Validação de dados              |
| SpringDoc OpenAPI     | 2.4.0   | Documentação Swagger            |
| JUnit 5 + Mockito     | -       | Testes unitários                |
