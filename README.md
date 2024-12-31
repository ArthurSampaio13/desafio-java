# CRUD de Usuário com Cálculo de Dígito Único

Este projeto implementa um CRUD para gerenciamento de usuários utilizando Java e Spring Boot. Além disso, a aplicação inclui uma funcionalidade para calcular o **dígito único** de números conforme as especificações fornecidas.

## 🔍 Descrição do Problema

### Dígito Único
O cálculo do dígito único segue as regras:
- Se o número contém apenas um dígito, este é o seu dígito único.
- Caso contrário, o dígito único é a soma dos dígitos do número, repetida até que reste apenas um dígito.

**Exemplo de cálculo**:
- Número: 9875
  - Soma dos dígitos: \( 9 + 8 + 7 + 5 = 29 \)
  - Soma novamente: \( 2 + 9 = 11 \)
  - Soma final: \( 1 + 1 = 2 \)

### Entrada para o cálculo:
Dois parâmetros:
- `n` (String): Número base.
- `k` (Integer): Número de vezes que `n` será concatenado para o cálculo.

**Exemplo**:
- Entrada: `n=9875`, `k=4`
- Concatenado: `9875987598759875`
- Resultado final: `2`

### Funcionalidades
1. CRUD de Usuários com os campos:
   - Nome
   - Email
   - Lista de cálculos de dígito único realizados.
2. API para calcular o dígito único e registrar o resultado associado a um usuário (opcional).

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.7**
- **H2 Database** (memória)
- **Spring Data JPA**
- **Spring Validation**
- **SpringDoc OpenAPI** (Swagger)
- **Lombok**

## 📦 Configuração do Projeto

### Pré-requisitos
- JDK 17 ou superior
- Maven 3.9.9 ou superior

### Clonar o Repositório
```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio
```

### Executar o Projeto
1. Execute o comando:
   ```bash
   ./mvnw spring-boot:run
   ```
2. Acesse a aplicação no navegador:
   - API: [http://localhost:8080](http://localhost:8080)
   - Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Testes
Os testes estão implementados utilizando o framework de testes do Spring Boot e JUnit. Para rodar os testes:
```bash
./mvnw test
```

## 📖 Documentação da API

### Endpoints

#### Usuários
1. **Criar Usuário**
   - `POST /user`
   - **Body**:
     ```json
     {
       "name": "John Doe",
       "email": "john.doe@example.com"
     }
     ```
   - **Resposta**:
     ```json
     {
       "id": "123e4567-e89b-12d3-a456-426614174000"
     }
     ```

2. **Buscar Usuário por ID**
   - `GET /user/{userId}`
   - **Resposta**:
     ```json
     {
       "id": "123e4567-e89b-12d3-a456-426614174000",
       "name": "John Doe",
       "email": "john.doe@example.com"
     }
     ```

3. **Atualizar Usuário**
   - `PUT /user/{userId}`
   - **Body**:
     ```json
     {
       "name": "Jane Doe",
       "email": "jane.doe@example.com"
     }
     ```

4. **Excluir Usuário**
   - `DELETE /user/{userId}`

5. **Obter Cálculos de Dígito Único por Usuário**
   - `GET /user/calculations/{userId}`
   - **Resposta**:
     ```json
     {
       "uniqueDigits": [
         { "result": 2, "number": "9875", "k": 4 },
         { "result": 8, "number": "9875", "k": 1 }
       ]
     }
     ```

#### Cálculo de Dígito Único
1. **Calcular Dígito Único**
   - `POST /calculate`
   - **Body**:
     ```json
     {
       "number": "9875",
       "k": 4,
       "userId": "123e4567-e89b-12d3-a456-426614174000" // Opcional
     }
     ```
   - **Resposta**:
     ```json
     {
       "result": 2
     }
     ```

## 🧪 Testes e Cobertura
O projeto inclui testes unitários para:
- Repositórios
- Serviços
- Controladores
- Domínio (lógica de cálculo de dígito único)
